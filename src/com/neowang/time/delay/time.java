package com.neowang.time.delay;

public class time {
	
	private long stop_time = 0;
	private long current_time = 0;
	
	public void set_stop_time(long time) {
		stop_time = time;
	}
	
	public long get_stop_time() {
		return stop_time;
	}
	
	public void set_current_time(long time) {
		current_time = time;
	}
	
	public long get_current_time() {
		return current_time;
	}
	
	
}
