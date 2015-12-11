package com.example.applock;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;

public class RunningAppInfo {
private Context context;
public RunningAppInfo(Context context) {
	// TODO Auto-generated constructor stub
	this.context=context;
}
public List<RunningAppProcessInfo> StartRunngInfo(){
	 
	 
	ActivityManager activity = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
	List<RunningAppProcessInfo> info = activity.getRunningAppProcesses();
	return info;
		}
	
}
/*public class MainActivity extends Activity {
    AppInfoAdapter adapter ;
    AppInfo app_info[] ;
       @Override
       protected void onCreate(Bundle savedInstanceState){
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);


           final ListView listApplication = (ListView)findViewById(R.id.listApplication);
           Button b= (Button) findViewById(R.id.button1);
           b.setOnClickListener(new OnClickListener()
           {

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
                   Toast.makeText(MainActivity.this, result, 1000).show();
               }

           });

           ApplicationInfo applicationInfo = getApplicationInfo();
           PackageManager pm = getPackageManager();
           List<PackageInfo> pInfo = new ArrayList<PackageInfo>();
           pInfo.addAll(pm.getInstalledPackages(0));
           app_info = new AppInfo[pInfo.size()];

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
} */