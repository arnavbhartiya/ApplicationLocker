package com.example.applock;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class DeviceAdminCaller extends Activity {
	static final int activationCode = 47;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		ComponentName deviceAdmiinComponent = new ComponentName(this, DeviceAdmin.class);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, deviceAdmiinComponent);
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "We reccomend you to enable this");
		startActivityForResult(intent, activationCode);

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    switch (requestCode) {
	        case activationCode:
	            if (resultCode == Activity.RESULT_OK) {
	                Log.i("DeviceAdminSample", "Administration enabled!");
	            } 
	            else {
	                Log.i("DeviceAdminSample", "Administration enable FAILED!");
	            }
	            return;
	    }
	    super.onActivityResult(requestCode, resultCode, data);
	}
}
