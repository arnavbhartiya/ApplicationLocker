package com.example.applock;

import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ApplicationAdapter extends ArrayAdapter<ApplicationInfo> /*implements OnCheckedChangeListener*/{
	/*SparseBooleanArray mCheckStates;*/	
	private List<ApplicationInfo> appsList = null;
	private Context context;
	private PackageManager packageManager;

	public ApplicationAdapter(Context context, int textViewResourceId,
			List<ApplicationInfo> appsList) {
		super(context, textViewResourceId, appsList);
		this.context = context;
		this.appsList = appsList;
		packageManager = context.getPackageManager();
		/*mCheckStates= new SparseBooleanArray(appsList.size());*/
	}

	/*@Override
	public int getCount() {
		return ((null != appsList) ? appsList.size() : 0);
	}

	@Override
	public ApplicationInfo getItem(int position) {
		return ((null != appsList) ? appsList.get(position) : null);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}*/
	/*private static class ApplicationHolder {
		public TextView appName;
		public TextView packageName;
		public ImageView iconview;
		
		public CheckBox check;
		
	}*/
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		/*ApplicationHolder holder = new ApplicationHolder();*/
		if (null == view) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.checkboxes, null);
		}

		ApplicationInfo applicationInfo = appsList.get(position);
		if (null != applicationInfo) {
			TextView appName = (TextView) view.findViewById(R.id.app_name);
			TextView packageName = (TextView) view.findViewById(R.id.app_paackage);
			ImageView iconview = (ImageView) view.findViewById(R.id.app_icon);
			/*check = (CheckBox) view.findViewById(R.id.checkbox);
			check.setOnCheckedChangeListener((AllAppsActivity)context);*/
			
			
			appName.setText(applicationInfo.loadLabel(packageManager));
			packageName.setText(applicationInfo.packageName);
			iconview.setImageDrawable(applicationInfo.loadIcon(packageManager));
			/*check.setTag(position);
			check.setChecked(mCheckStates.get(position, false));
			check.setOnCheckedChangeListener(this);*/
		}
		return view;
	}
	 /* public boolean isChecked(int position) {
	        return mCheckStates.get(position, false);
	    }

	    public void setChecked(int position, boolean isChecked) {
	        mCheckStates.put(position, isChecked);

	    }

	    public void toggle(int position) {
	        setChecked(position, !isChecked(position));

	    }
	@Override
	public void onCheckedChanged(CompoundButton buttonView,
	        boolean isChecked) {

	     mCheckStates.put((Integer) buttonView.getTag(), isChecked);    

	}*/
};