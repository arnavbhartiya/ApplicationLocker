package com.example.applock;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DeviceAdmin extends DeviceAdminReceiver {
@Override
public void onEnabled(Context context, Intent intent) {
	// TODO Auto-generated method stub
	super.onEnabled(context, intent);
	Log.i("Device Admin", "Enabled");
}
@Override
	public String onDisableRequested(Context context, Intent intent) {
		// TODO Auto-generated method stub
		return "Admin disable Requested";
	}
@Override
	public void onDisabled(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onDisabled(context, intent);
		Log.i("Device Admin", "Disables");
		
	}
@Override
	public void onPasswordChanged(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onPasswordChanged(context, intent);
		Log.i("Device Admin","Password Changed");
	}
}
