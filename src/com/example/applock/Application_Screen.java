package com.example.applock;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Application_Screen extends Activity {
 private ListView lView;
 private ArrayList results = new ArrayList();

 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.applicatiton_screen);
  lView = (ListView) findViewById(R.id.listView1);
  PackageManager pm = this.getPackageManager();

  Intent intent = new Intent(Intent.ACTION_MAIN, null);
  intent.addCategory(Intent.CATEGORY_LAUNCHER);

  List<ResolveInfo> list = pm.queryIntentActivities(intent, PackageManager.PERMISSION_GRANTED);
  for (ResolveInfo rInfo : list) {
   /*results.add(rInfo.activityInfo.applicationInfo.loadLabel(pm).toString());*/
   results.add(rInfo.activityInfo.applicationInfo.packageName.toString());
   Log.w("Installed Applications", rInfo.activityInfo.applicationInfo.loadLabel(pm).toString());
  } 
  lView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, results));
    }
}