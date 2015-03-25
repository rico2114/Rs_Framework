package networking;

import java.nio.ByteBuffer;

/**
 * Created with eclipse 21/03/2015 11:44:22 a. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class Packet {

	private final ByteBuffer buffer;
	
	public Packet(final int capacity) {
		this.buffer = ByteBuffer.allocate(capacity);
	}
	
	public ByteBuffer getBuffer() {
		return buffer;
	}
}
