package com.project.remoteclient;

import java.text.BreakIterator;

import com.project.remoteclient.process.ClientSocket;
import com.project.remoteprotocol.global.Buttons;
import com.project.remoteprotocol.global.Events;

import android.app.Activity;
import android.app.Notification.Action;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Test extends Activity{
	ImageView imgUP,imgDown,imgLeft,imgRight,imgA,imgB,imgC,imgD;
	ClientSocket client;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		client= new ClientSocket ();
		imgA= (ImageView) findViewById(R.id.cbtnA);
		imgB= (ImageView) findViewById(R.id.cbtnB);
		imgC= (ImageView) findViewById(R.id.cbtnC);
		imgD= (ImageView) findViewById(R.id.cbtnD);
		imgUP= (ImageView) findViewById(R.id.cbtnUP);
		imgDown= (ImageView) findViewById(R.id.cbtnDown);
		imgLeft= (ImageView) findViewById(R.id.cbtnLeft);
		imgRight= (ImageView) findViewById(R.id.cbtnRight);
		
		imgA.setOnTouchListener(otl);
		imgB.setOnTouchListener(otl);
		imgC.setOnTouchListener(otl);
		imgD.setOnTouchListener(otl);
		imgUP.setOnTouchListener(otl);
		imgDown.setOnTouchListener(otl);
		imgLeft.setOnTouchListener(otl);
		imgRight.setOnTouchListener(otl);
		
			
		
	}
	OnTouchListener otl=new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			int action = (event.getAction() & MotionEvent.ACTION_MASK);
			
			int button=0;
			switch(v.getId())
			{
			case R.id.cbtnA:
				button=Buttons.KEY_UP;
				break;
			case R.id.cbtnB:
				button=Buttons.KEY_B;
				break;
			case(R.id.cbtnC):
				button=Buttons.KEY_C;
				break;
			case(R.id.cbtnD):
				button=Buttons.KEY_A;
				break;
			case(R.id.cbtnUP):
				button=Buttons.KEY_D;
				break;
			case(R.id.cbtnDown):
				button=Buttons.KEY_DOWN;
				break;
			case(R.id.cbtnLeft):
				button=Buttons.KEY_LEFT;
				break;
			case(R.id.cbtnRight):
				button=Buttons.KEY_RIGHT;
				break;
			}
			
			
			
			switch(action)
			{
			
			
			case MotionEvent.ACTION_UP:
				 client.send(Events.BUTTON_RELEASE+","+button);
				break;
			case MotionEvent.ACTION_DOWN:
				client.send(Events.BUTTON_PRESS+","+button);
				break;
				
			}
			return false;
		}
	};
	
	

}
