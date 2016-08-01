package com.example.tgmc;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Regex;
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
import android.widget.TextView;
import android.widget.Toast;

public class updatedetails extends Activity implements ValidationListener{
	class CreateNewProduct extends AsyncTask<String, String, String> {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	
		String lastdate,phoneno,curaddress;
		Bundle n;
		String pid;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(updatedetails.this);
			pDialog.setMessage("User..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);			pDialog.show();	
			
			 n=getIntent().getExtras();			
			 pid=n.getString("id");
			 
			 Toast.makeText(getApplicationContext(), pid,Toast.LENGTH_LONG).show();
		}
		
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			 lastdate=last.getText().toString();
			phoneno=phone.getText().toString();
			curaddress=address.getText().toString();
			
			Log.d("pasrsed", "msg");
			
			
			params.add(new BasicNameValuePair("lastdate", lastdate));
			params.add(new BasicNameValuePair("phoneno", phoneno));
			params.add(new BasicNameValuePair("curaddress", curaddress));
			params.add(new BasicNameValuePair("pid",pid));
			JSONObject json = jsonParser.makeHttpRequest(url_update_product,
					"POST", params);
			Log.d("pasrsed", "msg");
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					finish();
					finish();
					
				} else {
					
				}
			} catch (JSONException e) {
				e.printStackTrace();
				
			}
			//finish();
			return null;
		}
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			//finish();
			pDialog.dismiss();
			
		}
		
	}
	
	
	
	@Regex(order = 7 ,pattern="(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)",message="Enter a valid date")
	EditText last;
	@TextRule(order = 6, minLength = 10, maxLength=10,message = "Enter a phone number.")
	EditText phone;
	@TextRule(order = 3, minLength = 15, message = "Enter complete address address.")
	EditText address;
	Button update;
	Button report;
	Button home;
	String flag;
	Validator validator;
	
	

	/**
	 * @param args
	 */

	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	private static String url_update_product = "http://10.0.2.2:80/sample/mohan/tgmcphp/update.php";

	private static final String TAG_SUCCESS = "success";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updatelast);
          last=(EditText)findViewById(R.id.editText1);
          phone=(EditText)findViewById(R.id.editText2);
          address=(EditText)findViewById(R.id.editText3);
          update=(Button)findViewById(R.id.button1);
          report=(Button)findViewById(R.id.button2);
          validator = new Validator(this);
          home=(Button)findViewById(R.id.button3);
	home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent main=new Intent(getApplicationContext(),MainActivity.class);
			startActivity(main);
			}
		});
  		validator.setValidationListener(this);
         
          
          update.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				validator.validate();
				if(flag.equals("1"))
				{
			new CreateNewProduct().execute();
				
				}	
				
			}
		});
         report.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				Intent i=new Intent(getApplicationContext(),ReportSpam.class);
				startActivity(i);
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
	}}
