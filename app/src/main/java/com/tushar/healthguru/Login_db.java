package com.tushar.healthguru;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Login_db {
  public static final String KEY_ID = "person_id";
  public static final String KEY_NAME = "person_name";
  public static final String KEY_AGE = "person_age";
  public static final String KEY_CITY = "person_city";
  public static final String KEY_PASSWORD = "password";
  
  private static final String DATABASE_NAME = "login_data";
  private static final String DATABASE_TABLE = "people_table";
  private static final int DATABASE_VERSION = 1;
  
  private Dbhelper ourhelper;
  private static Context ourcontext;
  private SQLiteDatabase ourdatabase;
  
  private static class Dbhelper extends SQLiteOpenHelper{

	public Dbhelper(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL("CREATE TABLE " + DATABASE_TABLE +" (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
		+ KEY_NAME + " TEXT NOT NULL, " + KEY_AGE + " TEXT NOT NULL, " + KEY_CITY + " TEXT NOT NULL, " + 
		KEY_PASSWORD + " TEXT NOT NULL);"
		);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		arg0.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
		onCreate(arg0);
	}
  }
	public Login_db(Context c)
	{
	  ourcontext = c;	
	}
	
    public Login_db open() throws SQLException
    {
      ourhelper = new Dbhelper(ourcontext);
      ourdatabase = ourhelper.getWritableDatabase();
      return this;
    }
  
    public void close()
    {
    	ourhelper.close();
    }
  
    public long create_entry(String name,String age,String city,String p1)
    {
     ContentValues cv = new ContentValues();
     cv.put(KEY_NAME,name);
     cv.put(KEY_AGE,age);  
     cv.put(KEY_CITY,city);
     cv.put(KEY_PASSWORD,p1);
     return ourdatabase.insert(DATABASE_TABLE, null, cv); 
    }
	public String getData() {
		// TODO Auto-generated method stub
		String[] columns_db = new String[]{ KEY_ID , KEY_NAME , KEY_AGE , KEY_CITY , KEY_PASSWORD }; 
		Cursor c = ourdatabase.query(DATABASE_TABLE, columns_db , null, null, null, null, null);
		String result = "";
		int iRow = c.getColumnIndex(KEY_ID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iAge = c.getColumnIndex(KEY_AGE);
		int iCity= c.getColumnIndex(KEY_CITY);
		int iPassword = c.getColumnIndex(KEY_PASSWORD);
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
		{
		  result = result + c.getString(iRow) + "  " + c.getString(iName) + "  " + c.getString(iAge) + "  " + c.getString(iCity) + "  " +c.getString(iPassword) + "\n"; 
		}
		
		
		return result;
	}
  }  
