package com.tushar.healthguru;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.lang.Thread; 


public class StartActivity extends Activity {
@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		Thread thread1 = new Thread(){
		 public void run()
		 {
		   try
		   {
			 sleep(1000);  
		   }
		   catch(InterruptedException e)
		   {
			 System.out.println("Error");  
		   }
		   finally
		   {
		     Intent intent1 = new Intent("com.example.tarsd.MAINACTIVITY");
		     startActivity(intent1);
		   } 
		 }
		};
	   thread1.start();
	 }

@Override
      protected void onPause() {
	      // TODO Auto-generated method stub
	        super.onPause();
	        finish();
       }
   
   
}