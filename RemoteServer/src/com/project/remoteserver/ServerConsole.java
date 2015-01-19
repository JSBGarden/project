package com.project.remoteserver;

import java.awt.AWTError;
import java.awt.Event;
import java.awt.Robot;
import java.awt.event.AWTEventListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.project.remoteprotocol.global.Events;




public class ServerConsole    {

	static Robot robot=null;
	static InputEvents inpuItEvents=null;
	public static void main(String[] args) throws IOException {
		    
		try{
			robot= new Robot();
			inpuItEvents=new InputEvents(robot);


		ServerSocket listener= new ServerSocket(8081);
		//listener.setSoTimeout(10000);
		System.out.println("WATING FOR CLIENT ");

		try{
			while(true){
				new ClientDealer(listener.accept()).start();				
			}

		}
		finally{
			listener.close();
		}
	}		
		catch(Exception e){}
		
	}

	private static class ClientDealer extends Thread {
		private Socket socket;

		public ClientDealer(Socket socket){
			this.socket=socket;			 
		}			


		public void run() {
			try{
				System.out.println("client found");
				BufferedReader in =new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				System.out.println("start");
				while (true){
					String input=in.readLine();
					System.out.println(input);
					if (input == null ) {
						break;
					}
					String data[]=input.split(",");
					switch (Integer.parseInt(data[0]))
					{
					case Events.POWER_POINT:					
						inpuItEvents.keyPress(Integer.parseInt(data[1]));
						break;
					case Events.MOUSE_MOVE:						
						int mouse_x = Integer.parseInt(data[1]);
						int mouse_y = Integer.parseInt(data[2]);												
							inpuItEvents.mouseMove(mouse_x,mouse_y);						
						break;
					case Events.MOUSE_CLICK:
						try{
						int mouse_button=Integer.parseInt(data[1]);
						inpuItEvents.mouseClick(mouse_button);
						}
						catch (Exception e){e.printStackTrace();}
					};
							
					
					

				}
			} catch(Exception e){

			} finally{
				try {
					
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

