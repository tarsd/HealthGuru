package com.tushar.healthguru;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Systolic_Variation_Graph extends Activity implements OnClickListener{
EditText etnumsystolic;
Button bsystolic;
String number;
String name,password,mobilenumber;
int num;
TextView title1,title2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.systolic);
		title1 = (TextView)findViewById(R.id.tvsystitle1);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/FunnyKid.ttf");
        title1.setTypeface(custom_font);
        title2 = (TextView)findViewById(R.id.tvsystitle2);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/FunnyKid.ttf");
        title2.setTypeface(custom_font1);
		Bundle getdata = getIntent().getExtras();
	    name = getdata.getString("key_username");
	    password = getdata.getString("key_pass");
	    mobilenumber = getdata.getString("key_mob");
		
	    etnumsystolic = (EditText)findViewById(R.id.etnosystolic);
		bsystolic = (Button)findViewById(R.id.bnosystolic);
		//number = etnumsystolic.getText().toString();
		bsystolic.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View arg0) {
		number = etnumsystolic.getText().toString();
		//Toast.makeText(getApplicationContext(),number,Toast.LENGTH_LONG).show();
		// TODO Auto-generated method stub
		num = Integer.parseInt(number);
		if(num>=3)
		{
			Bundle basket1 = new Bundle();
			basket1.putString("key_username", name);
			basket1.putString("key_pass", password);
			basket1.putString("key_mob", mobilenumber);
			basket1.putString("key_num", number);
			Intent ourIntent1 = new Intent(Systolic_Variation_Graph.this,Temp.class);
			ourIntent1.putExtras(basket1);
			startActivity(ourIntent1);
		}
		else
		{
			Toast.makeText(getApplicationContext(),"YOU NEED MORE THAN 2 ENTRIES FOR GRAPHS",Toast.LENGTH_LONG).show();
		}
		
	}
	
	

}
