package com.project.remoteprotocol.client;

import java.io.PrintWriter;
import java.net.Socket;

public class ClientSockets {
	private static Socket socket;
	private	static PrintWriter out ;	
	
	
	
	//function to send data to the server
		public static void send(  final String data){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try{
						out.println(data);
						out.flush();
					} catch (Exception e){					
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		
		//function to connect to the server 
		public static void connect(final String ip,final int port){
			new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						socket=new Socket(ip,port);
						out = new PrintWriter(socket.getOutputStream(), true);					
					} catch (Exception e){					
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		//function to disconnect to the server 
		public static void disconnect(){
			new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						out.close();
						socket.close();
					} catch (Exception e){					
						e.printStackTrace();
					}
				}
			}).start();
		}

}
