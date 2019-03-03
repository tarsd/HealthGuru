package com.tushar.healthguru;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

public class Enter_Records extends Activity implements OnClickListener , Validator.ValidationListener{
	@NotEmpty
	EditText systolic;
	@NotEmpty
EditText diastolic;
	@NotEmpty
EditText sugar;
EditText recordsdate;
Button b_submit;
String name;
String password;
String mobilenumber;
String namepass;
String bpsystolic;
String bpdiastolic;
String bloodsugar;
String date;
InputStream is=null;
String result=null;
String line=null;
int code;
TextView tvsystolic,tvdiastolic,tvsugar,tvdate,tvtitle;
	private Validator validator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enterrecords);

		validator = new Validator(this);
		validator.setValidationMode(Validator.Mode.BURST);
		validator.setValidationListener(this);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		tvtitle = (TextView)findViewById(R.id.tventer_records);
        Typeface custom_font5 = Typeface.createFromAsset(getAssets(), "fonts/FunnyKid.ttf");
        tvtitle.setTypeface(custom_font5);
		tvsystolic = (TextView)findViewById(R.id.tvsystolic);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/FunnyKid.ttf");
        tvsystolic.setTypeface(custom_font);
        tvdiastolic = (TextView)findViewById(R.id.tvdiastolic);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/FunnyKid.ttf");
        tvdiastolic.setTypeface(custom_font1);
        tvsugar = (TextView)findViewById(R.id.tvsugar);
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(), "fonts/FunnyKid.ttf");
        tvsugar.setTypeface(custom_font2);
       // tvdate = (TextView)findViewById(R.id.tvdate);
        //Typeface custom_font3 = Typeface.createFromAsset(getAssets(), "fonts/FunnyKid.ttf");
        //tvdate.setTypeface(custom_font3);
	    systolic = (EditText)findViewById(R.id.etsystolic);
		diastolic = (EditText)findViewById(R.id.etdiastolic);
		sugar = (EditText)findViewById(R.id.etsugar);
		//recordsdate = (EditText)findViewById(R.id.etdate);
		b_submit = (Button)findViewById(R.id.b_submit_records);
		Bundle getdata = getIntent().getExtras();
	    name = getdata.getString("key_username");
	    password = getdata.getString("key_pass");
	    mobilenumber = getdata.getString("key_mob");
	    namepass=name+"_"+password;
		Toast.makeText(getBaseContext(), "WELCOME " + name,Toast.LENGTH_LONG).show();
		b_submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		validator.validate();

	}
	
	public void insert()
    {
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	bpsystolic = systolic.getText().toString();
    	bpdiastolic = diastolic.getText().toString();
    	bloodsugar = sugar.getText().toString();
//    	date = recordsdate.getText().toString();
   	    nameValuePairs.add(new BasicNameValuePair("namepass",namepass));
   	    nameValuePairs.add(new BasicNameValuePair("mobile", "98788188"));
     	nameValuePairs.add(new BasicNameValuePair("systolic",bpsystolic));
     	nameValuePairs.add(new BasicNameValuePair("diastolic",bpdiastolic));
     	nameValuePairs.add(new BasicNameValuePair("sugar",bloodsugar));
     //	nameValuePairs.add(new BasicNameValuePair("date",date));
    	
    	 try
    	 {
		    HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(Utility.base_url+"inserttabledata.php");
	        //HttpPost httppost = new HttpPost("http://healthguru.lockernerd.co.uk/code/inserttabledata.php");
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

	@Override
	public void onValidationSucceeded() {
		insert();
	}

	@Override
	public void onValidationFailed(List<ValidationError> errors) {
		for (ValidationError error : errors) {
			View view = error.getView();
			String message = error.getCollatedErrorMessage(this);

			// Display error messages ;)
			if (view instanceof EditText) {
				((EditText) view).setError(message);
			} else {
				Toast.makeText(this, message, Toast.LENGTH_LONG).show();
			}

		}
	}
}
