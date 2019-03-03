package com.tushar.healthguru;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends Activity implements OnClickListener{
EditText et1;
EditText et2;
TextView text;
InputStream is=null;
String result=null;
String line=null;
String mobilenum;
int flag;
String loginname;
    private SessionManagement sessionManagement;
	private VolleySingleton volleySingleton;
	private RequestQueue requestQueue;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		requestQueue=VolleySingleton.getInstance().getRequestQueue();
        sessionManagement=new SessionManagement(getApplicationContext());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
        Button b1 = (Button) findViewById(R.id.buttonlogin);
        Button b2 = (Button) findViewById(R.id.button_newaccount);
        //TextView tv1 = (TextView) findViewById(R.id.tvwelcome);
        et1 = (EditText) findViewById(R.id.etusername);
        et2 = (EditText) findViewById(R.id.etpassword);
        ImageView image = (ImageView) findViewById(R.id.test_image);
        text = (TextView)findViewById(R.id.tvwelcome);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Actionit.TTF");
        text.setTypeface(custom_font);
        final ToggleButton tb1 = (ToggleButton) findViewById(R.id.tbshowpassword);
        b1.setOnClickListener(this);
       tb1.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View arg0) 
		   {
				// TODO Auto-generated method stub
				if(tb1.isChecked())
				{
				  et2.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
			    }
				else
				{
				  et2.setInputType(InputType.TYPE_CLASS_TEXT);
				}
		   }
		});
       b2.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	
	
	private void movetoindex() {
		// TODO Auto-generated method stub
		Bundle basket = new Bundle();
		String name = et1.getText().toString();
		String pass_word = et2.getText().toString();
		basket.putString("key_name", name);
		basket.putString("key_password", pass_word);
		basket.putString("key_mobile",mobilenum);
        sessionManagement.CreateLoginSession(name+pass_word,name,pass_word);
		Intent startindex = new Intent(MainActivity.this,index.class);
	    startindex.putExtras(basket);
	    startActivity(startindex);
		
	}


	public void select()
    {
		String uname = et1.getText().toString();
		String password = et2.getText().toString();
		Log.e("function","select");
		post(uname,password);}

		/*ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
 
	nameValuePairs.add(new BasicNameValuePair("Password",password));
    	
    	try
    	{
    		
		    HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(Utility.base_url+"checkusername.php");
	        //HttpPost httppost = new HttpPost("http://healthguru.lockernerd.co.uk/code/checkusername.php");
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost); 
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
	        Log.e("check login ",nameValuePairs.toString());
	        Log.e("pass 1", "connection success ");
	    }
        catch(Exception e)
	    {
        	Log.e("Fail 1", e.toString());
	    	Toast.makeText(getApplicationContext(), "Invalid IP Address",Toast.LENGTH_LONG).show();
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
   	    	//Toast.makeText(getBaseContext(), "HELLO ",Toast.LENGTH_LONG).show();
        	JSONObject json_data = new JSONObject(result);
        	loginname=(json_data.getString("Name"));
        	mobilenum = (json_data.getString("mobile"));
		   // Toast.makeText(getBaseContext(), "HELLO "+name,
			//Toast.LENGTH_LONG).show();
		   // Toast.makeText(getBaseContext(), "HELLO "+mobilenum,Toast.LENGTH_LONG).show();
		    if(loginname.contentEquals(uname))
		    {
		    	flag=1;
		    }
		    
		}
        catch(Exception e)
    	{
        	Log.e("Fail 3", e.toString());
    	}
    }*/
	public void post(final String name, final String password) {


		StringRequest myReq = new StringRequest(Request.Method.POST,
				Utility.base_url+"checkusername.php",
				createMyReqSuccessListener(),
				createMyReqErrorListener()) {

			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();
				params.put("name", name);
				params.put("password", password);
				return params;
			}

			;
		};
		int socketTimeout = 5000;//5 seconds - change to what you want
		RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
		myReq.setRetryPolicy(policy);
		requestQueue.add(myReq);

	}

	private Response.Listener<String> createMyReqSuccessListener() {
		return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                // Log.d(TAG, response.toString());

                try {
                    Log.e("Login INformation", response);
                    Log.e("Entered Try", "In Try");
                    //Converting string from string Request to Json
                    JSONObject loggedin = new JSONObject(response);
                    // String email = loggedin.getString("status");

                    if (loggedin.has("Name")) {
                        String fullname = loggedin.getString("Name");
                        loginname = fullname;
                        if (loggedin.has("Mobile")) {
                            String Mobile = loggedin.getString("Mobile");
                        }

                        flag = 1;
                        if(loginname.equals("0"))
                        {
                            Log.e("login","illegal");
                            Toast.makeText(getBaseContext(), "INVALID USERNAME OR PASSWORD", Toast.LENGTH_LONG).show();


                        }
                        else{
                            Toast.makeText(getBaseContext(), "HELLO " + loginname,
                                    Toast.LENGTH_LONG).show();
                            movetoindex();
                        }

                    } else {


                    }

                } catch (JSONException e) {
                    e.printStackTrace();


                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();


                }



                }
            }

            ;

	}


	private Response.ErrorListener createMyReqErrorListener() {
		return new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {

				if (error instanceof TimeoutError || error instanceof NoConnectionError)

				{
					Log.e("Oops!! ", "No Internet Connection");
				}
				else if (error instanceof AuthFailureError)
					Log.e("Authentication Failed", "Incorrect User/Pass");
				{
				}




				Log.e("fefe", "" + error);



               /* Toast.makeText(getApplicationContext(), "ERROR" + error, Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(llay, error + "'", Snackbar.LENGTH_LONG);
                snackbar.show();*/
			}
		};
	}



	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
	  int id = arg0.getId();
	  switch(id)
	  {
	    case R.id.buttonlogin:
	    {
	    	flag=0;
	    	select();
	    Log.e("flag value",flag+"");
            /*String user = et1.getText().toString();
	      Bundle basket1 = new Bundle();
	      basket1.putString("key",user);*/
	    	if(flag==1)
	    	{
	    		/* Toast.makeText(getBaseContext(), "HELLO "+loginname,
	    					Toast.LENGTH_LONG).show();
	    		movetoindex();*/
	    	}
	    	else
	    	{
	    //		Toast.makeText(getBaseContext(),"INVALID USERNAME OR PASSWORD",Toast.LENGTH_LONG).show();
	    	}
	      //Intent startindex = new Intent(MainActivity.this,index.class);
	      //startindex.putExtras(basket1);
	     // startActivity(startindex);
	      break;
	    }
	    case R.id.button_newaccount:
	    {
	      Intent start = new Intent(MainActivity.this,Mysqllogin.class);
	      startActivity(start);
	    }
	  }
		
	}
}
