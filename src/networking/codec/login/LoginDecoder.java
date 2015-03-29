package networking.codec.login;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

/**
 * Created with eclipse 25/03/2015 1:02:29 p. m.
 * 
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 * @Author Graham
 */
public class LoginDecoder extends ReplayingDecoder<LoginState> {

	/**
	 * The secure random number generator.
	 */
	private static final SecureRandom random = new SecureRandom();

	/**
	 * The login packet length.
	 */
	private int loginLength;

	/**
	 * The reconnecting flag.
	 */
	private boolean reconnecting;

	/**
	 * The server-side session key.
	 */
	private long serverSeed;

	/**
	 * The username hash.
	 */
	private int usernameHash;
	
	public LoginDecoder() {
		checkpoint(LoginState.HANDSHAKE);
	}

	@Override
	protected void decode(ChannelHandlerContext context, ByteBuf buffer, List<Object> out) throws Exception {
		switch (state()) {
		case HANDSHAKE:
			decodeHandshake(context, buffer, out);
			break;
		case HEADER:
			decodeHeader(context, buffer, out);
			break;
		case PAYLOAD:
			break;
		}
	}
	
	private void decodeHandshake(final ChannelHandlerContext context, ByteBuf buffer, List<Object> list) {
		if (buffer.isReadable()) {
			usernameHash = buffer.readUnsignedByte();
			serverSeed = random.nextLong();

			ByteBuf response = context.alloc().buffer(17);
			response.writeByte(0);
			response.writeLong(0);
			response.writeLong(serverSeed);
			
			context.channel().writeAndFlush(response);

			checkpoint(LoginState.HEADER);
		}
	}
	
	private void decodeHeader(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws IOException {
		if (buffer.readableBytes() >= 2) {
			int loginType = buffer.readUnsignedByte();

			if (loginType != 16 && loginType != 18) {
				throw new IOException("Invalid login type.");
			}

			reconnecting = loginType == 18;
			loginLength = buffer.readUnsignedByte();

			checkpoint(LoginState.PAYLOAD);
		}
	}
	
	private void decodePayload(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
		if (buffer.readableBytes() >= loginLength) {
			ByteBuf payload = buffer.readBytes(loginLength);
			int clientVersion = 255 - payload.readUnsignedByte();

			int releaseNumber = payload.readUnsignedShort();

			int lowMemoryFlag = payload.readUnsignedByte();
			if (lowMemoryFlag != 0 && lowMemoryFlag != 1) {
				throw new Exception("Invalid value for low memory flag.");
			}

			boolean lowMemory = lowMemoryFlag == 1;

			int[] archiveCrcs = new int[8];
			for (int i = 0; i < 9; i++) {
				archiveCrcs[i] = payload.readInt();
			}

			int securePayloadLength = payload.readUnsignedByte();
			if (securePayloadLength != loginLength - 41) {
				throw new Exception("Secure payload length mismatch.");
			}
			
			int secureId = payload.readUnsignedByte();
			if (secureId != 10) {
				throw new Exception("Invalid secure payload id.");
			}

			long clientSeed = payload.readLong();
			long reportedServerSeed = payload.readLong();
			if (reportedServerSeed != serverSeed) {
				throw new Exception("Server seed mismatch.");
			}

			int uid = payload.readInt();

			String username = payload.readString(payload);
			String password = payload.readString(payload);

			if (password.length() < 6 || password.length() > 20) {
				throw new Exception("Invalid password.");
			} else if (username.isEmpty() || username.length() > 12) {
				throw new Exception("Invalid username.");
			}

			int[] seed = new int[4];
			seed[0] = (int) (clientSeed >> 32);
			seed[1] = (int) clientSeed;
			seed[2] = (int) (serverSeed >> 32);
			seed[3] = (int) serverSeed;

			/*IsaacRandom decodingRandom = new IsaacRandom(seed);
			for (int i = 0; i < seed.length; i++) {
				seed[i] += 50;
			}

			IsaacRandom encodingRandom = new IsaacRandom(seed);

			PlayerCredentials credentials = new PlayerCredentials(username, password, usernameHash, uid);
			IsaacRandomPair randomPair = new IsaacRandomPair(encodingRandom, decodingRandom);

			LoginRequest request = new LoginRequest(credentials, randomPair, reconnecting, lowMemory, releaseNumber, archiveCrcs,
					clientVersion);

			out.add(request);
			if (buffer.isReadable()) {
				out.add(buffer.readBytes(buffer.readableBytes()));
			}*/
		}
	}
}
