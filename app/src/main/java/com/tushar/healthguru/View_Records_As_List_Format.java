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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class View_Records_As_List_Format extends Activity{
	
	InputStream is = null;
	//String ip = "http://10.0.2.2/user/selectall.php";
	String line = null;
	String result = null;
	String name;
	String password;
	String namepass;
	String mobilenumber;
	String date;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mysqldatabase);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		Bundle getdata = getIntent().getExtras();
	    name = getdata.getString("key_username");
	    password = getdata.getString("key_pass");
	    mobilenumber = getdata.getString("key_mob");
	    namepass=name+"_"+password;
		webserviceCall();
	}
	
	
	private void webserviceCall() 
	{
		try
		{
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("namepass",namepass));
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(Utility.base_url+"mysql.php");
	        //HttpPost httppost = new HttpPost("http://healthguru.lockernerd.co.uk/code/mysql.php");
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost); 
	       // httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();

	        Log.e("log_tag", "connection success ");
	     //   Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_SHORT).show();
	}
	catch(Exception e)
	{
	        Log.e("log_tag", "Error in http connection "+e.toString());
	        Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

	}
	//convert response to string
	try
	{
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null) 
	        {
	                sb.append(line + "\n");
	              //  Toast.makeText(getApplicationContext(), "Input Reading pass", Toast.LENGTH_SHORT).show();
	        }
	        is.close();

	        result=sb.toString();
	}
	catch(Exception e)
	{
	       Log.e("log_tag", "Error converting result "+e.toString());
	    Toast.makeText(getApplicationContext(), " Input reading fail", Toast.LENGTH_SHORT).show();

	}

	//parse json data
	try
	{

	JSONArray jArray = new JSONArray(result);


	String re=jArray.getString(jArray.length()-1);

	// Toast.makeText(getApplicationContext(), "HELLO tushar",Toast.LENGTH_LONG).show();
	TableLayout tv=(TableLayout) findViewById(R.id.table);
	tv.removeAllViewsInLayout();
	  int flag=1;
	for(int i=-1;i<jArray.length()-1;i++)
	        
	        {
	        	 
	              
	              
	              
		            TableRow tr=new TableRow(View_Records_As_List_Format.this);
		            
		            tr.setLayoutParams(new LayoutParams(
		                       LayoutParams.FILL_PARENT,
		                       LayoutParams.WRAP_CONTENT));
		            
		            //Toast.makeText(getApplicationContext(), "HELLO world",Toast.LENGTH_LONG).show();
		            
		            
		         
		            if(flag==1)
		            {
		            	
		            	/*TextView b6=new TextView(Mysql_database.this);
		        	     b6.setText("namepass");
		        	     b6.setTextColor(Color.BLUE);
		        	     b6.setTextSize(15);
		                 tr.addView(b6);
		            
		            	
		                TextView b19=new TextView(Mysql_database.this);
		                 b19.setPadding(10, 0, 0, 0);
		                 b19.setTextSize(15);
		        	     b19.setText("mobile");
		        	     b19.setTextColor(Color.BLUE);
		        	     tr.addView(b19);*/
		                 
		               TextView b29=new TextView(View_Records_As_List_Format.this);
 	         	       b29.setPadding(10, 0, 0, 0);
		        	     b29.setText("systolic");
		        	     b29.setTextColor(Color.BLUE);
		        	     b29.setTextSize(15);
		                 tr.addView(b29);
		              
		                 TextView b39=new TextView(View_Records_As_List_Format.this);
	 	         	       b39.setPadding(10, 0, 0, 0);
			        	     b39.setText("diastolic");
			        	     b39.setTextColor(Color.BLUE);
			        	     b39.setTextSize(15);
			                 tr.addView(b39);
			                 
			              TextView b49=new TextView(View_Records_As_List_Format.this);
		 	         	    b49.setPadding(10, 0, 0, 0);
				        	  b49.setText("sugar");
				        	  b49.setTextColor(Color.BLUE);
				        	  b49.setTextSize(15);
				              tr.addView(b49);
				              
				            TextView b59=new TextView(View_Records_As_List_Format.this);
			 	         	 b59.setPadding(10, 0, 0, 0);
					          b59.setText("date");
					          b59.setTextColor(Color.BLUE);
					          b59.setTextSize(15);
					          tr.addView(b59);
			         
        
		             tv.addView(tr);
		            
		        	     final View vline = new View(View_Records_As_List_Format.this);
			       		   vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 2));
			       		   vline.setBackgroundColor(Color.BLUE);
			       		  
		            
		            
		            tv.addView(vline);
		            flag=0;
		            //Toast.makeText(getApplicationContext(), "HELLO",Toast.LENGTH_LONG).show();	
		            	
		            }
	    
		            else
		            {
		            	
		            
		            	
		            	JSONObject json_data = jArray.getJSONObject(i);
		        	    
	  	              Log.i("log_tag","id: "+json_data.getString("namepass")+
	  	                        ", mobile: "+json_data.getString("mobile"));
	  	       
		            
		            
	            
	           // TextView b=new TextView(Mysql_database.this);
	       	     String stime=String.valueOf(json_data.getString("namepass"));
	       	 	 /* b.setText(stime);
	       	     b.setTextColor(Color.RED);
	       	     b.setTextSize(15);
	                tr.addView(b);*/
	           
	           	
	            /*   TextView b1=new TextView(Mysql_database.this);
	                b1.setPadding(10, 0, 0, 0);
	                b1.setTextSize(15);*/
	                String stime1=json_data.getString("mobile");
	             /*   b1.setText(stime1);
	       	        b1.setTextColor(Color.RED);
	       	       tr.addView(b1);*/
	                
	              TextView b2=new TextView(View_Records_As_List_Format.this);
	             b2.setPadding(10, 0, 0, 0);
	       	    String stime2=json_data.getString("systolic");
	       		 b2.setText(stime2);
	       	     b2.setTextColor(Color.RED);
	       	     b2.setTextSize(15);
	            tr.addView(b2);
	            
	            TextView b3=new TextView(View_Records_As_List_Format.this);
	             b3.setPadding(10, 0, 0, 0);
	       	    String gender=json_data.getString("diastolic");
	       		 b3.setText(gender);
	       	     b3.setTextColor(Color.RED);
	       	     b3.setTextSize(15);
	            tr.addView(b3);
	            
	            TextView b4=new TextView(View_Records_As_List_Format.this);
	             b4.setPadding(10, 0, 0, 0);
	       	    String stime3=json_data.getString("sugar");
	       		 b4.setText(stime3);
	       	     b4.setTextColor(Color.RED);
	       	     b4.setTextSize(15);
	            tr.addView(b4);
	            
	            TextView b5=new TextView(View_Records_As_List_Format.this);
	             b5.setPadding(10, 0, 0, 0);
	       	    String stime4=json_data.getString("date");
	       		 b5.setText(stime4);
	       	     b5.setTextColor(Color.RED);
	       	     b5.setTextSize(15);
	            tr.addView(b5);
	        
	              tv.addView(tr);
	            
	            
	        final View vline1 = new View(View_Records_As_List_Format.this);
		   vline1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
		   vline1.setBackgroundColor(Color.WHITE);
		   tv.addView(vline1);		
	        
	       	
		            }
	               
	       }



	}
	catch(JSONException e)
	{
	        Log.e("log_tag", "Error parsing data "+e.toString());
	        Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
	}		
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.table_select, menu);
		return true;
	}

}



