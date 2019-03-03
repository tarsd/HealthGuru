package com.tushar.healthguru;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class Insert_Problematic_Medicines extends Activity implements OnClickListener{

EditText etmedicine;
EditText etproblem;
EditText etdeletemedicine;
Button bsubmit;	
Button bdelete;
String Medicine;
String deleteMedicine;
String Problems;
String name;
InputStream is=null;
String result=null;
String line=null;
String password;
String namepass;
String mobilenumber;
int code;
int code1;
TabHost th;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tableallergy);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		th = (TabHost)findViewById(R.id.tabhost);
		th.setup();
		
		TabSpec specs = th.newTabSpec("tag1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("INSERT");
		th.addTab(specs);
		
		specs = th.newTabSpec("tag2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("DELETE");
		th.addTab(specs);
		etmedicine = (EditText)findViewById(R.id.etmedicine);
		etproblem = (EditText)findViewById(R.id.etproblem);
		etdeletemedicine = (EditText)findViewById(R.id.etdeletemedicine);
		bsubmit = (Button)findViewById(R.id.b_submit_allergy);
		bdelete = (Button)findViewById(R.id.b_delete_allergy);
		
		Bundle getdata = getIntent().getExtras();
	    name = getdata.getString("key_username");
	    password = getdata.getString("key_pass");
	    mobilenumber = getdata.getString("key_mob");
	    namepass=name+"_"+password;
		Toast.makeText(getBaseContext(), "WELCOME " + name,Toast.LENGTH_LONG).show();
		bsubmit.setOnClickListener(this);
		bdelete.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int id = arg0.getId();
		switch(id)
	  {
		case R.id.b_submit_allergy :
		{
			insert();
			break;
		}
		case R.id.b_delete_allergy:
		{
			delete();
		}
	  }
		
		
	}
	
	
	public void insert()
    {
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	Medicine = etmedicine.getText().toString();
		Problems = etproblem.getText().toString();
   	    nameValuePairs.add(new BasicNameValuePair("namepass",namepass));
   	    nameValuePairs.add(new BasicNameValuePair("mobilenumber", mobilenumber));
     	nameValuePairs.add(new BasicNameValuePair("Medicine",Medicine));
     	nameValuePairs.add(new BasicNameValuePair("Problems",Problems));
     	
    	
    	 try
    	 {
		    HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(Utility.base_url+"insertallergy.php");
	        //HttpPost httppost = new HttpPost("http://healthguru.lockernerd.co.uk/code/insertallergy.php");
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost); 
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
	        Log.e("pass 1", "connection success ");
	     } 
         catch(Exception e)
	     {
        	Log.e("Fail 1", e.toString());
	    	Toast.makeText(getApplicationContext(), "Invalid IP Address",
			Toast.LENGTH_LONG).show();
	     }     
    	 try
         {
             BufferedReader reader = new BufferedReader
 			(new InputStreamReader(is,"iso-8859-1"),8);
             StringBuilder sb = new StringBuilder();
             while ((line = reader.readLine()) != null)
 	         {
                 sb.append(line + "\n");
             }
             is.close();
             result = sb.toString();
 	         Log.e("pass 2", "connection success ");
 	     }
         catch(Exception e)
 	     {
             Log.e("Fail 2", e.toString());
 	     }     
        
 	     try
 	     {
             JSONObject json_data = new JSONObject(result);
             code=(json_data.getInt("code"));
 			
             if(code==1)
             {
 		       Toast.makeText(getBaseContext(), "Inserted Successfully",
 			   Toast.LENGTH_SHORT).show();
             }
             else
             {
 		       Toast.makeText(getBaseContext(), "Sorry, Try Again",
 			   Toast.LENGTH_LONG).show();
             }
 	     }
 	     catch(Exception e)
 	     {
             Log.e("Fail 3", e.toString());
 	     }
          
  }
	
	
	public void delete()
    {
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	deleteMedicine = etdeletemedicine.getText().toString();
   	    nameValuePairs.add(new BasicNameValuePair("namepass",namepass));
     	nameValuePairs.add(new BasicNameValuePair("Medicine",deleteMedicine));
     	
     	
    	
    	 try
    	 {
		    HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(Utility.base_url+"deleteallergy.php");
	        //HttpPost httppost = new HttpPost("http://healthguru.lockernerd.co.uk/code/deleteallergy.php");
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost); 
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
	        Log.e("pass 1", "connection success ");
	     } 
         catch(Exception e)
	     {
        	Log.e("Fail 1", e.toString());
	    	Toast.makeText(getApplicationContext(), "Invalid IP Address",
			Toast.LENGTH_LONG).show();
	     }     
    	 try
         {
             BufferedReader reader = new BufferedReader
 			(new InputStreamReader(is,"iso-8859-1"),8);
             StringBuilder sb = new StringBuilder();
             while ((line = reader.readLine()) != null)
 	         {
                 sb.append(line + "\n");
             }
             is.close();
             result = sb.toString();
 	         Log.e("pass 2", "connection success ");
 	     }
         catch(Exception e)
 	     {
             Log.e("Fail 2", e.toString());
 	     }     
        
 	     try
 	     {
             JSONObject json_data = new JSONObject(result);
             code=(json_data.getInt("code"));
             //Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
             if(code==1)
             {
 		       Toast.makeText(getBaseContext(), "Deleted Successfully",
 			   Toast.LENGTH_SHORT).show();
             }
             else
             {
 		       Toast.makeText(getBaseContext(), "Sorry, Try Again"+namepass,Toast.LENGTH_LONG).show();
             }
 	     }
 	     catch(Exception e)
 	     {
             Log.e("Fail 3", e.toString());
 	     }
          
  }
	
	

}
