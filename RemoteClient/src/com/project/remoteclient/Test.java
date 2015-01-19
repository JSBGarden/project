package com.project.remoteclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Test extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		final TextView a;
		Button eys;
		a=(TextView)findViewById(R.id.textView1);
		Intent get_ip=getIntent();
		String ipget=get_ip.getStringExtra("ip");
		a.setText(ipget);
		eys=(Button)findViewById(R.id.btnVlcPlay);
		eys.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent b=new Intent(Test.this,Menu.class);
				String ipadd=a.getText().toString();
				b.putExtra("ip", ipadd);
				startActivityForResult(b, 6);
				// TODO Auto-generated method stub
				
			}
		});
	}
	

}
