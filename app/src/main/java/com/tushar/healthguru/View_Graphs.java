package com.tushar.healthguru;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class View_Graphs extends Activity{
	
	InputStream is = null;
	//String ip = "http://10.0.2.2/user/selectall.php";
	String line = null;
	String result = null;
	float[] xvalues;
	float[] yvalues;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mysqldatabase);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
  		StrictMode.setThreadPolicy(policy);
  		xvalues = new float[15];
  		yvalues = new float[15];
  		webserviceCall();
		
		/*LinearLayout buttonGraph = new LinearLayout(this);
        buttonGraph.setOrientation(LinearLayout.VERTICAL);
      
        
        LinearLayout buttons = new LinearLayout(this);
        buttons.setOrientation(LinearLayout.HORIZONTAL);
        Button Start = new Button(this);
        Start.setText("Test Button 1");
        Button Stop = new Button(this);
        Stop.setText("Test Button 2");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT); // Verbose!
        
        buttons.addView(Start, lp);
        buttons.addView(Stop, lp);
        buttonGraph.addView(buttons, lp);*/
       
        //float[] xvalues = new float[] { -1.0f, 1.0f, 2.0f, 3.0f , 4.0f, 5.0f, 6.0f };
        //float[] yvalues = new float[] { 15.0f, 2.0f, 0.0f, 2.0f, -2.5f, -1.0f , -3.0f };
        
       /* float[] xvalues = new float[1201];
        float[] yvalues = new float[1201];*/
      /*  for (int i=0;i<12;i++){
        	//double temp = (-5+i*.01);
        	//xvalues[i] = (float)temp;
        	//yvalues[i] = (float)(Math.sin(temp)*Math.random());
        	yvalues[i] = i+1;
        }*/
        
        /*plot2d graph = new plot2d(this, xvalues, yvalues, 1);

        buttonGraph.addView(graph, lp);

        setContentView(buttonGraph);
        
		//setContentView(R.layout.mysqldatabase);*/
		//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		//StrictMode.setThreadPolicy(policy);
		//webserviceCall();
	}
	
	
	private void webserviceCall() 
	{
		try{
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(Utility.base_url+"graph.php");
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

	// Toast.makeText(getApplicationContext(), "HELLO tushar",Toast.LENGTH_LONG).show();
	//TableLayout tv=(TableLayout) findViewById(R.id.table);
	//tv.removeAllViewsInLayout();
	  int flag=1;
	for(int i=-1;i<jArray.length()-1;i++)
	        
	        {
	        	 
	              
	              
	              
		            /*TableRow tr=new TableRow(View_Graphs.this);
		            
		            tr.setLayoutParams(new LayoutParams(
		                       LayoutParams.FILL_PARENT,
		                       LayoutParams.WRAP_CONTENT));*/
		            
		            //Toast.makeText(getApplicationContext(), "HELLO world",Toast.LENGTH_LONG).show();
		            
		            
		         
		           // if(flag==1)
		           // {
		            	
		            	/*TextView b6=new TextView(View_Graphs.this);
		        	     b6.setText("namepass");
		        	     b6.setTextColor(Color.BLUE);
		        	     b6.setTextSize(15);
		                 tr.addView(b6);
		            
		            	
		                TextView b19=new TextView(View_Graphs.this);
		                 b19.setPadding(10, 0, 0, 0);
		                 b19.setTextSize(15);
		        	     b19.setText("mobile");
		        	     b19.setTextColor(Color.BLUE);
		        	     tr.addView(b19);
		                 
		               TextView b29=new TextView(View_Graphs.this);
 	         	       b29.setPadding(10, 0, 0, 0);
		        	     b29.setText("systolic");
		        	     b29.setTextColor(Color.BLUE);
		        	     b29.setTextSize(15);
		                 tr.addView(b29);
		              
		                 TextView b39=new TextView(View_Graphs.this);
	 	         	       b39.setPadding(10, 0, 0, 0);
			        	     b39.setText("diastolic");
			        	     b39.setTextColor(Color.BLUE);
			        	     b39.setTextSize(15);
			                 tr.addView(b39);
			                 
			              TextView b49=new TextView(View_Graphs.this);
		 	         	    b49.setPadding(10, 0, 0, 0);
				        	  b49.setText("sugar");
				        	  b49.setTextColor(Color.BLUE);
				        	  b49.setTextSize(15);
				              tr.addView(b49);
			         
        
		             tv.addView(tr);
		            
		        	     final View vline = new View(View_Graphs.this);
			       		   vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 2));
			       		   vline.setBackgroundColor(Color.BLUE);
			       		  
		            
		            
		            tv.addView(vline);*/
		            //flag=0;
		            //Toast.makeText(getApplicationContext(), "HELLO",Toast.LENGTH_LONG).show();	
		            	
		           // }
	    
		           // else
		           // {
		            	
		            
		            	
		            	JSONObject json_data = jArray.getJSONObject(i);
		        	    
	  	              Log.i("log_tag","id: "+json_data.getString("namepass")+
	  	                        ", mobile: "+json_data.getString("mobile"));
	  	       
		            
		            
	            
	           // TextView b=new TextView(View_Graphs.this);
	       	   /*  String stime=String.valueOf(json_data.getString("namepass"));
	       	 	  b.setText(stime);
	       	     b.setTextColor(Color.RED);
	       	     b.setTextSize(15);
	                tr.addView(b);*/
	           
	           	
	             /*  TextView b1=new TextView(View_Graphs.this);
	                b1.setPadding(10, 0, 0, 0);
	                b1.setTextSize(15);
	                String stime1=json_data.getString("mobile");
	                b1.setText(stime1);
	       	        b1.setTextColor(Color.RED);
	       	       tr.addView(b1);*/
	                
	             /* TextView b2=new TextView(View_Graphs.this);
	             b2.setPadding(10, 0, 0, 0);*/
	       	    String stime2=json_data.getString("systolic");
	       	   // xvalues[i+1] = Float.parseFloat(stime2);
	       	    
	       	    Toast.makeText(getApplicationContext(),stime2,Toast.LENGTH_LONG ).show();
	       		/* b2.setText(stime2);
	       	     b2.setTextColor(Color.RED);
	       	     b2.setTextSize(15);
	            tr.addView(b2);*/
	            
	          /*  TextView b3=new TextView(View_Graphs.this);
	             b3.setPadding(10, 0, 0, 0);
	       	    String gender=json_data.getString("diastolic");
	       		 b3.setText(gender);
	       	     b3.setTextColor(Color.RED);
	       	     b3.setTextSize(15);
	            tr.addView(b3);
	            
	            TextView b4=new TextView(View_Graphs.this);
	             b4.setPadding(10, 0, 0, 0);
	       	    String stime3=json_data.getString("sugar");
	       		 b4.setText(stime3);
	       	     b4.setTextColor(Color.RED);
	       	     b4.setTextSize(15);
	            tr.addView(b4);
	        
	              tv.addView(tr);
	            
	            
	        final View vline1 = new View(View_Graphs.this);
		   vline1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
		   vline1.setBackgroundColor(Color.WHITE);
		   tv.addView(vline1);	*/	
	        
	       	
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



