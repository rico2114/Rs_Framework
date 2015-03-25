package game;

/**
 * Created with eclipse 24/03/2015 9:56:58 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class Position {

	private int x;
	private int y;
	private int plane;
	
	public Position(final int x, final int y, final int plane) {
		this.x = x;
		this.y = y;
		this.plane = plane;
	}
	
	public Position(final int x, final int y) {
		this(x, y, 0);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getPlane() {
		return plane;
	}
}
