package engine;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import engine.interfaces.Task;


/**
 * Created with eclipse 21/03/2015 9:54:50 a. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public abstract class AbstractEngine<T> implements EngineInterface, Runnable {

	protected final List<Task> upcomingTasks = Collections.synchronizedList(new LinkedList<>());
	protected final List<Task> runtimeTasks = new LinkedList<>();
	protected AtomicInteger cycleCount = new AtomicInteger();
	protected T executor;
	
	@Override
	public void run() {
		int cycleRate = Constants.CYCLE_RATE.getValue();
		for (;;) {
			final long start = System.currentTimeMillis();
			synchronized(upcomingTasks) {
				final Iterator<Task> iterator = upcomingTasks.iterator();
				while (iterator.hasNext()) {
					final Task task = iterator.next();
					runtimeTasks.add(task);
					iterator.remove();
				}
			}
			
			final Iterator<Task> iterator = runtimeTasks.iterator();
			while (iterator.hasNext()) {
				final Task task = iterator.next();
				synchronized (task) {
					if (task.isCancelled()) {
						iterator.remove();
					}

					if (task.canExecute(cycleCount)) {
						execute(task);
					}
				}
			}
			
			cycleCount.incrementAndGet();

			final long end = System.currentTimeMillis();
			final long difference = cycleRate - (end - start);
			
			if (difference >= 0) {
				System.out.println("SLEEPING: " + difference);
				try {
					TimeUnit.MILLISECONDS.sleep(difference);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("The server is being overlapped by " + Math.abs(difference) + ".");
			}
		}
	}
	
	@Override
	public boolean add(Task task) {
		return upcomingTasks.add(task);
	}
}
