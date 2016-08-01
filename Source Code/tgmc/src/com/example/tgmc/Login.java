package com.example.tgmc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Activity implements ValidationListener {

	class checkuser extends AsyncTask<String, String, String> {
String id;
int success;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Login.this);
			pDialog.setMessage("Logging in. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
			

		}
		
		
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			
			uname=name.getText().toString();
			phno=password.getText().toString();
			List<NameValuePair> params2 = new ArrayList<NameValuePair>();
			params2.add(new BasicNameValuePair("uname", uname));
			params2.add(new BasicNameValuePair("phno", phno));
			Log.d("All Products: ", uname);
			Log.d("All Products: ", phno);
			
			JSONObject json2 = jParser.makeHttpRequest(url_login_products,
					"POST", params2);
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_login_products,"GET", params);
			Log.d("All Products: ", uname);
			Log.d("All Products: ", phno);
	
			int success;
			try {
				
				success = json.getInt(TAG_SUCCESS);
				 success1=Integer.toString(success);
				Log.d("json",success1);
			
			
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			return null;
		}
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			Intent i;
			Bundle a=new Bundle();

//			a=getIntent().getExtras();	
			
			if(success1.equals("0"))
			{
				finish();
			}
			else
			{
				i=new Intent(getApplicationContext(),updatedetails.class);
			a.putString("id", success1);
	
			i.putExtras(a);	
			startActivity(i);
			}}		
			
		
		
		
	}
	
	
	//Button btnsearch;
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	ArrayList<HashMap<String, String>> productsList;

	// url to get all products list
	private static String url_login_products = "http://10.0.2.2:80/sample/mohan/tgmcphp/login.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PRODUCTS = "products";
	private static final String TAG_PID = "ID";
	private static final String TAG_NAME = "tabname";
	private static final String TAG_ADDR = "tabaddr";
	private static final String TAG_DIST = "tabdistr";
	private static final String TAG_PHONE = "tabph";
	private static final String TAG_AGE = "tabage";
	private static final String TAG_BLOOD = "tabbloodgrp";
	private static final String TAG_GENDER = "tabgender";
	private static final String TAG_LAST = "tablast";
	JSONArray products = null;
	String uname,phno;
	String flag;
	Button home;
	String success1;
	@Required(order = 1)
	@TextRule(order = 2, minLength = 3, message = "Enter at least 3 characters.")
	EditText name;
	@Required(order = 1)
	EditText password;
	Validator validator;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		validator = new Validator(this);
		validator.setValidationListener(this);
		 name=(EditText)findViewById(R.id.editText1);
		 password=(EditText)findViewById(R.id.editText2);
		 home=(Button)findViewById(R.id.button2);
			home.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				Intent main=new Intent(getApplicationContext(),MainActivity.class);
				startActivity(main);
				}
			});
		Button login=(Button)findViewById(R.id.button1);
		login.setOnClickListener(new View.OnClickListener() {
			
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				validator.validate();
				if(flag.equals("1"))
				{
		new checkuser().execute();				
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
