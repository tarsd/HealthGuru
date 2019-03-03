package com.tushar.healthguru;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class View_Database extends Activity{
	TextView tv;	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewdatabase);
		try
		{
	     tv = (TextView) findViewById(R.id.tv_viewinfo);
		 Login_db obj = new Login_db(this);
		 obj.open();
		 String data = obj.getData();
		 obj.close();
		 tv.setText(data); 
		}
		catch(Exception e)
		{
		 String message = e.getMessage();
		 tv.setText(message);
			
		}
		
	}
	

}
