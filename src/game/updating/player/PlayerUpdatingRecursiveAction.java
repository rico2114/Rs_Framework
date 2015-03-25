package game.updating.player;

import engine.Constants;
import game.players.PlayerRepository;
import game.updating.UpdatingRecursiveAction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

/**
 * Created with eclipse 13/03/2015 11:25:13 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class PlayerUpdatingRecursiveAction extends UpdatingRecursiveAction {

	private static final int MINIMUM_PLAYERS_TO_PROCESS = 200;
	private final PlayerRepository repository;
	
	private final int index;
	private final int startIndex;
	private final int finishIndex;
	
	public PlayerUpdatingRecursiveAction(final int index, final PlayerRepository repository, final int startIndex, final int finishIndex) {
		this.index = index;
		this.repository = repository;
		this.startIndex = startIndex;
		this.finishIndex = finishIndex;
	}
	
	public void computeDirectly() {
		repository.update(index, startIndex, finishIndex);
	}

	@Override
	public void run() {
		int difference = finishIndex - startIndex;
		if (difference <= MINIMUM_PLAYERS_TO_PROCESS) {
			computeDirectly();
			return;
		}
		
		int amount = difference / MINIMUM_PLAYERS_TO_PROCESS;
		
		final List<ForkJoinTask<?>> tasks = new ArrayList<>();
		int maximumPlayers = Constants.MAXIMUM_PLAYERS_SIZE.getValue();
		
		int sIndex = -MINIMUM_PLAYERS_TO_PROCESS;
		for (int i = 0; i < amount; i++) {
			sIndex += MINIMUM_PLAYERS_TO_PROCESS;
			int end = sIndex + MINIMUM_PLAYERS_TO_PROCESS;
			
			if (end > maximumPlayers)
				end = maximumPlayers;
			
			tasks.add(ForkJoinTask.adapt(new PlayerUpdatingRecursiveAction(i, repository, sIndex, end)));
		}
		ForkJoinTask.invokeAll(tasks);
	}
}
