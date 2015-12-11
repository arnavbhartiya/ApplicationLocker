package com.example.applock;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class AllAppsActivity extends ListActivity implements OnClickListener {
	private PackageManager packageManager = null;
	private List<ApplicationInfo> applist = null;
	private ApplicationAdapter listadaptor = null;
	public ArrayList<String> protectedApp = null;
	/*static String result = "";
	
	SharedPreferences data;*/
	int i=0;
	Button b;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		packageManager = getPackageManager();
		b= (Button)findViewById(R.id.buttonCheck);
		b.setOnClickListener(this);

		new LoadApplications().execute();
		/*data=getSharedPreferences(result, 0);*/
	}
	
   
		
	

	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		ApplicationInfo app = applist.get(position);
		
		/*if(i==0)
		{*/
			String t = app.packageName+" added";
			try {
				Intent intent = packageManager.getLaunchIntentForPackage(app.packageName);
	
				if (null != intent) {
				
					protectedApp.add(app.packageName);
				}
			} catch (ActivityNotFoundException e) {
				Toast.makeText(AllAppsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				Toast.makeText(AllAppsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
			}
			
		/*protectedApp.add(app.packageName);*/
		Toast.makeText(getApplicationContext(),t , Toast.LENGTH_SHORT).show();
		
		}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this,ApplicationAdapter.class); // need to change.
		Bundle bundle = new Bundle();
		bundle.putStringArrayList("mylist", protectedApp);
		intent.putExtras(bundle);       
		startActivity(intent);
       
        }
		/*if(i==1)
		{
			String r = app.loadLabel(packageManager)+" removed" ;
		
		try {
			Intent intent = packageManager.getLaunchIntentForPackage(app.packageName);

			if (null != intent) {
				protectedApp.remove(app.packageName);
			}
		} catch (ActivityNotFoundException e) {
			Toast.makeText(AllAppsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(AllAppsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
			protectedApp.remove(app.packageName);
			Toast.makeText(getApplicationContext(),r , Toast.LENGTH_LONG).show();
		
		i=0;}*/
		
		/*try {
			Intent intent = packageManager.getLaunchIntentForPackage(app.packageName);

			if (null != intent) {
				startActivity(intent);
			}
		} catch (ActivityNotFoundException e) {
			Toast.makeText(AllAppsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(AllAppsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
		}*/
	
	

	private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
		ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
		for (ApplicationInfo info : list) {
			try {
				if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
					applist.add(info);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return applist;
	}

	private class LoadApplications extends AsyncTask<Void, Void, Void> {
		private ProgressDialog progress = null;

		@Override
		protected Void doInBackground(Void... params) {
			applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
			listadaptor = new ApplicationAdapter(AllAppsActivity.this, R.layout.checkboxes, applist);

			return null;
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected void onPostExecute(Void result) {
			setListAdapter(listadaptor);
			progress.dismiss();
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			progress = ProgressDialog.show(AllAppsActivity.this, null, "Loading application info...");
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}
	}

	
}

/*public class AllAppsActivity extends Activity implements OnClickListener {
    ApplicationAdapter adapter ;
    ApplicationInfo app_info[] ;
       @Override
       protected void onCreate(Bundle savedInstanceState){
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);


           final ListView listApplication = (ListView)findViewById(R.id.list);
           Button b= (Button) findViewById(R.id.button1);
           b.setOnClickListener(this);

           

           

           ApplicationInfo applicationInfo = getApplicationInfo();
           PackageManager pm = getPackageManager();
           List<PackageInfo> pInfo = new ArrayList<PackageInfo>();
           pInfo.addAll(pm.getInstalledPackages(0));
           app_info = new ApplicationInfo[pInfo.size()];

           int counter = 0;
           for(PackageInfo item: pInfo){
               try{

                   applicationInfo = pm.getApplicationInfo(item.packageName, 1);

                   app_info[counter] = new AppInfo(pm.getApplicationIcon(applicationInfo), 
                           String.valueOf(pm.getApplicationLabel(applicationInfo)));

                   System.out.println(counter);

               }
               catch(Exception e){
                    System.out.println(e.getMessage());
               }

               counter++;
           }

          adapter = new AppInfoAdapter(this, R.layout.listview_item_row, app_info);
           listApplication.setAdapter(adapter);

       }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		StringBuilder result = new StringBuilder();
        for(int i=0;i<adapter.mCheckStates.size();i++)
        {
            if(adapter.mCheckStates.get(i)==true)
            {

                               result.append(app_info[i].applicationName);
                result.append("\n");
            }

        }
        Toast.makeText(AllAppsActivity.this, result, 1000).show();
	}
} */