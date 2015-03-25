package main;


import engine.Server;
import game.EntityRepository;
import game.players.Player;
import game.players.PlayerRepository;

import java.util.Objects;

/**
 * Created with eclipse 14/03/2015 12:09:55 a. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class Launcher {

	private static Server server = null;
	
	public static void main(String[] args) {
		server = new Server(5555);
		
		// multi threading tests
		final PlayerRepository playerRepository = (PlayerRepository) server.getWorld().getRepository(Player.class);
		
		if (Objects.nonNull(playerRepository)) {
			playerRepository.initAll();
		}
		
		for (EntityRepository<?> repositories : server.getWorld().getRepositories()) {
			server.getWorld().getPool().add(repositories.createRecursiveAction());
		}
		
		server.getWorld().getPool().run();
	}
	
	public static final Server getServer() {
		return server;
	}
}
