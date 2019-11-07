package com.neowang.time.delay;


public class delay {
	private static final delay instance = new delay();
	
	public static delay getInstance() {
		return instance;
	}
	
	public long get_current_time(){//��ȡ��ǰϵͳʱ��  ����
		return System.currentTimeMillis();
	}
	
	public void set_time_out(time t,int timeout) {//����timeout
		long now = get_current_time();
		t.set_stop_time(timeout + now);
	}
		
	
	public boolean time_is_out(time t) {
		boolean is_not_timeout; //ʱ��û�����
		
		is_not_timeout = time_compare(get_current_time(), t.get_stop_time());
		
		return is_not_timeout == false;
	}
	
	public void sleep(int ms) {
		final time time= new time();
		set_time_out(time,ms);
		while(time_is_out(time)==false);
	}
	
	private boolean time_compare(long now,long stop) {//�Ƚ�ʱ���С
		if(now < stop)
			return true;
		else 
			return false;		
	}
	
}
