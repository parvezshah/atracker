package com.thread;

class MyObject {

	public synchronized void m2() {
		for(int i=0;i<100000;i++)
		System.out.println("Inside M2");
	}

	public  void m3() {
		for(int i=0;i<100000;i++)
		System.out.println("Inside M3");
		
	}

}

public class TestThread {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		MyObject obj = new MyObject();

		new Thread(new T1(obj)).start();
		//Thread.sleep(1000);
		new Thread(new T2(obj)).start();

	}
}

class T1 implements Runnable {

	private MyObject obj;

	public T1(MyObject obj) {
		this.obj = obj;
	}

	@Override
	public void run() {

		obj.m2();
	}

}

class T2 implements Runnable {

	private MyObject obj;

	public T2(MyObject obj) {
		this.obj = obj;
	}

	@Override
	public void run() {

		obj.m3();
	}

}
