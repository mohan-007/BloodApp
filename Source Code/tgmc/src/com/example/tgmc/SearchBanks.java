package com.example.tgmc;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SearchBanks extends Activity implements ValidationListener {
Button btnsearch;
Button home;
public String detgrp;
Validator validator;
public String detdistr;
	
	String[] spin_array2=new String[12];
	Spinner s1;
	String flag;
	@Required(order = 1)
	@TextRule(order = 2, minLength = 3, message = "Enter the district name properly.")
	EditText District;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchdonar);
		validator=new Validator(this);
		validator.setValidationListener(this);
		spin_array2[0]="A1+ve";
		spin_array2[1]="A+ve";
		spin_array2[2]="B1+ve";
		spin_array2[3]="B+ve";
		spin_array2[4]="AB+ve";
		spin_array2[5]="AB-ve";
		spin_array2[6]="A1-ve";
		spin_array2[7]="A-ve";
		spin_array2[8]="B1-ve";
		spin_array2[9]="B-ve";
		spin_array2[10]="O+ve";
		spin_array2[11]="O-ve";
		District=(EditText)findViewById(R.id.editText1);
		
		s1=(Spinner)findViewById(R.id.spinner1);
		home=(Button)findViewById(R.id.button2);
	home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent main=new Intent(getApplicationContext(),MainActivity.class);
			startActivity(main);
			}
		});
		
		
		SpinnerAdapter adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spin_array2);
		s1.setAdapter(adapter1);
		btnsearch=(Button)findViewById(R.id.button1);
		btnsearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				validator.validate();
				if(flag.equals("1"))
				{
				String detgrp=s1.getSelectedItem().toString();
	String detdistr=District.getText().toString();
				
				
			Intent k=new Intent(getApplicationContext(),AllBloodBanks.class);
			Bundle n=new Bundle();
			n.putString("name", detdistr);
			n.putString("group", detgrp);
			k.putExtras(n);
			startActivity(k);
				}
				}
		});
		
	
	
	}
	@Override
	public void onValidationFailed(View failedView, Rule<?> failedRule) {
        String message = failedRule.getFailureMessage();

        if (failedView instanceof EditText) {
            failedView.requestFocus();
            ((EditText) failedView).setError(message);
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
        flag="0";
    }

	@Override
	public void onValidationSucceeded() {
		// TODO Auto-generated method stub
		  Toast.makeText(this, "Yay! we got it right!", Toast.LENGTH_SHORT).show();
		flag="1";
	}

}
