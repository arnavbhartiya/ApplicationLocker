package com.example.applock;

import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class ServiceExample extends Service {
	
	
	 ArrayList<String> value;
	/*List<RunningAppProcessInfo> info;*/
	 Thread thread;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
@Override
public int onStartCommand(Intent intent, int flags, int startId) {
	
	Toast.makeText(getApplicationContext(),"starting service",Toast.LENGTH_SHORT).show();

	if (intent !=null && intent.getExtras()!=null){
	     value = intent.getStringArrayListExtra("mylist");
	}
		 thread = new Thread(){
			public void run(){
				try{
					
					thread.sleep(1000);
					RunningAppInfo running = new RunningAppInfo(getApplicationContext());
					List<RunningAppProcessInfo> info=running.StartRunngInfo();
					/*ActivityManager activity = (ActivityManager)Context.getSystemService(ACTIVITY_SERVICE);
					info = activity.getRunningAppProcesses();*/
					for(int i = 0;i<info.size();i++)
					{
						/*for(int k=0;k<value.size();k++)
						{*/
							try{
						if(info.get(i).processName.contentEquals("com.android.chrome"))
						{
							Intent in = getPackageManager().getLaunchIntentForPackage("com.example.applock");
							startActivity(in);
							Toast.makeText(getApplicationContext(), "Chrome locked ",Toast.LENGTH_LONG).show();
						}
						}catch(ActivityNotFoundException e){
							e.printStackTrace();
						}
						/*}*/
					}
					
					
					}
				catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		};
		thread.start();
	return START_STICKY;
	}
	

public void onDestroy()
{
	super.onDestroy();
}
}
