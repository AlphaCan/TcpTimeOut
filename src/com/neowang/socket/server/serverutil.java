package com.neowang.socket.server;



public class serverutil implements Runnable  {

	tcpserver clientsocket;
	
	public serverutil(tcpserver client) {
		clientsocket = client;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		while (true) {
			
			clientsocket.get_tcp_data();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}		
	}

}
