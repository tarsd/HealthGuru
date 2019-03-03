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
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class First_Aid extends Activity implements OnClickListener, OnItemSelectedListener{

//EditText et_disease;
//Button b1;
int position;
TextView text;
InputStream is=null;
String result=null;
String line=null;
String medicine;
Spinner spinner;
String[] disease = {"Cuts and Wounds","Abrasions","Headaches","Motion-Sickness","Anaemia","Pain Abdomen","Burns","Bruise","Nasal-Blockade","Common Cold",
		            "Insect Byte","Fever","Dyspepsia","Muscular Pain","Constipation","Tooth Ache","Conjuctivitis","Loose Motion","Acidity","Ear Wax","Sore Throat",
		            "Mouth Ulcer","Joint Pain","Epistaxis","Wasp Bite","Sudden Fall due to SHOCK","Vertigo","Ear Infection(PAIN+DISCHARGE)","Scabies"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_aid);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		//et_disease = (EditText)findViewById(R.id.diseasename);
		//b1 = (Button)findViewById(R.id.button_submit_disease);
		text = (TextView)findViewById(R.id.tvfirstaid);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/alienmarks.TTF");
        text.setTypeface(custom_font);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(First_Aid.this,android.R.layout.simple_spinner_item, disease);
		spinner = (Spinner)findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		//b1.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		//select();
	}

	public void select(String disease)
    {
    	//String disease = et_disease.getText().toString();
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("Disease",disease));
    	
    	try
    	{
		HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(Utility.base_url+"firstaid.php");
	        //HttpPost httppost = new HttpPost("http://healthguru.lockernerd.co.uk/code/firstaid.php");
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
        	medicine=(json_data.getString("Medicine"));
		Toast.makeText(getBaseContext(), "MEDICINE Name : "+medicine,
			Toast.LENGTH_SHORT).show();
    	}
        catch(Exception e)
    	{
        	Log.e("Fail 3", e.toString());
    	}
    }

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		 position = spinner.getSelectedItemPosition();
		 select(disease[position]);
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
