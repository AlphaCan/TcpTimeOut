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
			
			do {//��һ�� ��� MB�Ƿ�ź�
				MB_put_OK = false;
				send_buffer(variable.cmd_ask_MB_put_ok);//ѯ���Ƿ�ź�MB
				delay.getInstance().set_time_out(TIME, variable.WAITE_TIME_OUT);//���ó�ʱ 
				do {					
					String buf = get_buffer();//��ȡ�������� 
					if (buf.equals("ctla4")) {//�õ�MB�Ѿ��źõ�����
						MB_put_OK = true;//flag�ı�
					}						
				} while ((delay.getInstance().time_is_out(TIME) == false)&&!MB_put_OK);//��ʱ����ѭ������ʱ���ڻ����ȷ�ظ� ����ѭ��
				
			} while (!MB_put_OK);//һֱ��ѭ���У�ֱ��MB��ȷ����
			
			
			do {//�ڶ��� ���ִ�а�ť�Ƿ���
				start_to_test = false;
				send_buffer(variable.cmd_require_start_to_test);//ѯ�ʿ��ƺ� ִ�а�ť�Ƿ���
				delay.getInstance().set_time_out(TIME, variable.WAITE_TIME_OUT);//���ó�ʱ 
				do {
					String buf = get_buffer();//��ȡ�������� 
					if (buf.equals("ctla1")) {
						start_to_test = true;
					}
					
				} while ((delay.getInstance().time_is_out(TIME) == false)&&!start_to_test);
				
			} while (!start_to_test);
			
			do {
				IO_Operation = false;
				send_buffer(variable.cmd_Control_Push_MB_Cylinder_IN);//����MB��������
				delay.getInstance().set_time_out(TIME, variable.WAITE_TIME_OUT);//���ó�ʱ 
				do {
					String buf = get_buffer();//��ȡ�������� 
					if (buf.equals("ctlo80")) {
						IO_Operation = true;
					}
					
				} while ((delay.getInstance().time_is_out(TIME) == false)&&!IO_Operation);
				retry_count++;
				if (retry_count >= variable.retry_number) {
					System.out.println("����ʧ�ܴ���   ��ʾ�豸����  ���˹�ȷ��");
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
		if((socket.get_receive_status()&0x8000) == 0x8000)//�ж��Ƿ���յ�����
		{
			int length = socket.get_receive_status()&0x3fff;//�յ������ݳ���
			if (length > 0) {
				
				byte[] packet = socket.get_tcp_receive_buffer();//��ȡ�յ����ֽ�����
				buf = new String(packet,0,length);//ת���ַ���
			}
			socket.clear_tcp_receive_buffer();//��ջ�����
			socket.set_receive_status(0);//���״̬
		}
		return buf;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
