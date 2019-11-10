package com.neowang.socket.control;


import com.neowang.socket.server.tcpserver;
import com.neowang.time.delay.delay;
import com.neowang.time.delay.time;


public class task extends Thread{
	
	tcpserver socket;
	int length = 0;
	
	public task(tcpserver client) {
		this.socket = client;
	}

	@Override
	public void run() {
		time TIME = new time();
		boolean MB_put_OK = false;
		boolean start_to_test = false;
		boolean IO_Operation = false;
		byte retry_count = 0;
		while (true) {
			
			do {//第一步 检测 MB是否放好
				MB_put_OK = false;
				send_buffer(variable.cmd_ask_MB_put_ok);//询问是否放好MB
				delay.getInstance().set_time_out(TIME, variable.WAITE_TIME_OUT);//设置超时 
				do {					
					String buf = get_buffer();//获取返回数据 
					if (buf.equals("ctla4")) {//得到MB已经放好的命令
						MB_put_OK = true;//flag改变
					}						
				} while ((delay.getInstance().time_is_out(TIME) == false)&&!MB_put_OK);//超时跳出循环，在时间内获得正确回复 跳出循环
				
			} while (!MB_put_OK);//一直在循环中，直到MB正确放置
			
			
			do {//第二步 检测执行按钮是否按下
				start_to_test = false;
				send_buffer(variable.cmd_require_start_to_test);//询问控制盒 执行按钮是否按下
				delay.getInstance().set_time_out(TIME, variable.WAITE_TIME_OUT);//设置超时 
				do {
					String buf = get_buffer();//获取返回数据 
					if (buf.equals("ctla1")) {
						start_to_test = true;
					}
					
				} while ((delay.getInstance().time_is_out(TIME) == false)&&!start_to_test);
				
			} while (!start_to_test);
			
			do {
				IO_Operation = false;
				send_buffer(variable.cmd_Control_Push_MB_Cylinder_IN);//发出MB进入命令
				delay.getInstance().set_time_out(TIME, variable.WAITE_TIME_OUT);//设置超时 
				do {
					String buf = get_buffer();//获取返回数据 
					if (buf.equals("ctlo80")) {
						IO_Operation = true;
					}
					
				} while ((delay.getInstance().time_is_out(TIME) == false)&&!IO_Operation);
				retry_count++;
				if (retry_count >= variable.retry_number) {
					System.out.println("调用失败处理   显示设备故障  请人工确认");
				}
			} while (!IO_Operation);
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
			
			
		}			
	}
	
	
	
	
	
	private void send_buffer(String cmd)
	{
		socket.send_tcp_data(cmd, cmd.length());
	}
	
	private String get_buffer()
	{
		String buf = "";
		if((socket.get_receive_status()&0x8000) == 0x8000)//判断是否接收到数据
		{
			int length = socket.get_receive_status()&0x3fff;//收到的数据长度
			if (length > 0) {
				
				byte[] packet = socket.get_tcp_receive_buffer();//获取收到的字节数据
				buf = new String(packet,0,length);//转成字符串
			}
			socket.clear_tcp_receive_buffer();//清空缓冲区
			socket.set_receive_status(0);//清空状态
		}
		return buf;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
