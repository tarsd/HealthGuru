package com.tushar.healthguru;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;


import java.util.HashMap;


/**
 * Created by ravdeep on 23/12/15.
 */
public class SessionManagement {

    SharedPreferences userpref;
  //  SharedPreferences tokenpref;
    SharedPreferences.Editor usereditor;
    //SharedPreferences.Editor tokeneditor;
    Context scontext;
    int Private_Mode = 0;
    private final static String Pref_User = "prefuser";

    //private final static String Pref_token = "preftoken";
    private final static String Is_Login = "IsLoggedIn";
  public final static String Key_Token = "token";
    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
    public static final String Key_Uname = "username";
    public static final  String Key_Pass="password";

    // Constructor
    public SessionManagement(Context context) {
        this.scontext = context;
        //volleySingleton = VolleySingleton.getInstance();
        //requestQueue = VolleySingleton.getInstance().getRequestQueue();

        //tokenpref = scontext.getSharedPreferences(Pref_token, Private_Mode);
        userpref = scontext.getSharedPreferences(Pref_User,Private_Mode);
       // tokeneditor = tokenpref.edit();
        usereditor = userpref.edit();
    }

    public void CreateLoginSession(String token, String uname, String password) {
        usereditor.putString(Key_Token,token);
        usereditor.putString(Key_Uname, uname);
        usereditor.putString(Key_Pass,password);
        //usereditor.putString(KEY_NAME,fname);
        usereditor.commit();

    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        //Geting token And Username From Saved Pref and storing it to Hashmap
        user.put(Key_Token,userpref.getString(Key_Token,null));
        user.put(Key_Uname, userpref.getString(Key_Uname, null));
        user.put(Key_Pass, userpref.getString(Key_Pass, null));
       // user.put(KEY_NAME,userpref.getString(KEY_NAME,null));

        return user;
    }
    public HashMap<String,String> getToken()
    {
        HashMap<String, String> usertoken = new HashMap<>();
        usertoken.put(Key_Token,userpref.getString(Key_Token,null));

        return usertoken;
    }

    public void CheckLogin() {
        if (!this.isLoggedIn()) {
            Log.e("Session  management ", "in if" + this.isLoggedIn());
            // user is not logged in redirect him to Login Activity
        //    Intent i = new Intent(scontext, LoginActivity.class);
            Intent i = new Intent(scontext, MainActivity.class);

            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            scontext.startActivity(i);

        } else {
            HashMap<String, String> user1= getUserDetails();
            String u=user1.get(Key_Uname);
            String p=user1.get(Key_Pass);
            String n=user1.get(KEY_NAME);
            Log.e("session management",u+"="+Key_Uname+"||"+p+"="+Key_Pass+"||"+n+"="+KEY_NAME);
            /*DBHandler h= new DBHandler(scontext);
            h.drop(scontext);*/
            //post2(u, p);
            Log.e("Session  management ", "in else");
          //  Intent i = new Intent(scontext, Main2Activity.class);
            Intent i = new Intent(scontext, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            scontext.startActivity(i);
        }


    }

    public void logoutUser() {
        // Clearing all data from Shared Preferences
        usereditor.clear();
        usereditor.commit();
      //  tokeneditor.clear();
        //tokeneditor.commit();
       // DBHandler h= new DBHandler(scontext);
       // h.drop(scontext);



        // After logout redirect user to Loing Activity
        //Intent i = new Intent(scontext, LoginActivity.class);
        Intent i = new Intent(scontext, MainActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        scontext.startActivity(i);

    }

    // Get Login State
    public boolean isLoggedIn() {
        return userpref.getBoolean(Is_Login, false);
    }
/*
    public void post2(final String email, final String password) {
Log.e("Session Management","In Post2"+email+"||"+password);
        StringRequest myReq = new StringRequest(Request.Method.POST,
                base + rt_account + account_login + api_key,
                createMyReqSuccessListener2(),
                createMyReqErrorListener2()) {

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
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

    private Response.Listener<String> createMyReqSuccessListener2() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                // Log.d(TAG, response.toString());

                try {
                    Log.e("Session Management", "Post 2 In Try");
                    //Converting string from string Request to Json
                    JSONObject loggedin = new JSONObject(response);
                    String email = loggedin.getString("status");
                    String token = loggedin.getString("token");

                    //  text2 = token;
                    // rav=token;
                    HashMap<String, String> user2= getUserDetails();
                    HashMap<String, String> token1= getToken();
                    String u,p,n;
                   // t=token1.get(Key_Token);
                    u=user2.get(Key_Uname);
                    p=user2.get(Key_Pass);
                    n=user2.get(KEY_NAME);
                    Log.e("Session Management","token="+token+"||"+"User="+u+"||"+"Pass="+p+"Name="+n);


                    CreateLoginSession(token, u, p,n);

                    Log.e("Session ,Management", isLoggedIn() + "");


                } catch (JSONException e) {
                    e.printStackTrace();



                }

                //  Toast.makeText(getApplicationContext(),"RESPONSE"+response,Toast.LENGTH_LONG).show();
            }
        };
    }


    private Response.ErrorListener createMyReqErrorListener2() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError)

                {
                    Log.e("Session Management","Timeout Error");
                }
                else if (error instanceof AuthFailureError)

                {
                    Log.e("Session Management","Auth Failed");
                }




                Log.e("fefe", "" + error);



               /* Toast.makeText(getApplicationContext(), "ERROR" + error, Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar
                        .make(llay, error + "'", Snackbar.LENGTH_LONG);
                snackbar.show();*/
          /*  }
        };
    }
*/
}
