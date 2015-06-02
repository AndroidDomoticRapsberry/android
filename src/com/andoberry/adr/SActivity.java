package com.andoberry.adr;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		getFragmentManager().beginTransaction()
		.replace(android.R.id.content, new MainSettingsFragment()).commit();
	}
	
	public static class MainSettingsFragment extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.pref_notification);
		}
	}
	
}
