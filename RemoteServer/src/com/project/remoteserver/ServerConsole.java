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
						try {
						int mouse_x = Integer.parseInt(data[1]);
						int mouse_y = Integer.parseInt(data[2]);
						System.out.println(mouse_x+" |||" +mouse_y);
						System.out.println();
							inpuItEvents.mouseMove(mouse_x,mouse_y);
						}catch(Exception e){
							e.printStackTrace();
						}
						break;
						
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








	/*		Socket clientSocket = null;
		ServerSocket serverSocket = null;
		int count =0 ;
		try{
			robot= new Robot();
			inpuItEvents=new inpuItEvents(robot);

			serverSocket = new ServerSocket(8081);
		      serverSocket.setSoTimeout(10000);
				System.out.println("WATING FOR CLIENT ");
				clientSocket = serverSocket.accept();
				System.out.println("CLIENT FOUND");
			System.out.println("server started....");
			while (true){
				System.out.println("count :" + count++);
				System.out.println("_________________");

				//	BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputS­tream()));
				Scanner in1 = new Scanner(clientSocket.getInputStream());
				System.out.println("new scanner");
				String mes; 


				int innerCount = 0;
				while(in1.hasNext()){
					System.out.println("INNERCOUNT :" + innerCount++);
					System.out.println("_________________");
					System.out.println("enter while");
					mes=in1.nextLine();
					System.out.println("get msg");
					String data[]=mes.split(",");
					System.out.println("Client message :"+mes);

					System.out.println("before if");
					if (data[0].equals("1"))//&& data.length==2)
					{        							
						System.out.println("inside if");
						inpuItEvents.keyPress(Integer.parseInt(data[1]));
					};
					System.out.println("outside if");


				}
				in1.close();
			}
		}catch(Exception e){} //read & display the message		
	}*/


