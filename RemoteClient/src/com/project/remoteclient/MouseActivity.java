package com.project.remoteclient;


import com.project.remoteclient.process.ClientSocket;
import com.project.remoteclient.process.MouseClientProcess;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import com.project.remoteprotocol.global.Events;
public class MouseActivity extends Activity {
	ClientSocket client;
	MouseClientProcess mouseCleintProcess;
	Button btn,btnConnect;
	EditText IP;	  
	  int port =8081;
	  
	//float x,y,x_last,y_last;
	  
	 
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mouse);
		client=new ClientSocket();
		mouseCleintProcess= new MouseClientProcess();
		IP=(EditText)findViewById(R.id.txtTESTIP);
		
		btn=(Button) findViewById(R.id.btnmouse);
		btnConnect=(Button) findViewById(R.id.btnTest1);
		btnConnect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				client.connect(IP.getText().toString(), port);			
				
			}
		});
		
		
		
		btn.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent motionEvent) {
				int action = (motionEvent.getAction() & MotionEvent.ACTION_MASK);
				 
		
		
		    	 				    	 

			    
				mouseCleintProcess.updateCoordinate( motionEvent.getX(),  motionEvent.getY());
				     switch (action){				
				     case MotionEvent.ACTION_MOVE:								    
				     case MotionEvent.ACTION_UP:								    
				     case MotionEvent.ACTION_POINTER_UP:								    
				     case MotionEvent.ACTION_CANCEL:		
				    	 
				    	 if (mouseCleintProcess.shouldDataBeSend())				
				    	 client.send(Events.MOUSE_MOVE +","+Integer.toString(mouseCleintProcess.getX_difference()) + ","+
				    			 		  Integer.toString(mouseCleintProcess.getY_difference()));				    	 
				    	 
				    	 
				      break;
				     
				     }
				return false;
			}
		});
		
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
}
