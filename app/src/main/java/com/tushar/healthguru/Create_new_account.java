package com.tushar.healthguru;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Create_new_account extends Activity {       //implements OnClickListener {

EditText et_name,et_age,et_city,et_password,et_retypepassword;
Button b_submit,b_view;	
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.new_account);
		
	/*	et_name = (EditText) findViewById(R.id.et_name);
		et_age = (EditText) findViewById(R.id.et_age);
		et_city = (EditText) findViewById(R.id.et_city);
		et_password = (EditText) findViewById(R.id.et_password);
		et_retypepassword = (EditText) findViewById(R.id.et_retypepassword);
		b_submit = (Button) findViewById(R.id.b_submit);
		b_view = (Button) findViewById(R.id.button1);
		
		b_submit.setOnClickListener(this);
		b_view.setOnClickListener(this);
		
	}

	@Override
       public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int id = arg0.getId();
		switch(id)
	  {
	   case R.id.b_submit :
		{
		 String p1 = et_password.getText().toString();
		 String p2 = et_retypepassword.getText().toString();
	
		if(p1.contentEquals(p2))
		{
	      String name = et_name.getText().toString();
	      String age = et_age.getText().toString();
	      String city = et_city.getText().toString();
	      boolean diditwork = true;
	      try
	      {
	       Login_db obj1 = new Login_db(Create_new_account.this);
	       obj1.open();
	       obj1.create_entry(name,age,city,p1);
	       obj1.close();
	      }
	      catch(Exception e)
	      {
	    	diditwork=false; 
	      }
	      finally
	      {
	    	if(diditwork)
	    	{
	    	 Dialog d = new Dialog(this);
	    	 d.setTitle("HELLO!!");
	    	 TextView tv = new TextView(this);
	    	 tv.setText("YOU HAVE SUCCESSFULY CREATED YOUR ACCOUNT");
	    	 d.setContentView(tv);
	    	 d.show();
	    	}
	    	else
	        {
	    		Dialog d1 = new Dialog(this);
		    	d1.setTitle("HELLO!!");
		        TextView tv1 = new TextView(this);
		    	tv1.setText("CREATE YOUR ACCOUNT AGAIN");
		    	d1.setContentView(tv1);
		        d1.show();	
	        }
	      }
		}
		else
		{
		  Toast.makeText(getApplicationContext()," RE-ENTER PASSWORD ",Toast.LENGTH_LONG).show();
		}
		break;
	   }
		
	   case R.id.button1 :
	   {
		  Intent i1 = new Intent(Create_new_account.this,View_Database.class);
		  startActivity(i1);
	   }
	}*/
    
}
}
