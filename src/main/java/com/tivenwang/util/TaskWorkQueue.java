package com.tivenwang.util;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

/**
 * @author Pecan 类说明
 * 自定义线程池
 */
public class TaskWorkQueue {
	private static final int nThreads=50;
	private static PoolWorker[] threads=null;
	private static ConcurrentLinkedQueue<Runnable> queue= new ConcurrentLinkedQueue<Runnable>();
	private static Logger logger=Logger.getLogger(TaskWorkQueue.class);
	static{
		new TaskWorkQueue();
	}
	
	public TaskWorkQueue(){
		threads = new PoolWorker[nThreads];
		for (int i = 0; i < nThreads; i++) {
			threads[i]=new PoolWorker();
			threads[i].start();
		}
	}
	
	public static void execute(Runnable runnable) {
		synchronized (queue) {
			queue.add(runnable);
			queue.notify();
		}
	}

	private class PoolWorker extends Thread {
		public void run() {
			Runnable r;
			while (true) {
				synchronized (queue) {
					while (queue.isEmpty()) {
						try {
							queue.wait();
						} catch (InterruptedException ignored) {
							ignored.printStackTrace();
							logger.error(ignored);
						}
					}
					r = (Runnable) queue.poll();
				}
				// If we don't catch RuntimeException,
				// the pool could leak threads
				try {
					r.run();
				} catch (RuntimeException e) {
					// You might want to log something here
					e.printStackTrace();
					logger.error("",e);
				}
			}
		}
	}
	
	public static void shutDownTask(){
		for (Runnable thread: queue) {
			Thread thread2=new Thread(thread);
			if (!thread2.isInterrupted()) {
				logger.info(thread2+"已经停止！");
				thread2.interrupt();
			}
		}
	}
}
