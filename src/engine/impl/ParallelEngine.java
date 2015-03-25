package engine.impl;

import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

import engine.AbstractEngine;
import engine.interfaces.Task;

/**
 * Created with eclipse 21/03/2015 10:18:32 a. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class ParallelEngine extends AbstractEngine<ForkJoinPool> {
	
	@Override
	public void execute(Task task) {
		final ForkJoinTask<?> action = RecursiveAction.adapt(task);
		executor.invoke(action);
	}
	
	@Override
	public Void onCreate() {
		executor = new ForkJoinPool();
		return null;
	}

	@Override
	public Void onStop() {
		if (Objects.nonNull(executor))
			executor.shutdown();
		return null;
	}
}
