package com.thread;

public class MyThread extends Thread {
	public String data;

	public MyThread(String data) {
		this.data = data;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName());
		System.err.println(data);
	}

	public static void main(String[] args) {
		Thread thread = new MyThread("data");
		thread.start();
	}
}