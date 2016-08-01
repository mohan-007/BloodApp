package com.example.tgmc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AllProductsActivity extends ListActivity {

	// Progress Dialog
	private ProgressDialog pDialog;
int success;
	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();
	

	ArrayList<HashMap<String, String>> productsList;

	// url to get all products list
	private static String url_all_products = "http://10.0.2.2:80/sample/mohan/tgmcphp/get_donars.php";
    
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
	private static final String TAG_PIN = "Pincode";	
	private static final String TAG_FAILURE = "No donors found";
	private static final int RQS_PICK_CONTACT = 1;	
	String number;
	 String selectedFromList;
	 SmsManager sms = SmsManager.getDefault();



	// products JSONArray
	JSONArray products = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_products);

	      
		// Hashmap for ListView
		productsList = new ArrayList<HashMap<String, String>>();

		// Loading products in Background Thread
		new LoadAllProducts().execute();

		// Get listview
		final ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {
			  public void onItemClick(AdapterView<?> myAdapter, View myView, int pos, long mylng) {
			    selectedFromList =(lv.getItemAtPosition(pos).toString());
			    // this is your selected item

			    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			    intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
			    startActivityForResult(intent, 1);             

			   // Toast.makeText(getApplicationContext(), selectedFromList, Toast.LENGTH_LONG).show();
			  }                 
			});
		
	}
		
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// if result code 100
	
			// if result code 100 is received 
			// means user edited/deleted product
			// reload this screen again
			if(requestCode == RQS_PICK_CONTACT){
				   if(resultCode ==RESULT_OK) 
					   {
				    Uri contactData = data.getData();
				    Cursor cursor =  managedQuery(contactData, null, null, null, null);
				    cursor.moveToFirst();

				      number =       cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));

				   
				    //  contactNumber.setText(number);
				      //contactEmail.setText(email);
				      
				       sms.sendTextMessage(number, null, selectedFromList, null, null);
				      Toast.makeText(getApplicationContext(), "message send to"+number, Toast.LENGTH_LONG).show();
				     }
				     }
		}

	

	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadAllProducts extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(AllProductsActivity.this);
			pDialog.setMessage("Loading products. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
			

		}

		/**
		 * getting All products from url
		 * */
		protected String doInBackground(String... args) {
			
			
			Bundle n=getIntent().getExtras();
			String distr=n.getString("name");
			String group=n.getString("group");
			List<NameValuePair> params2 = new ArrayList<NameValuePair>();
			params2.add(new BasicNameValuePair("name", distr));
			params2.add(new BasicNameValuePair("group",group));
			JSONObject json2 = jParser.makeHttpRequest(url_all_products,
					"POST", params2);
			
			
			
			
			// Building Parameters
					List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_products,"GET", params);
			
			// Check your log cat for JSON reponse
			Log.d("All Products: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				 success = json.getInt(TAG_SUCCESS);
                
				
				
					// products found
					// Getting Array of Products
					products = json.getJSONArray(TAG_PRODUCTS);

					// looping through All Products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						// Storing each json item in variable
						String id = c.getString(TAG_PID);
						String name = c.getString(TAG_NAME);
						String addr = c.getString(TAG_ADDR);
						String age = c.getString(TAG_AGE);
						String phone = c.getString(TAG_PHONE);
						String dist = c.getString(TAG_DIST);
						String last = c.getString(TAG_LAST);
						String bloodgrp = c.getString(TAG_BLOOD);
						String gender = c.getString(TAG_GENDER);
						String pin=c.getString(TAG_PIN);
						

						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_PID, id);
						map.put(TAG_NAME, name);
						map.put(TAG_ADDR, addr);
						map.put(TAG_AGE, age);
						map.put(TAG_PHONE, phone);
						map.put(TAG_DIST, dist);
						map.put(TAG_LAST, last);
						map.put(TAG_BLOOD, bloodgrp);
						map.put(TAG_GENDER, gender);
						map.put(TAG_PIN, pin);
						
						

						// adding HashList to ArrayList
						productsList.add(map);
						
					}
				
				
					// products found
					// Getting Array of Products
								

				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
				if(success==1)
					{ListAdapter adapter = new SimpleAdapter(
							AllProductsActivity.this, productsList,
							R.layout.list_item, new String[] { TAG_PID,
									TAG_NAME,TAG_ADDR,TAG_AGE,TAG_PHONE,TAG_DIST,TAG_LAST,TAG_PIN,TAG_BLOOD,TAG_GENDER},
							new int[] { R.id.pid, R.id.name,+R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5 ,R.id.textView6});
					// updating listview
					setListAdapter(adapter);
					}
				
				if(success==0)
					{ListAdapter adapter = new SimpleAdapter(
							AllProductsActivity.this, productsList,
							R.layout.list_item, new String[] { "",
									TAG_NAME,"","","","","","","",""},
							new int[] { R.id.pid, R.id.name,R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5 ,R.id.textView6});
					// updating listview
					
				
					setListAdapter(adapter);
				
					}
				
			
					
				}
			});

		
			
			
		
		}
			

	}
}