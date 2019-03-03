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
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Digits;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

public class Mysqllogin extends Activity implements OnClickListener, Validator.ValidationListener {
	@NotEmpty
EditText et1;
	@NotEmpty
			@Password(min=6,message = "Minimum 6 characters Required")
EditText et2;
	@NotEmpty
			@ConfirmPassword
EditText et3;

@NotEmpty(message = "Mobile Number Required")
		@Length(min = 10,max =10,message = "Please Enter a valid Mobile Number")
EditText etmob_num;
Button b1;
InputStream is = null;
String line = null;
String result=null;
int code;
String name,p1,p2,mobile;
	private Validator validator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_account);

		validator = new Validator(this);
		validator.setValidationMode(Validator.Mode.BURST);
		validator.setValidationListener(this);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		b1 = (Button)findViewById(R.id.b_submit);
		et1 = (EditText)findViewById(R.id.et_name);
		et2 = (EditText)findViewById(R.id.et_password);
		et3 = (EditText)findViewById(R.id.et_retypepassword);
		etmob_num = (EditText)findViewById(R.id.et_mobilenum);
		//ProgressDialog xx=new ProgressDialog(this);
		//xx.showd
		b1.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==b1.getId())
		// TODO Auto-generated method stub
		{
			Log.e("Button","clicked");
		 name = et1.getText().toString();
		 p1 = et2.getText().toString();
		 p2 = et3.getText().toString();
		 mobile = etmob_num.getText().toString();
		//if(p1.contentEquals(p2))
		//{
		  //insert();
			validator.validate();
			//onValidationSucceeded();
		//}
	//	else
	//	{
	//	  Toast.makeText(getApplicationContext(), "RETYPE PASSWORD", Toast.LENGTH_LONG).show();
	//	}
	}}
	
	
	public void insert()
    {
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
 
   	    nameValuePairs.add(new BasicNameValuePair("name",name));
    	nameValuePairs.add(new BasicNameValuePair("password",p1));
    	nameValuePairs.add(new BasicNameValuePair("mobile",mobile));
    	try
    	{
		    HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(Utility.base_url+"logindata.php");
	        //HttpPost httppost = new HttpPost("http://healthguru.lockernerd.co.uk/code/logindata.php");
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        HttpResponse response = httpclient.execute(httppost); 
	        HttpEntity entity = response.getEntity();
	        is = entity.getContent();
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
		Log.e("Validation","Passed");
		insert();
	}

	@Override
	public void onValidationFailed(List<ValidationError> errors) {
Log.e("Validation","failed");
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
