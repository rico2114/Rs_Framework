package game;

import java.util.ArrayList;
import java.util.List;

import engine.AbstractEngine;
import engine.impl.ParallelEngine;
import game.players.PlayerRepository;

/**
 * Created with eclipse 11/03/2015 10:40:01 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class World {

	private final List<EntityRepository<?>> repositories = new ArrayList<>();
	private final AbstractEngine<?> pool = new ParallelEngine();
	
	public World() {
		this.repositories.add(new PlayerRepository());
		this.pool.onCreate();
	}
	
	public EntityRepository<?> getRepository(final Class<?> clasz) {
		for (EntityRepository<?> repository : repositories) {
			if (repository.getType().equals(clasz)) 
				return repository;
		}
		return null;
	}
	
	public List<EntityRepository<?>> getRepositories() {
		return repositories;
	}
	
	public AbstractEngine<?> getPool() {
		return pool;
	}
}
