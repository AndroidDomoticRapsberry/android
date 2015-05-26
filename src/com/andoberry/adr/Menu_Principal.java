package com.andoberry.adr;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Destroyable;

import android.app.Activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Menu_Principal extends Activity
implements NavigationDrawerFragment.NavigationDrawerCallbacks, Fragment_TabSwipe.OnFragmentInteractionListener, 
Tutorial.OnFragmentInteractionListener {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	
	private Boolean b = false;
	
	ListView listView;
	
	private final static String TAG_FRAGMENT = "TAG_FRAGMENT";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SharedPreferences settings = getSharedPreferences("prefs", 0);
	    boolean firstRun = settings.getBoolean("firstRun", true);
	    if ( firstRun )
	    {
	        
	        startActivity(
	        		new Intent(this, PrimeraPantalla.class));
	        
	        SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("firstRun", false);
            editor.commit();
	    }
	    
		setContentView(R.layout.activity_menu__principal);

		mNavigationDrawerFragment = (NavigationDrawerFragment)
				getFragmentManager().findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(
				R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));				 
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		
		FragmentManager fragmentManager = getFragmentManager();

		switch (position) {
		case 0:
			fragmentManager.beginTransaction()
			.replace(R.id.container, Fragment_TabSwipe.newInstance(null, null))
			.commit();
			break;
		case 1:
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.es/?gws_rd=ssl")));
			break;
		case 2:
			b = true;
			fragmentManager.beginTransaction()
			.replace(R.id.container, Tutorial.newInstance(null, null))
			.addToBackStack(Fragment_TabSwipe.TAG)
			.commit();
			break;
			
		case 3: 
			Intent intent = new Intent(this, SActivity.class);
			PendingIntent pendingIntent =
			        TaskStackBuilder.create(this)
			                        // add all of DetailsActivity's parents to the stack,
			                        // followed by DetailsActivity itself
			                        .addNextIntentWithParentStack(intent)
			                        .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
			startActivity(intent);
			break;
		}

	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.Home);
			break;
		case 2:
			mTitle = getString(R.string.Web);
			break;
		case 3:
			mTitle = getString(R.string.Help);
			break;
		case 4: 
			mTitle = getString(R.string.Settings);
			break;
		}
	}



	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		//actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.menu__principal, menu);
			restoreActionBar();
			return true;
		}
//		getMenuInflater().inflate(R.menu.menu__principal, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(this, SActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public void sendMessage(View view) {
		Intent intent = new Intent(this, SActivity.class);
		startActivity(intent);
	}
	
	public void EditB(View view) {
		Toast.makeText(this, "Catapumba.", Toast.LENGTH_SHORT).show();
	}
	
	public void bum(View view) {
		Toast.makeText(this, "cowabumba.", Toast.LENGTH_SHORT).show();
		
	}
	
	@Override
	public void onFragmentInteraction(Uri uri) {
		

	}
	
	@Override
	public void onBackPressed() {
		
		if (b){
			super.onBackPressed();
			b = false;
		}
		else {
		new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Salir")
        .setMessage("¿Está usted seguro?")
        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
        	@Override
            public void onClick(DialogInterface dialog, int which) {
        
		Intent intent2 = new Intent(Intent.ACTION_MAIN);
        intent2.addCategory(Intent.CATEGORY_HOME);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent2);
        finish();
        	}
        }).setNegativeButton("No", null).show();
		}
		
	}

}

//public static class PlaceholderFragment extends Fragment {
//	/**
//	 * The fragment argument representing the section number for this
//	 * fragment.
//	 */
//	private static final String ARG_SECTION_NUMBER = "section_number";
//
//	/**
//	 * Returns a new instance of this fragment for the given section
//	 * number.
//	 */
//	public static PlaceholderFragment newInstance(int sectionNumber) {
//		PlaceholderFragment fragment = new PlaceholderFragment();
//		Bundle args = new Bundle();
//		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//		fragment.setArguments(args);
//		return fragment;
//	}
//
//	public PlaceholderFragment() {
//	}
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		View rootView = inflater.inflate(R.layout.fragment_menu__principal, container, false);
//		return rootView;
//	}
//
//	@Override
//	public void onAttach(Activity activity) {
//		super.onAttach(activity);
//		((Menu_Principal) activity).onSectionAttached(
//				getArguments().getInt(ARG_SECTION_NUMBER));
//	}
//}
