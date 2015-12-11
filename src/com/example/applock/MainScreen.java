package com.example.applock;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainScreen extends Activity implements OnClickListener {
	Button b1,b2,b3;
	TextView tv;
	EditText et;
	public static String filename = "shared";
	SharedPreferences data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainscreen);

		initialize();
		clicks();
	}

	private void clicks() {
		// TODO Auto-generated method stub
		b1.setOnClickListener(this);
		/*b2.setOnClickListener(this);
		b3.setOnClickListener(this);*/

	}

	private void initialize() {
		// TODO Auto-generated method stub
		et = (EditText) findViewById(R.id.editText1);
		tv = (TextView) findViewById(R.id.textView);
		b1 = (Button) findViewById(R.id.button1);
		/*b2 = (Button) findViewById(R.id.button2);
		b3 = (Button) findViewById(R.id.button3);*/

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			String check = et.getText().toString();
			data = getSharedPreferences(filename, 0);
			String passwordShow = data.getString("pass", "no password saved");
			if (check.contentEquals(passwordShow)) {
				tv.setText("it works");
				Intent in = new Intent("android.intent.action.ALLAPPS");
				startActivity(in);
				

			}
			break;
		/*case R.id.button2:
			tv.setText("");
			Toast.makeText(getApplicationContext(), "Now protecting your apps", Toast.LENGTH_LONG).show();
			startService(new Intent(getBaseContext(),ServiceExample.class));
			Intent inte = new Intent("android.intent.action.APPLICATIONSCREEN");
			startActivity(inte);
			break;*/
//		case R.id.button3:
//			tv.setText("ye b chal rha h");
//			stopService(new Intent(getBaseContext(),ServiceExample.class));
//			break;
		}
	}

}
