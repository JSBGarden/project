package com.project.remoteserver;

import java.awt.AWTError;
import java.awt.Robot;
import java.awt.event.AWTEventListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerConsole    {


	public static void main(String[] args) throws IOException {
		Robot robot=null;
		KeybordEvents keybordEvents=null;    

		Socket clientSocket = null;
		ServerSocket serverSocket = null;
		try{
			robot= new Robot();
			keybordEvents=new KeybordEvents(robot);

			serverSocket = new ServerSocket(8081);
			System.out.println("server started....");
			while (true){ 
				System.out.println("WATING FOR CLIENT ");
				clientSocket = serverSocket.accept();
				System.out.println("CLIENT FOUND");

				//	BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputS­tream()));
				Scanner in1 = new Scanner(clientSocket.getInputStream());
				
				String mes; 

				while(in1.hasNext()){

					mes=in1.nextLine();
					String data[]=mes.split(",");
					System.out.println("Client message :"+mes);
					
					if (data[0].equals("1"))//&& data.length==2)
					{        							
						keybordEvents.keyPress(Integer.parseInt(data[1]));
					};
					/*if (mes.contentEquals("v")){
            MyServer.direction(KeyEvent.VK_DOWN);
        }
        else if (mes.contentEquals("^")){
            MyServer.direction(KeyEvent.VK_UP);
        }
        else if (mes.contentEquals("<")){
            MyServer.direction(KeyEvent.VK_LEFT);
        }
        else if (mes.contentEquals(">")){
            MyServer.direction(KeyEvent.VK_RIGHT);
        }*/

				}
				in1.close();
			}
		}catch(Exception e){} //read & display the message		
	}

}
