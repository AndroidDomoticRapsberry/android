package com.andoberry.adr;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class PrimeraPantalla extends Activity{

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_primera_pantalla);
				if (savedInstanceState == null) {
					getFragmentManager().beginTransaction()
							.add(R.id.container, new PlaceholderFragment()).commit();
				}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.primera_pantalla, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		
		private String url = "https://www.google.es/?gws_rd=ssl";

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_primera_pantalla, container, false);
			return rootView;
		}

		@Override
		public void onViewCreated(View view, Bundle savedInstanceState) {
			TextView textView2;
			textView2 = (TextView) view.findViewById(R.id.textView2);

			textView2.setText(Html.fromHtml("<a href=" + url + ">ADR-WEB</a>"));
			textView2.setMovementMethod(LinkMovementMethod.getInstance());
		}
	}

	public void cotinue(View view) {
		Intent intent = new Intent(this, Menu_Principal.class);
		startActivity(intent);
	}

	@Override
	public void onBackPressed() {
	}




}
