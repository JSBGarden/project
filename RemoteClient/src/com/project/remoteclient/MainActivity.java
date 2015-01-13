package com.project.remoteclient;


import com.project.remoteprotocol.client.ClientSockets;
import com.project.remoteprotocol.global.Buttons;
import com.project.remoteprotocol.global.Events;

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
	
	private EditText ip;
	private Button previous,next,home,end,connect,disconnect;
	private int sendPort=8081;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ip= (EditText) findViewById(R.id.txtIP);
		previous= (Button) findViewById(R.id.btnPrevious);
		next= (Button) findViewById(R.id.btnNext);
		home= (Button) findViewById(R.id.btnHome);
		end= (Button) findViewById(R.id.btnEnd);
		connect= (Button) findViewById(R.id.btnConnect);
		disconnect=(Button) findViewById(R.id.btnDisconnect);
		
		previous.setOnClickListener(oclBtns);
		next.setOnClickListener(oclBtns);
		end.setOnClickListener(oclBtns);
		home.setOnClickListener(oclBtns);
		connect.setOnClickListener(oclBtns);
		disconnect.setOnClickListener(oclBtns);
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
	
	 OnClickListener oclBtns = new OnClickListener() {
	       @Override
	       public void onClick(View v) {	         
	    	   switch( v.getId())
	   		{
	   		case R.id.btnEnd:	   			
	   			ClientSockets.send(Events.POWER_POINT +","+Buttons.KEY_END);
	   			break;
	   		case R.id.btnHome:	   			
	   			ClientSockets.send(Events.POWER_POINT +","+Buttons.KEY_HOME);
	   			break;
	   		case R.id.btnPrevious:	   			
	   			ClientSockets.send(Events.POWER_POINT +","+Buttons.KEY_PREVIOUS);
	   			break;
	   		case R.id.btnNext:
	   			ClientSockets.send(Events.POWER_POINT +","+Buttons.KEY_NEXT);
	   			break;
	   		case R.id.btnConnect:
	   			ClientSockets.connect(ip.getText().toString(),sendPort);
	   			break;
	   		case R.id.btnDisconnect:
	   			ClientSockets.disconnect();
	   			break;
	   		}
	       }
	     };
	
}
