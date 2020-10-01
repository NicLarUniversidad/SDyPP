package com.ar.model.utils.chronometer.versionAlfa;

public class Chronometer{

	private long timer;
	private long count;
	private boolean running;
	
	protected long getTimer() {
		return timer;
	}

	protected void setTimer(long timer) {
		this.timer = timer;
	}

	protected long getCount() {
		return count;
	}

	protected void setCount(long count) {
		this.count = count;
	}

	protected boolean isRunning() {
		return running;
	}

	protected void setRunning(boolean running) {
		this.running = running;
	}

	public Chronometer() {
		super();
		this.timer = System.currentTimeMillis();
		this.count = 0;
		this.running = false;
	}

	public void reset() {
		this.count = 0;
	}

	public void start() {
		this.timer = System.currentTimeMillis();
		this.running = true;
	}

	public void stop() {
		if (!this.running) {
			
		}
		else {
			this.count = System.currentTimeMillis() - this.timer;
			this.running = false;
		}
	}

	public String getTime() {
		return Long.toString(this.count) + " milisegundos";
	}

}
