package engine.interfaces;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created with eclipse 13/03/2015 11:39:52 p. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public abstract class Task implements Runnable {
	
	private int cycles;
	private int lastCycle;
	private boolean cancelled;
	
	public Task(final int cycles) {
		this.cycles = cycles;
	}

	/**
	 * Warning, this method should only be invoked by the engines
	 * @param cycle	the engine cycles
	 * @return	can we execute this task? if so, set our last cycle to the atomic integer
	 */
	public boolean canExecute(final AtomicInteger cycle) {
		final boolean can = cycles >= (cycle.get() - lastCycle);
		if (can)
			lastCycle = cycle.get();
		
		return can;
	}
	
	public void setCancelled(final boolean state) {
		this.cancelled = state;
	}
	
	public void cancel() {
		setCancelled(true);
	}
	
	public boolean isCancelled() {
		return cancelled;
	}
}
