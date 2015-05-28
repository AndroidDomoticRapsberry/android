package com.andoberry.adr;


import java.util.List;



import android.app.Activity;

import android.app.ActionBar;
import android.app.AlertDialog;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

class check extends Thread{
	
	boolean eL; 
	boolean eP; 
	boolean eT; 
	boolean eH; 
	boolean ev1; 
	boolean sbL1; 
	
	public check(Boolean eL, boolean eP, boolean eT, boolean eH, boolean sbL1){
		
	}
	
}


public class Menu_Principal extends Activity
implements NavigationDrawerFragment.NavigationDrawerCallbacks, Fragment_TabSwipe.OnFragmentInteractionListener, 
Tutorial.OnFragmentInteractionListener, FragmentComm, Raspberry_Connect.OnFragmentInteractionListener, Scenes_Config.OnFragmentInteractionListener,
Termostato.OnFragmentInteractionListener, Humidificador.OnFragmentInteractionListener{

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
	TextView tv;
	String i = "patata";
	String getData;
	
	Fragment_TabSwipe f_ts;

	private final static String tac = "ListViewFrag";

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

	Boolean edit = false; 
	CharSequence options[] = new CharSequence[] {"Luces", "Persianas", "Termostato", "Humidificador"};


	public void EditB(View view) {
		if (!edit){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Pick an option");
			builder.setItems(options, new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			        
			    }
			});
			builder.show();
			Toast.makeText(this, "Cata.", Toast.LENGTH_SHORT).show();
			edit = true;
		}
		else {
			Toast.makeText(this, "Pumba.", Toast.LENGTH_SHORT).show();
			edit = false;
		}
	}
	
	String ip = null;
	
	@Override
	public void onFragmentInteraction(String str) {
		ip = str;
	}
	
	boolean engaged = false;
	
	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments

		FragmentManager fragmentManager = getFragmentManager();
		
		switch (position) {
		case 0:	
			fragmentManager.beginTransaction()
			.replace(R.id.container, Fragment_TabSwipe.newInstance(i, ip))
			.commit();
			engaged = true;
			break;
		case 1:
			new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Ir a la Web")
			.setMessage("¿Está usted seguro?")
			.setPositiveButton("Sí", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.es/?gws_rd=ssl")));
				}

			}).setNegativeButton("No", null).show();

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
	public void check(){
		//Menu_Principal p = new Menu_Principal();
		
		
		Button b = (Button) findViewById(R.id.Button03);
		
		
		//if (eL == false){
		//	b.setBackgroundColor(Color.parseColor("#CCCCCC"));
			//Toast.makeText(this, "Encendido.", Toast.LENGTH_SHORT).show();
		}
		//if (eL){
	//	b.setBackgroundColor(Color.parseColor("#0033FF"));
	//	}
//		if (eP == false){
//			b.setBackgroundColor(Color.parseColor("#CCCCCC"));
//			//Toast.makeText(this, "Encendido.", Toast.LENGTH_SHORT).show();
//		}
//		if (eP){
//			b.setBackgroundColor(Color.parseColor("#00FF00"));
//		}
//		if (eT == false){
//			b.setBackgroundColor(Color.parseColor("#00FF00"));
//			//Toast.makeText(this, "Encendido.", Toast.LENGTH_SHORT).show();
//		}
//		if (eT){
//			b.setBackgroundColor(Color.parseColor("#CCCCCC"));
//		}
//		if (eH == false){
//			b.setBackgroundColor(Color.parseColor("#00FF00"));
//			//Toast.makeText(this, "Encendido.", Toast.LENGTH_SHORT).show();
//		}
//		if (eH){
//			b.setBackgroundColor(Color.parseColor("#CCCCCC"));
//		}
//		if (sbL1 == false){
//			b.setBackgroundColor(Color.parseColor("#00FF00"));
//			//Toast.makeText(this, "Encendido.", Toast.LENGTH_SHORT).show();
//		}
//		if (sbL1){
//			b.setBackgroundColor(Color.parseColor("#CCCCCC"));
//		}
//	}

	boolean encendidoL = false;
	boolean encendidoP = false;
	boolean encendidoT = false;
	boolean encendidoH = false;
	boolean event1 = false;
	boolean event2 = false;
	boolean event3 = false;
	boolean event4 = false;
	boolean subLuz1 = false;
	
	
	
	public void bum(View view) {
		tv = (TextView) findViewById(R.id.TextView03);
		Button b = (Button) view;
		
		String t = (String) b.getTag();
		
		

		switch(t){
		case "Luces": 
			Toast.makeText(this, "Tensa Zangetsu.", Toast.LENGTH_SHORT).show();
			if (encendidoL == false){
				encendidoL = true;
				b.setBackgroundColor(Color.parseColor("#00FF00"));
				//check();
				break;
				
			}
			if (encendidoL){
				encendidoL = false; 
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				//check();
				break;
				
			}
			break;
		case "Persianas":
			Toast.makeText(this, "Emperor's eye.", Toast.LENGTH_SHORT).show();
			if (encendidoP == false){
				encendidoP = true;
				b.setBackgroundColor(Color.parseColor("#00FF00"));
				break;
			}
			if (encendidoP){
				encendidoP = false; 
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				break;
			}
			break;
		case "Termostato":
			Toast.makeText(this, "Invisibility.", Toast.LENGTH_SHORT).show();
			if (encendidoT == false){
				encendidoT = true;
				b.setBackgroundColor(Color.parseColor("#00FF00"));
				break;
			}
			if (encendidoT){
				encendidoT = false; 
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				break;
			}
			break;
		case "Humidificador":
			Toast.makeText(this, "ZONE.", Toast.LENGTH_SHORT).show();
			if (encendidoH == false){
				encendidoH = true;
				b.setBackgroundColor(Color.parseColor("#00FF00"));
				break;
			}
			if (encendidoH){
				encendidoH = false; 
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				break;
			}
			break;
		case "Fiestuki":
			Toast.makeText(this, "FIESHTA.", Toast.LENGTH_SHORT).show();
			if (event1 == false){
				event1 = true;
				b.setBackgroundColor(Color.parseColor("#00FF00"));
				break;
			}
			if (event1){
				event1 = false; 
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				break;
			}
			break;
		case "Luces1": 
			Toast.makeText(this, "subEscena1", Toast.LENGTH_SHORT).show();
			if (subLuz1 == false){
				subLuz1 = true;
				b.setBackgroundColor(Color.parseColor("#00FF00"));
				break;
			}
			if (subLuz1){
				subLuz1 = false; 
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				break;
			}
			break;
		}
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
	
	@Override
	public void onFragmentInteraction(Uri uri) {

		
	}
	
	@Override
	public void onFragmentBInteraction(Bundle uri) {

		
	}
	
	
	@Override
	public void onFragmentTabSwipeInteraction(List<ScenesController> listScenes){
		
	}

	@Override
	public ScenesControllerAdapter updateList(List<ScenesController> listScenes) {
		
		return null;
	}

}