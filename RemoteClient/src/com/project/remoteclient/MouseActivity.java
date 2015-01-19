package com.project.remoteclient;


import com.project.remoteclient.process.ClientSocket;
import com.project.remoteclient.process.MouseClientProcess;







import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.project.remoteprotocol.global.Buttons;
import com.project.remoteprotocol.global.Events;
public class MouseActivity extends Activity {
	ClientSocket client;
	MouseClientProcess mouseClientProcess;
	Button btnLeftClick,btnRightClick;
	ImageButton btnMousepad;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mouse);
	
		client=new ClientSocket();
		mouseClientProcess= new MouseClientProcess();
		btnMousepad=(ImageButton) findViewById(R.id.btnmouse);		
		btnLeftClick=(Button) findViewById(R.id.btnLeftClick);
		btnRightClick=(Button) findViewById(R.id.btnRightClick);
		btnLeftClick.setOnClickListener(oclBtns);
		btnRightClick.setOnClickListener(oclBtns);
		
		btnMousepad.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent motionEvent) {
				int action = (motionEvent.getAction() & MotionEvent.ACTION_MASK);
				 
			mouseClientProcess.updateCoordinate( motionEvent.getX(),  motionEvent.getY());
				     switch (action){				
				     case MotionEvent.ACTION_MOVE:								    
				     case MotionEvent.ACTION_UP:								    
				     case MotionEvent.ACTION_POINTER_UP:								    
				     case MotionEvent.ACTION_CANCEL:		
				    	 
				    	 if (mouseClientProcess.shouldDataBeSend())				
				    	 client.send(Events.MOUSE_MOVE +","+Integer.toString(mouseClientProcess.getX_difference()) + ","+
				    			 		  Integer.toString(mouseClientProcess.getY_difference()));				    	 
				    	 
				    	 
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
	
	OnClickListener oclBtns = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			int mouse_button = 0;
			switch (v.getId())
			{
			case R.id.btnLeftClick:
				mouse_button=Buttons.MOUSE_BUTTON_LEFT;
				break;
			case R.id.btnRightClick:
				mouse_button=Buttons.MOUSE_BUTTON_RIGHT;
				break;
			}
	    	 client.send(Events.MOUSE_CLICK +","+mouse_button);
			
		}
	};

}
