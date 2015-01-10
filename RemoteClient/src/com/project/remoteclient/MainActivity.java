package com.project.remoteclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.project.remoteprotocol.Buttons;
import com.project.remoteprotocol.Events;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Socket client;
	private PrintWriter printwriter;
	
	private TextView lblinf;
	private EditText ip,message;
	private Button previous,next,home,end;
	private String sendMessage;
	private int sendPort=8081;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ip= (EditText) findViewById(R.id.txtIP);
		
		//message= (EditText) findViewById(R.id.txtMessage);
		//send = (Button) findViewById(R.id.btnSend);
		previous= (Button) findViewById(R.id.btnPrevious);
		next= (Button) findViewById(R.id.btnNext);
		home= (Button) findViewById(R.id.btnHome);
		end= (Button) findViewById(R.id.btnEnd);
		previous.setOnClickListener(oclBtns);
		next.setOnClickListener(oclBtns);
		end.setOnClickListener(oclBtns);
		home.setOnClickListener(oclBtns);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	///////////////////////////////
	public void send( final String a){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					client=new Socket(ip.getText().toString(),sendPort);
					printwriter=new PrintWriter(client.getOutputStream());
					printwriter.write(a);
					printwriter.flush();
					printwriter.close();
					client.close();
					
				}
				
				catch (IOException e){
					
					e.printStackTrace();
				}
				
			}
		}).start();
	}
	
	
	
	 OnClickListener oclBtns = new OnClickListener() {
	       @Override
	       public void onClick(View v) {
	         // TODO Auto-generated method stub
	    	   switch( v.getId())
	   		{
	   		case R.id.btnEnd:
	   			send(Events.PowerPoint +","+Buttons.KEY_END);
	   		case R.id.btnHome:
	   			send(Events.PowerPoint +","+Buttons.KEY_HOME);
	   		case R.id.btnPrevious:
	   			send(Events.PowerPoint +","+Buttons.KEY_PREVIOUS);
	   		case R.id.btnNext:
	   			send(Events.PowerPoint +","+Buttons.KEY_NEXT);
	   		
	   		}
	       }
	     };
	
}
