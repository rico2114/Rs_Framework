package engine.impl;

import java.util.concurrent.ExecutorService;

import engine.AbstractEngine;
import engine.interfaces.Task;

/**
 * Created with eclipse 21/03/2015 10:00:00 a. m.
 * @Author Juan Sebastian Quiceno <Juan.2114@hotmail.com>
 */
public class ConcurrentEngine extends AbstractEngine<ExecutorService> {
	
	@Override
	public void execute(Task task) {
		task.run();
	}
	
	@Override
	public Void onCreate() {
		return null;
	}

	@Override
	public Void onStop() {
		return null;
	}
}
