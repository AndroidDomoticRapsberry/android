package com.andoberry.adr;


import java.util.List;



import android.app.Activity;

import android.app.ActionBar;
import android.app.AlertDialog;

import android.app.FragmentManager;
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
	Button b;

	public check(Boolean eL, boolean eP, boolean eT, boolean eH, boolean sbL1, Button b){
		this.eL = eL;
		this.eP = eP;
		this.eT = eT;
		this.eH = eH;
		this.sbL1 = sbL1;
		this.b = b;
	}

	@Override
	public void run(){
		try{
			if (eL == false){
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));

			}
			if (eL){
				b.setBackgroundColor(Color.parseColor("#0033FF"));
			}

		} catch(Exception e){
			e.printStackTrace();
		}
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

	//Variables para controlar los estados de las escenas generales.
	static boolean encendidoL = false;
	static boolean encendidoP = false;
	static boolean encendidoT = false;
	static boolean encendidoH = false;

	//Variables para controlar los estados de los eventos.
	static boolean event1 = false;
	static boolean event2 = false;
	static boolean event3 = false;
	static boolean event4 = false;

	//Variables para controlar los estados de las subescenas.
	//Por ejemplo, la luz de la cocina.
	static boolean subLuz1 = false;
	static boolean subLuz2 = false;
	static boolean subLuz3 = false;
	
	//Por ejemplo, la persiana del comedor.
	static boolean subPersiana1 = false;
	static boolean subPersiana2 = false;

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments

		FragmentManager fragmentManager = getFragmentManager();

		switch (position) {
		case 0:	
			fragmentManager.beginTransaction()
			.replace(R.id.container, Fragment_TabSwipe.newInstance(i, ip))
			.commit();
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

	//Método para controlar el estado de los botones.
	public void check(Button b){
	
		boolean eL = encendidoL; 
		boolean eP = encendidoP; 
		boolean eT = encendidoT; 
		boolean eH = encendidoH; 
		boolean ev1 = event1; 
		boolean sbL1 = subLuz1;
		boolean sbL2 = subLuz2;
		boolean sbL3 = subLuz3;
		boolean sbP1 = subPersiana1;
		boolean sbP2 = subPersiana2;
		

		String t = (String) b.getTag();

		switch(t){
		case "Luces": 
			if (eL == false){
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				
			}
			else{
				b.setBackgroundColor(Color.parseColor("#00FF00"));
			}
			break;
		case "Persianas":
			if (eP == false){
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));

			}
			else{
				b.setBackgroundColor(Color.parseColor("#00FF00"));
			}
			break;
		case "Termostato":
			if (eT == false){
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
			}
			else{

				b.setBackgroundColor(Color.parseColor("#00FF00"));
			}
			break;
		case "Humidificador":
			if (eH == false){
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
			}
			else{

				b.setBackgroundColor(Color.parseColor("#00FF00"));
			}
			break;
		case "Luces1":
			if (sbL1 == false){
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
			}
			else{
				b.setBackgroundColor(Color.parseColor("#00FF00"));				
			}
			break;
		case "Luces2":
			if (sbL2 == false){
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
			}
			else{
				b.setBackgroundColor(Color.parseColor("#00FF00"));				
			}
			break;
		case "Luces3":
			if (sbL3 == false){
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
			}
			else{
				b.setBackgroundColor(Color.parseColor("#00FF00"));				
			}
			break;
		case "Persiana1":
			if (sbP1 == false){
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
			}
			else{
				b.setBackgroundColor(Color.parseColor("#00FF00"));				
			}
			break;
		case "Persiana2":
			if (sbP2 == false) {
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
			}
			else{
				b.setBackgroundColor(Color.parseColor("#00FF00"));				
			}
			
		}
		//END OF SWITCH
	}


	public void checkT(Button b, TextView t, boolean trm){
		
		if (!trm){
			b.setBackgroundColor(Color.parseColor("#00FF00"));
			b.setTextColor(Color.parseColor("#000000"));
			b.setText("ENCENDIDO");
			t.setText("20");
			trm = true;
		}
		else{
			b.setBackgroundColor(Color.parseColor("#CCCCCC"));
			b.setTextColor(Color.parseColor("#000000"));
			b.setText("APAGADO");
			t.setText("0");
			trm = false; 
		}
		
	}
	
	//Boton para controlar las diferentes escenas y eventos. 
	public void bum(View view) {
		tv = (TextView) findViewById(R.id.TextView03);
		Button b = (Button) view;

		String t = (String) b.getTag();

		switch(t){
		case "Luces": 

			if (encendidoL == false){
				encendidoL = true;
				b.setBackgroundColor(Color.parseColor("#00FF00"));
				break;

			}
			else{
				encendidoL = false; 
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				break;

			}
		case "Persianas":
			
			if (encendidoP == false){
				encendidoP = true;
				b.setBackgroundColor(Color.parseColor("#00FF00"));
				break;
			}
			else{
				encendidoP = false; 
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				break;
			}

		case "Termostato":
			
			if (encendidoT == false){
				encendidoT = true;
				b.setBackgroundColor(Color.parseColor("#00FF00"));
				break;
			}
			else{
				encendidoT = false; 
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				break;
			}

		case "Humidificador":
			
			if (encendidoH == false){
				encendidoH = true;
				b.setBackgroundColor(Color.parseColor("#00FF00"));
				break;
			}
			else{
				encendidoH = false; 
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				break;
			}
		case "Fiestuki":
			
			if (event1 == false){
				event1 = true;
				b.setBackgroundColor(Color.parseColor("#00FF00"));
				break;
			}
			else{
				event1 = false; 
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				break;
			}

		case "Luces1": 
			
			if (subLuz1 == false){
				subLuz1 = true;
				b.setBackgroundColor(Color.parseColor("#00FF00"));
				break;
			}
			else{
				subLuz1 = false; 
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				break;
			}
		case "Luces2":
			
			if (subLuz2 == false){
				subLuz2 = true;
				b.setBackgroundColor(Color.parseColor("#00FF00"));
				break;
			}
			else {
				subLuz2 = false;
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				break;
			}
		case "Luces3": 
			
			if (subLuz3 == false){
				subLuz3 = true;
				b.setBackgroundColor(Color.parseColor("#00FF00"));
				break;
			}
			else {
				subLuz3 = false;
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				break;
			}
		case "Persiana1":
			
			if (subPersiana1 == false){
				subPersiana1 = true;
				b.setBackgroundColor(Color.parseColor("#00FF00"));
				break;
			}
			else {
				subPersiana1 = false;
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				break;
			}
		case "Persiana2":
			
			if (subPersiana2 == false){
				subPersiana2 = true;
				b.setBackgroundColor(Color.parseColor("#00FF00"));
				break;
			}
			else {
				subPersiana2 = false;
				b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				break;
			}
		}
		//END OF SWITCH
	}

	//Botón de tirar para atrás
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