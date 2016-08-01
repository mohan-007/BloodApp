package com.example.tgmc;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.tgmc.Register.CreateNewProduct;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReportSpam extends Activity implements ValidationListener{
	class blockdonar extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(ReportSpam.this);
			pDialog.setMessage("blocking him..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);			pDialog.show();	}
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String namepar = name.getText().toString();
			String addrpar= password.getText().toString();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("namepar", namepar));
			params.add(new BasicNameValuePair("passwordpar",addrpar));
			JSONObject json = jsonParser.makeHttpRequest(url_create_product,
					"POST", params);
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Intent main=new Intent(getApplicationContext(),MainActivity.class);
					startActivity(main);
				} else {
					Intent main=new Intent(getApplicationContext(),Register.class);
					startActivity(main);
				}
			} catch (JSONException e) {
				e.printStackTrace();
				
			}
			finish();
			return null;
		}
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			finish();
			pDialog.dismiss();
			
		}
		
	}
	
	
	
	
	
	
	
	
	Button reportbtn,home;
	String flag;
	
	private ProgressDialog pDialog;
	Validator validator;
	JSONParser jsonParser = new JSONParser();
	private static String url_create_product = "http://10.0.2.2:80/sample/mohan/tgmcphp/block_donar.php";
	@Required(order = 1)
	@TextRule(order = 2, minLength = 3, message = "Enter name of the spam.")
	EditText name;
	@Required(order = 1)
	@TextRule(order = 2, minLength = 3, message = "Enter phone number of the spam")
	EditText password;
	private static final String TAG_SUCCESS = "success";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_spam);
		reportbtn=(Button)findViewById(R.id.button1);
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
		validator = new Validator(this);
  		validator.setValidationListener(this);
		reportbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			validator.validate();
			if(flag.equals("1"))
			{
				new blockdonar().execute();
			}}
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
