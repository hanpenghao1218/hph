package com.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyCallable implements Callable<String> {
	public String data;

	public MyCallable(String data) {
		this.data = data;
	}

	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName());
		System.err.println(data);
		return data+1;
	}

	public static void main(String[] args) throws Exception {
		Callable<String> call = new MyCallable("data");
		FutureTask<String> task = new FutureTask<String>(call);
		new Thread(task, "子线程").start();
		System.out.println(task.get());
	}
}