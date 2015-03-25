package engine;

import networking.Networking;
import engine.interfaces.Bundle;
import game.World;

/**
 * Created with eclipse 11/03/2015 10:25:26 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class Server implements Bundle<Server> {

	private final int port;
	private World world;
	private Networking networking;
	
	public Server(final int port) {
		this.port = port;
	}

	@Override
	public Server onCreate() {
		this.world = new World();
		this.networking = new Networking(port);
		return this;
	}

	@Override
	public Server onStop() {
		return this;
	}	
	
	public int getPort() {
		return port;
	}
	
	public World getWorld() {
		return world;
	}
	
	public Networking getNetworking() {
		return networking;
	}
}
