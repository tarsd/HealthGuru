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
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Tempdia extends Activity{
	
	InputStream is = null;
	//String ip = "http://10.0.2.2/user/selectall.php";
	int number;
	String line = null;
	String result = null;
	String name;
	String password;
	String namepass;
	String mobilenumber;
	String date;
	String numsystolic;
	float[] systolic;
	float[] xaxis;
	String[] insertdate;
	int j=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.temp);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		//systolic = new float[5];
		//xaxis = new float[5];
		Bundle getdata = getIntent().getExtras();
	    name = getdata.getString("key_username");
	    password = getdata.getString("key_pass");
	    mobilenumber = getdata.getString("key_mob");
	    numsystolic = getdata.getString("key_num");
	    number = Integer.parseInt(numsystolic);
	    namepass=name+"_"+password;
	//	namepass="hxhx_qwerty123";
		webserviceCall();
		LinearLayout buttonGraph = new LinearLayout(this);
        buttonGraph.setOrientation(LinearLayout.VERTICAL);
        
        
        LinearLayout buttons = new LinearLayout(this);
        buttons.setOrientation(LinearLayout.HORIZONTAL);
        //Button Start = new Button(this);
        //Start.setText("Test Button 1");
        
        //Button Stop = new Button(this);
        //Stop.setText("Test Button 2");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT); // Verbose!
        
       // buttons.addView(Start, lp);
       // buttons.addView(Stop, lp);
        buttonGraph.addView(buttons, lp);
       
        //float[] xvalues = new float[] { -1.0f, 1.0f, 2.0f, 3.0f , 4.0f, 5.0f, 6.0f };
        //float[] yvalues = new float[] { 15.0f, 2.0f, 0.0f, 2.0f, -2.5f, -1.0f , -3.0f };
        
       /* float[] xvalues = new float[1201];
        float[] yvalues = new float[1201];
        for (int i=0;i<1201;i++){
        	double temp = (-5+i*.01);
        	xvalues[i] = (float)temp;
        	yvalues[i] = (float)(Math.sin(temp)*Math.random());
        }*/
        
        plot2d graph = new plot2d(this, xaxis, systolic,insertdate,number,1);

        buttonGraph.addView(graph, lp);

        setContentView(buttonGraph);
        //Toast.makeText(getApplicationContext(),""+number,Toast.LENGTH_LONG).show();
	}
	
	
	private void webserviceCall() 
	{
		try{
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("namepass",namepass));
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(Utility.base_url+"mysql.php");
	        //HttpPost httppost = new HttpPost("http://healthguru.lockernerd.co.uk/code/mysql.php");
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost); 
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

	//Toast.makeText(getApplicationContext(), "HELLO tushar"+number,Toast.LENGTH_LONG).show();
	/*TableLayout tv=(TableLayout) findViewById(R.id.table);
	tv.removeAllViewsInLayout();*/
	int flag=jArray.length()-1;
	//Toast.makeText(getApplicationContext(), "HELLO tushar"+flag,Toast.LENGTH_LONG).show();
	/*systolic = new float[flag];
	xaxis = new float[flag];
	insertdate = new String[flag];*/
	systolic = new float[number];
	xaxis = new float[number];
	insertdate = new String[number];
	int loop=flag-number;
	for(int i=loop;i<jArray.length()-1;i++)
	        
	        {
	        	 
	              
	              
	             // Toast.makeText(getApplicationContext(),""+flag,Toast.LENGTH_LONG).show();
		           /* TableRow tr=new TableRow(Temp.this);
		            
		            tr.setLayoutParams(new LayoutParams(
		                       LayoutParams.FILL_PARENT,
		                       LayoutParams.WRAP_CONTENT));*/
		            
		            //Toast.makeText(getApplicationContext(), "HELLO world",Toast.LENGTH_LONG).show();
		            
		            
		         
		           /* if(flag==1)
		            {
		            	
		            	TextView b6=new TextView(Temp.this);
		        	     b6.setText("namepass");
		        	     b6.setTextColor(Color.BLUE);
		        	     b6.setTextSize(15);
		                 tr.addView(b6);
		            
		            	
		                TextView b19=new TextView(Temp.this);
		                 b19.setPadding(10, 0, 0, 0);
		                 b19.setTextSize(15);
		        	     b19.setText("mobile");
		        	     b19.setTextColor(Color.BLUE);
		        	     tr.addView(b19);
		                 
		               TextView b29=new TextView(Temp.this);
 	         	       b29.setPadding(10, 0, 0, 0);
		        	     b29.setText("systolic");
		        	     b29.setTextColor(Color.BLUE);
		        	     b29.setTextSize(15);
		                 tr.addView(b29);
		              
		                 TextView b39=new TextView(Temp.this);
	 	         	       b39.setPadding(10, 0, 0, 0);
			        	     b39.setText("diastolic");
			        	     b39.setTextColor(Color.BLUE);
			        	     b39.setTextSize(15);
			                 tr.addView(b39);
			                 
			              TextView b49=new TextView(Temp.this);
		 	         	    b49.setPadding(10, 0, 0, 0);
				        	  b49.setText("sugar");
				        	  b49.setTextColor(Color.BLUE);
				        	  b49.setTextSize(15);
				              tr.addView(b49);
			         
        
		             tv.addView(tr);
		            
		        	     final View vline = new View(Temp.this);
			       		   vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 2));
			       		   vline.setBackgroundColor(Color.BLUE);
			       		  
		            
		            
		            tv.addView(vline);
		            flag=0;
		            //Toast.makeText(getApplicationContext(), "HELLO",Toast.LENGTH_LONG).show();	
		            	
		            }*/
	    
		            //else
		           // {
		            	
		            
		            	
		            	JSONObject json_data = jArray.getJSONObject(i);
		        	    
	  	              Log.i("log_tag","id: "+json_data.getString("namepass")+
	  	                        ", mobile: "+json_data.getString("mobile"));
	  	       
	  	           // Toast.makeText(getApplicationContext(), "HELLO tushar",Toast.LENGTH_LONG).show();
		            
	            
	            //TextView b=new TextView(Temp.this);
	       	     String stime=String.valueOf(json_data.getString("namepass"));
	       	 	 // b.setText(stime);
	       	     //b.setTextColor(Color.RED);
	       	    // b.setTextSize(15);
	              //  tr.addView(b);
	           
	           	
	              /* TextView b1=new TextView(Temp.this);
	                b1.setPadding(10, 0, 0, 0);
	                b1.setTextSize(15);*/
	                String stime1=json_data.getString("mobile");
	              /*  b1.setText(stime1);
	       	        b1.setTextColor(Color.RED);
	       	       tr.addView(b1);*/
	                
	             /* TextView b2=new TextView(Temp.this);
	             b2.setPadding(10, 0, 0, 0);*/
	       	    String stime2=json_data.getString("systolic");
	       	   // systolic[j] = Integer.parseInt(stime2);
	       	   // xaxis[j] = j+1;
	       	    
                //Toast.makeText(getApplicationContext(), ""+systolic[i],Toast.LENGTH_LONG).show();
	       		/* b2.setText(stime2);
	       	     b2.setTextColor(Color.RED);
	       	     b2.setTextSize(15);
	            tr.addView(b2);*/
	            
	          /*  TextView b3=new TextView(Temp.this);
	             b3.setPadding(10, 0, 0, 0);*/
	       	    String gender=json_data.getString("diastolic");
	       	    systolic[j] = Integer.parseInt(gender);
	       	    xaxis[j] = j+1;
	       		/* b3.setText(gender);
	       	     b3.setTextColor(Color.RED);
	       	     b3.setTextSize(15);
	            tr.addView(b3);
	            
	            TextView b4=new TextView(Temp.this);
	             b4.setPadding(10, 0, 0, 0);*/
	       	    String stime3=json_data.getString("sugar");
	       		/* b4.setText(stime3);
	       	     b4.setTextColor(Color.RED);
	       	     b4.setTextSize(15);
	            tr.addView(b4);
	        
	              tv.addView(tr);*/
	           String stime4=json_data.getString("date");
	           insertdate[j] = stime4;
	           j++;
	       /* final View vline1 = new View(Temp.this);
		   vline1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
		   vline1.setBackgroundColor(Color.WHITE);
		   tv.addView(vline1);*/		
	        
	       	
		            }
	               
	      // }



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





