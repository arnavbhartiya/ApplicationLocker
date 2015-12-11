package com.example.applock;

import java.security.PublicKey;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Splash_Screen extends Activity {
final String PREF ="Mypref";
Thread timer; 
	@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.spalsh);
	final SharedPreferences settings = getSharedPreferences(PREF, 0);
	  timer = new Thread(){
	public void run(){
		try{
			timer.sleep(1000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			if(settings.getBoolean("MyFirst", true)){ 	
				Intent First = new Intent("android.intent.action.FIRST");
				startActivity(First);
				settings.edit().putBoolean("MyFirst", false).commit();
			}
			else{
				Intent Second = new Intent("android.intent.action.ORIGINAL");
				startActivity(Second);
			}
		}
		
	}
	};
	timer.start();
		}
	protected void onPause(){
		super.onPause();
		finish();
	}
}

