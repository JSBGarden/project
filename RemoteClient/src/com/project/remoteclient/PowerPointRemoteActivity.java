package com.project.remoteclient;

import com.project.remoteclient.process.ClientSocket;
import com.project.remoteprotocol.global.Buttons;
import com.project.remoteprotocol.global.Events;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PowerPointRemoteActivity extends Activity{
	
	
	ClientSocket client ;
	public final int port=8081;

	Button connect;
	ImageButton previous, next, home,exit,fullScreen,toggleBlackScreen,ok;
	EditText Ipaddress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);				
		setContentView(R.layout.power_point_remote);
		
		
		client=new ClientSocket();
		
		previous=(ImageButton) findViewById(R.id.ibtnPrevious);
		next=(ImageButton) findViewById(R.id.ibtnNext);
		home=(ImageButton) findViewById(R.id.ibtnHome);
		exit=(ImageButton) findViewById(R.id.ibtnExit);
		fullScreen=(ImageButton) findViewById(R.id.ibtnFullScreen);
		toggleBlackScreen=(ImageButton) findViewById(R.id.ibtnToggleBlack);
		ok=(ImageButton) findViewById(R.id.ibtnOk);
		connect=(Button)findViewById(R.id.btnConnection);
		Ipaddress=(EditText)findViewById(R.id.txtIp);
		connect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				client.connect(Ipaddress.getText().toString(), port);		
			}
		});
		previous.setOnClickListener(oclBtns);
		next.setOnClickListener(oclBtns);
		home.setOnClickListener(oclBtns);
		exit.setOnClickListener(oclBtns);
		fullScreen.setOnClickListener(oclBtns);
		toggleBlackScreen.setOnClickListener(oclBtns);
		ok.setOnClickListener(oclBtns);
		
		
	}
	
	
	
	OnClickListener oclBtns = new OnClickListener() {
	       @Override
	       public void onClick(View v) {	         
	    	   switch( v.getId())
	   		{
	   		case R.id.ibtnExit:
	   			client.send(Events.POWER_POINT +","+Buttons.KEY_END);
	   			break;
	   		case R.id.ibtnHome:	   			
	   			client.send(Events.POWER_POINT +","+Buttons.KEY_HOME);
	   			break;
	   		case R.id.ibtnPrevious:	   			
	   				client.send(Events.POWER_POINT +","+Buttons.KEY_PREVIOUS);
	   			break;
	   		case R.id.ibtnNext:
	   				client.send(Events.POWER_POINT +","+Buttons.KEY_NEXT);
	   			break;
	   		case R.id.ibtnFullScreen:
	   			client.send(Events.POWER_POINT +","+Buttons.KEY_FULL_SCREEN);
	   			break;
	   		case R.id.ibtnToggleBlack:
	   			client.send(Events.POWER_POINT +","+Buttons.KEY_TOGGLE_BLACK);
	   			break;
	   		}
	       }
	     };

}
