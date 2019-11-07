package com.neowang.time.delay;


public class delay {
	private static final delay instance = new delay();
	
	public static delay getInstance() {
		return instance;
	}
	
	public long get_current_time(){//获取当前系统时间  毫秒
		return System.currentTimeMillis();
	}
	
	public void set_time_out(time t,int timeout) {//设置timeout
		long now = get_current_time();
		t.set_stop_time(timeout + now);
	}
		
	
	public boolean time_is_out(time t) {
		boolean is_not_timeout; //时间没有溢出
		
		is_not_timeout = time_compare(get_current_time(), t.get_stop_time());
		
		return is_not_timeout == false;
	}
	
	public void sleep(int ms) {
		final time time= new time();
		set_time_out(time,ms);
		while(time_is_out(time)==false);
	}
	
	private boolean time_compare(long now,long stop) {//比较时间大小
		if(now < stop)
			return true;
		else 
			return false;		
	}
	
}
