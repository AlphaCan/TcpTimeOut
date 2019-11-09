package com.neowang.socket.control;


import com.neowang.socket.server.tcpserver;

public class task extends Thread{
	
	tcpserver socket;
	
	public task(tcpserver client) {
		this.socket = client;
	}

	@Override
	public void run() {
		while (true) {
			if ((socket.get_receive_status()&0x8000) == 0x8000) {
				
				int length = socket.get_receive_status()&0x3fff;
				if(length > 0) {
					System.out.println(new String(socket.get_tcp_receive_buffer(),0,length));
				}
				socket.set_receive_status(0);
				socket.clear_tcp_receive_buffer();
			}
			
			
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
}
