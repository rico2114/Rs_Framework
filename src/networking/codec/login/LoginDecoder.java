package networking.codec.login;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * Created with eclipse 25/03/2015 1:02:29 p. m.
 * 
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 * @Author Graham
 */
public class LoginDecoder extends ReplayingDecoder<LoginState> {

	public LoginDecoder() {
		checkpoint(LoginState.HANDSHAKE);
	}

	@Override
	protected void decode(ChannelHandlerContext arg0, ByteBuf arg1, List<Object> arg2) throws Exception {
		switch (state()) {
		case HANDSHAKE:
			break;
		case HEADER:
			break;
		case PAYLOAD:
			break;
		}
	}
}
