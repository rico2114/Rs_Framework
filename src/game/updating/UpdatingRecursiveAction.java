package game.updating;

import engine.interfaces.Task;

/**
 * Created with eclipse 13/03/2015 11:22:32 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public abstract class UpdatingRecursiveAction extends Task  {

	private static final int UPDATING_CYCLES = 1;
	
	public UpdatingRecursiveAction() {
		super(UPDATING_CYCLES);
	}

}
