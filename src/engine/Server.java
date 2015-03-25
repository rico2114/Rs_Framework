package engine;

import engine.interfaces.Bundle;
import game.World;

/**
 * Created with eclipse 11/03/2015 10:25:26 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class Server implements Bundle<Void> {

	private final int port;
	private final World world;
	
	public Server(final int port) {
		this.port = port;
		this.world = new World();
	}

	@Override
	public Void onCreate() {
		return null;
	}

	@Override
	public Void onStop() {
		return null;
	}
	
	public int getPort() {
		return port;
	}
	
	public World getWorld() {
		return world;
	}
}
