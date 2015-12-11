package com.example.applock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.preference.Preference;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class First_Time_Class extends Activity implements OnClickListener{
	EditText et1_first,et2_first;
	TextView first_tv;
	Button first_button,first_test,enable_admin;
	public static String filename = "shared";
	SharedPreferences data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firsttime);
		first_button=(Button)findViewById(R.id.button_first);
		first_test=(Button)findViewById(R.id.buttonPassword);
		first_tv=(TextView)findViewById(R.id.text_first);
		et1_first=(EditText)findViewById(R.id.edit1_first);
		et2_first=(EditText)findViewById(R.id.edit2_first);
		first_button.setOnClickListener(this);
		first_test.setOnClickListener(this);
		data=getSharedPreferences(filename, 0);
		enable_admin=(Button)findViewById(R.id.enableDeviceAdmin);	
		enable_admin.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.button_first:
			String edit1 = et1_first.getText().toString();
			String edit2 = et2_first.getText().toString();
			if(!edit1.contentEquals(edit2)){
				first_tv.setText("Try again!");
				first_tv.setTextColor(Color.RED);
			}else{
				SharedPreferences.Editor editor = data.edit();
				editor.putString("pass", edit2);
				/*ActivityManager activity = (ActivityManager)this.getSystemService(ACTIVITY_SERVICE);
				List<RunningAppProcessInfo> info = activity.getRunningAppProcesses();
				for(int i = 0;i<info.size();i++){
					String x = info.get(i).processName;
					editor.putStringSet(key, values)
				}*/
				editor.commit();
				first_tv.setText("");
				Toast.makeText(getApplicationContext(), "Password Saved", Toast.LENGTH_SHORT).show();
				
			}
			break;
		case R.id.buttonPassword:
			data=getSharedPreferences(filename, 0);
			String passwordShow=data.getString("pass", "no password saved");
			Toast.makeText(getApplicationContext(), passwordShow, Toast.LENGTH_SHORT).show();
			/*try {
				FileOutputStream filewriter = openFileOutput("checking", MODE_WORLD_WRITEABLE);
				ActivityManager activity = (ActivityManager)this.getSystemService(ACTIVITY_SERVICE);
				List<RunningAppProcessInfo> info = activity.getRunningAppProcesses();
				for(int i = 0;i<info.size();i++){
					String x = info.get(i).processName;
				filewriter.write(x);;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			break;
		case R.id.enableDeviceAdmin:
			Toast.makeText(getApplicationContext(), "starting device admin", Toast.LENGTH_SHORT).show();
			Intent adminIntent= new Intent("android.intent.action.DEVICE_ADMIN_CALLER");
			startActivity(adminIntent);
		}
	}
	/*public File getAlbumStorageDir(String albumName) {
	    // Get the directory for the user's public pictures directory.
	    File file = new File(Environment.getExternalStoragePublicDirectory(
	            Environment.DIRECTORY_DOWNLOADS), albumName);
	    return file;
	}*/
}
