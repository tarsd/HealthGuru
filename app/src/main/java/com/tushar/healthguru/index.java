package com.tushar.healthguru;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class index extends ListActivity
{
	
    String classes[] = {"Enter_Records","Systolic_Variation_Graph","Diastolic_Variation_Graph","Sugar_Variation_Graph","First_Aid","View_Records_As_List_Format",
    		            "Insert_Problematic_Medicines","View_Problematic_Medicines"};
    String name;
    String password;
    String mobile;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(index.this,android.R.layout.simple_list_item_1,classes));
		Bundle basket = getIntent().getExtras();
	    name = basket.getString("key_name");
	    password = basket.getString("key_password");
	    mobile = basket.getString("key_mobile");
	    //graphics();
	}
	
	

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		try
		{
		 String temp = classes[position];
		 Class ourClass = Class.forName("com.tushar.healthguru."+temp);
		//if(position==0)
		// {
			Bundle basket1 = new Bundle();
			basket1.putString("key_username", name);
			basket1.putString("key_pass", password);
			basket1.putString("key_mob", mobile);
			
			Intent ourIntent1 = new Intent(index.this,ourClass);
			ourIntent1.putExtras(basket1);
			startActivity(ourIntent1);
		 //}
		/* else
		 {
		  Intent ourIntent = new Intent(index.this,ourClass);
		  startActivity(ourIntent);
		 }*/
		}
		catch(ClassNotFoundException e)
		{
		  System.out.println("ERROR");
		}
	 }
	}
  

