package com.example.tgmc;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	Button search_donar,search_banks,login,register;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		search_donar=(Button)findViewById(R.id.button1);
		search_banks=(Button)findViewById(R.id.button2);
		login=(Button)findViewById(R.id.button3);
		register=(Button)findViewById(R.id.button4);
		search_banks.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent sb=new Intent(getApplicationContext(),SearchBanks.class);
				startActivity(sb);
			}
		});
        search_donar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
		
			
				Intent sd=new Intent(MainActivity.this,SearchDonars.class);
				startActivity(sd);
				
			}

		}); 	
        search_banks.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent sb=new Intent(MainActivity.this,SearchBanks.class);	
			startActivity(sb);
			}
		});
        register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent reg=new Intent(MainActivity.this,Register.class);	
			startActivity(reg);
			}
		});
        login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent log=new Intent(MainActivity.this,Login.class);	
			startActivity(log);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
