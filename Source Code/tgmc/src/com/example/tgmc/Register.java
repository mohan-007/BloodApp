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
import com.mobsandgeeks.saripaar.annotation.NumberRule;
import com.mobsandgeeks.saripaar.annotation.Regex;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;
import com.mobsandgeeks.saripaar.*;



import android.R.string;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity implements ValidationListener{
	Spinner blood,nown;
	TelephonyManager mngr; 

String flag;
Validator validator;

	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	private static String url_create_product = "http://10.0.2.2:80/sample/mohan/tgmcphp/register_donar.php";
	
	
	@Required(order = 1)
	@TextRule(order = 2, minLength = 3, message = "Enter at least 3 characters.")
	EditText uname;
	
	@TextRule(order = 3, minLength = 15, message = "Enter complete address address.")
	EditText phno;
	
	@TextRule(order = 4, minLength = 3, message = "Enter at least 3 characters.")
	EditText city;

	@TextRule(order = 5, minLength = 6,maxLength=6, message = "Enter at least 6 characters.")
	EditText pinno;
	@TextRule(order = 6, minLength = 10, maxLength=10,message = "Enter a phone number.")
	EditText addr;
	@Regex(order = 7 ,pattern="(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)",message="Enter a valid date")
	EditText age;
	@Regex(order = 9, pattern="(1[8-9]|[2-9]\\d|\\d{3,})",message="Enter your age")
	EditText lastdateblood;
	
	
	
	private static final String TAG_SUCCESS = "success";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
	//	String imei=mngr.getDeviceId();
		String[] spin_array1=new String[2];
		String[] spin_array2=new String[12];
		 
		validator = new Validator(this);
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
		spin_array1[0]="Male";
		spin_array1[1]="Female";
		blood=(Spinner)findViewById(R.id.spinner1);
		nown=(Spinner)findViewById(R.id.spinner2);
		pinno=(EditText)findViewById(R.id.editText7);
		
		SpinnerAdapter adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spin_array2);
	blood.setAdapter(adapter1);
	SpinnerAdapter adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spin_array1);
	nown.setAdapter(adapter2);

	uname=(EditText)findViewById(R.id.editText1);
	addr=(EditText)findViewById(R.id.editText2);
	phno=(EditText)findViewById(R.id.editText3);
	age=(EditText)findViewById(R.id.editText4);
	lastdateblood=(EditText)findViewById(R.id.editText5);
	city=(EditText)findViewById(R.id.editText6);
	Button registerbtn;
	registerbtn=(Button)findViewById(R.id.button1);
	registerbtn.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		String a="set";
		 validator.validate();
		if(flag.equals("1"))
		{
		 new CreateNewProduct().execute();
		}
		
		
		}});
		}
	class CreateNewProduct extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Register.this);
			pDialog.setMessage("User..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);			pDialog.show();	}
			protected String doInBackground(String... args) {
			String namepar = uname.getText().toString();
			String addrpar= phno.getText().toString();
			String distpar=city.getText().toString();
			String phonepar=addr.getText().toString();
			String agepar=lastdateblood.getText().toString();
			String bloodpar=blood.getSelectedItem().toString();
			String genderpar=nown.getSelectedItem().toString();
			String lastdatebloodpar=age.getText().toString();
			String pin=pinno.getText().toString();
			String imeino=mngr.getDeviceId();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("namepar", namepar));
			params.add(new BasicNameValuePair("addrpar",addrpar));
			params.add(new BasicNameValuePair("distpar",distpar));
			params.add(new BasicNameValuePair("pinno", pin));
			params.add(new BasicNameValuePair("phonepar",phonepar));
			params.add(new BasicNameValuePair("agepar",agepar));
			params.add(new BasicNameValuePair("bloodpar",bloodpar));
			params.add(new BasicNameValuePair("genderpar",genderpar));
			params.add(new BasicNameValuePair("lastdatebloodpar",lastdatebloodpar));
			params.add(new BasicNameValuePair("imei",imeino));
			Log.i("assiggnedddddddddddddd", lastdatebloodpar);
			JSONObject json = jsonParser.makeHttpRequest(url_create_product,
					"POST", params);
			
			Log.d("Create Response", json.toString());
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
