package com.andoberry.adr;


import java.net.Socket;
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

public class Menu_Principal extends Activity
implements NavigationDrawerFragment.NavigationDrawerCallbacks, Fragment_TabSwipe.OnFragmentInteractionListener, 
Tutorial.OnFragmentInteractionListener, Scenes_Config.OnFragmentInteractionListener,
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_menu__principal);

		mNavigationDrawerFragment = (NavigationDrawerFragment)
				getFragmentManager().findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(
				R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));		
	}

	CharSequence options[] = new CharSequence[] {"Luces", "Persianas", "Termostato", "Humidificador"};

	public void EditB(View view) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Pick an option");
		builder.setItems(options, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				for (int i = 0; i < options.length; i++){
					String opt = options[i].toString();
					if (opt.equalsIgnoreCase("luces")){
						FragmentManager fragmentManager = getFragmentManager();
						fragmentManager.beginTransaction()
						.replace(R.id.container, Tutorial.newInstance(null, null))
						.addToBackStack(Fragment_TabSwipe.TAG)
						.commit();
					}
					else {
						if (opt.equalsIgnoreCase("persianas")){
							FragmentManager fragmentManager = getFragmentManager();
							fragmentManager.beginTransaction()
							.replace(R.id.container, Fragment_TabSwipe.newInstance(null, null))
							.addToBackStack(Fragment_TabSwipe.TAG)
							.commit();
						}
					}
				}
			}
		});
		builder.show();		
	}

	String ip = null;


	//Variables para controlar los estados de las escenas generales.
	static Boolean encendidoL = null;
	static Boolean encendidoP = null;
	static Boolean encendidoT = null;
	static Boolean encendidoH = null;

	//Variables para controlar los estados de los eventos.
	static Boolean event1 = null;
	static Boolean event2 = null;
	static Boolean event3 = null;
	static Boolean event4 = null;

	//Variables para controlar los estados de las subescenas.
	//Por ejemplo, la luz de la cocina.
	static Boolean subLuz1 = null;
	static Boolean subLuz2 = null;
	static Boolean subLuz3 = null;

	//Por ejemplo, la persiana del comedor.
	static Boolean subPersiana1 = null;
	static Boolean subPersiana2 = null;

	//Por ejemplo, el termostato
	static Boolean tr = null;
	static Boolean hd = null;

	
	/**
	 * Carga los fragments
	 */
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
			.setMessage("�Est� usted seguro?")
			.setPositiveButton("S�", new DialogInterface.OnClickListener() {

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

	/**
	 * Pone el titulo en el men� lateral
	 * @param number
	 */
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



	/**
	 * Devuelve a su estado anterior la barra lateral
	 */
	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		//actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}


	/**
	 * Carga la action bar(Barra lateral).
	 */
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

	
	/**
	 * M�todo para el bot�n de settings de arriba a la derecha
	 */
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
	 * M�todo para controlar el estado de los botones.
	 * @param b
	 * @throws InterruptedException
	 */
	public void check(Button b) throws InterruptedException{
		ControlThread cT = new ControlThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), "lectura", "Listener");
		cT.start();
		cT.join();

		String l1 = cT.getbL1();
		System.out.println("luz" + l1);
		String l2 = cT.getbL2();
		System.out.println("luz" + l2);
		String l3 = cT.getbL3();
		System.out.println(" luz " +l3);
		String p1 = cT.getbP1();
		String p2 = cT.getbP2();
		String t1 = cT.getbT1();
		String h = cT.getbH1();

		//boolean eL = encendidoL; 
		//boolean eP = encendidoP; 
//		boolean eT = Boolean.parseBoolean(t1);
//		boolean eH = Boolean.parseBoolean(h); 
//		boolean ev1 = event1; 
//		boolean sbL1 = Boolean.parseBoolean(l1);
//		boolean sbL2 = Boolean.parseBoolean(l2);
//		boolean sbL3 = Boolean.parseBoolean(l3);
//		boolean sbP1 = Boolean.parseBoolean(p1);
//		boolean sbP2 = Boolean.parseBoolean(p2);

		String t = (String) b.getTag();
		if((l1.equals("true") || l1.equals("false")) && (l2.equals("true") || l2.equals("false")) && (l3.equals("true") || l3.equals("false")) 
				&& (p1.equals("true") || p1.equals("false"))
				&& (p2.equals("true") || p2.equals("false"))){
			System.out.println("Luz " + l1);
			System.out.println("Luz " +l2);
			System.out.println("Luz " +l3);
			System.out.println("Aqu� no");
			switch(t){
			case "Luces": 
				if (l1.equals("false") && l2.equals("false") && l3.equals("false")){
					b.setBackgroundColor(Color.parseColor("#CCCCCC"));
					System.out.println("Luz1 " + l1);
					encendidoL = false;
					subLuz1 = encendidoL;
					subLuz2 = encendidoL;
					subLuz3 = encendidoL;
					break;
				}
				else{
					b.setBackgroundColor(Color.parseColor("#00FF00"));
					System.out.println("Luz1 " + l1);
					encendidoL = true;
					subLuz1 = encendidoL;
					subLuz2 = encendidoL;
					subLuz3 = encendidoL;
					break;
				}

			case "Persianas":
				if (p1.equals("false") && p2.equals("false")){
					b.setBackgroundColor(Color.parseColor("#CCCCCC"));
					encendidoP = false;
					subPersiana1 = encendidoP;
					subPersiana2 = encendidoP;
					break;
				}
				else{
					b.setBackgroundColor(Color.parseColor("#00FF00"));
					encendidoP = true;
					subPersiana1 = encendidoP;
					subPersiana2 = encendidoP;
					break;
				}
			case "Termostato":
				if (t1.equals("false")){
					b.setBackgroundColor(Color.parseColor("#CCCCCC"));
					encendidoT = false;
					break;
				}
				else{
					b.setBackgroundColor(Color.parseColor("#00FF00"));
					encendidoT = true;
					break;
				}

			case "Humidificador":
				if (h.equals("false")){
					b.setBackgroundColor(Color.parseColor("#CCCCCC"));
					encendidoH = false;
					break;
				}
				else{
					b.setBackgroundColor(Color.parseColor("#00FF00"));
					encendidoH = true;
					break;
				}

			case "Luces1":
				if (l1.equals("false")){
					b.setBackgroundColor(Color.parseColor("#CCCCCC"));
					subLuz1 = false;
					break;
				}
				else{
					b.setBackgroundColor(Color.parseColor("#00FF00"));
					subLuz1 = true;
					break;
				}

			case "Luces2":
				if (l2.equals("false")){
					b.setBackgroundColor(Color.parseColor("#CCCCCC"));
					subLuz2 = false;
					break;
				}
				else{
					b.setBackgroundColor(Color.parseColor("#00FF00"));	
					subLuz2 = true;
					break;
				}

			case "Luces3":
				if (l3.equals("false")){
					b.setBackgroundColor(Color.parseColor("#CCCCCC"));
					subLuz3 = false;
					break;
				}
				else{
					b.setBackgroundColor(Color.parseColor("#00FF00"));	
					subLuz3 = true;
					break;
				}

			case "Persiana1":
				if (p1.equals("false")){
					b.setBackgroundColor(Color.parseColor("#CCCCCC"));
					subPersiana1 = false;
					break;
				}
				else{
					b.setBackgroundColor(Color.parseColor("#00FF00"));		
					subPersiana1 = true;
					break;
				}

			case "Persiana2":
				if (p2.equals("false")) {
					b.setBackgroundColor(Color.parseColor("#CCCCCC"));
					subPersiana2 = false;
					break;
				}
				else{
					b.setBackgroundColor(Color.parseColor("#00FF00"));			
					subPersiana2 = true;
					break;
				}

			}
			//END OF SWITCH
		}
	}



	/**
	 * Boton para controlar las diferentes escenas y eventos. 
	 * @param view
	 * @throws InterruptedException
	 */
	public void bum(View view) throws InterruptedException {
		tv = (TextView) findViewById(R.id.TextView03);
		Button b = (Button) view;

		String t = (String) b.getTag();

		switch(t){
		case "Luces": 
			String StL1 = String.valueOf(subLuz1);
			String StL2 = String.valueOf(subLuz2);
			String StL3 = String.valueOf(subLuz3);
			ControlThread cT = new ControlThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), StL1, StL2, StL3, "Permisos", "Luces", "Writer");
			cT.start();
			cT.join();
			String perm = cT.getPermiso();
			if (StL1.equals("false") && StL2.equals("false") && StL3.equals("false")){
				if (perm.equalsIgnoreCase("vision")){
					Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
				}
				else {
					check(b);
					//b.setBackgroundColor(Color.parseColor("#00FF00"));
				}
			}
			else{
				StL1 = String.valueOf(subLuz1);
				StL2 = String.valueOf(subLuz2);
				StL3 = String.valueOf(subLuz3);

				cT = new ControlThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), StL1, StL2, StL3, "Permisos", "Luces", "Writer");
				cT.start();
				cT.join();
				perm = cT.getPermiso();
				if (perm.equalsIgnoreCase("vision")){
					Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
				}
				else {
					check(b);
					//b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				}

			}
			break;
		case "Persianas":
			String StP1 = String.valueOf(subPersiana1);
			String StP2 = String.valueOf(subPersiana2);
			cT = new ControlThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), StP1, StP2, "Permisos", "Persianas", "Writer");
			cT.start();
			cT.join();
			perm = cT.getPermiso();
			if (StP1.equals("false") && StP2.equals("false")){
				if (perm.equalsIgnoreCase("vision")){
					Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
				}
				else {
					check(b);
					//b.setBackgroundColor(Color.parseColor("#00FF00"));
				}


			}
			else{
				StP1 = String.valueOf(subLuz1);
				StP2 = String.valueOf(subLuz2);
				cT = new ControlThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), StP1, StP2, "Permisos", "Persianas", "Writer");
				cT.start();
				cT.join();
				perm = cT.getPermiso();
				if (perm.equalsIgnoreCase("vision")){
					Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
				}
				else {
					check(b);
					//b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				}


			}
			break;
		case "Termostato":
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
			.replace(R.id.container, Termostato.newInstance(null, null))
			.addToBackStack(null)
			.commit();
			break;


		case "Humidificador":

			FragmentManager fManager = getFragmentManager();
			fManager.beginTransaction()
			.replace(R.id.container, Humidificador.newInstance(null, null))
			.addToBackStack(null)
			.commit();
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
			StL1 = String.valueOf(subLuz1);
			StL2 = null;
			StL3 = null;
			cT = new ControlThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), StL1, StL2, StL3, "Permisos", "Luces", "Writer");
			cT.start();
			cT.join();
			perm = cT.getPermiso();
			if (subLuz1 == false){
				if (perm.equalsIgnoreCase("vision")){
					Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
				}
				else {
					check(b);
					//b.setBackgroundColor(Color.parseColor("#00FF00"));
				}
				break;
			}
			else{
				StL1 = String.valueOf(subLuz1);
				StL2 = null;
				StL3 = null;
				cT = new ControlThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), StL1, StL2, StL3, "Permisos", "Luces", "Writer");
				cT.start();
				cT.join();
				perm = cT.getPermiso();
				if (perm.equalsIgnoreCase("vision")){
					Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
				}
				else{
					check(b);
					//b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				}

				break;
			}
		case "Luces2":
			StL2 = String.valueOf(subLuz2);
			StL1 = null;
			StL3 = null;
			cT = new ControlThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), StL1, StL2, StL3, "Permisos", "Luces", "Writer");
			cT.start();
			cT.join();
			perm = cT.getPermiso();
			if (subLuz2 == false){
				if (perm.equalsIgnoreCase("vision")){
					Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
				}
				else {
					check(b);
					//b.setBackgroundColor(Color.parseColor("#00FF00"));
				}
				break;
			}
			else {
				StL2 = String.valueOf(subLuz2);
				StL1 = null;
				StL3 = null;
				cT = new ControlThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), StL1, StL2, StL3, "Permisos", "Luces", "Writer");
				cT.start();
				cT.join();
				perm = cT.getPermiso();
				if (perm.equalsIgnoreCase("vision")){
					Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
				}
				else{
					check(b);
					//b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				}

				break;
			}
		case "Luces3": 
			StL3 = String.valueOf(subLuz3);
			StL1 = null;
			StL2 = null;
			cT = new ControlThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), StL1, StL2, StL3, "Permisos", "Luces", "Writer");
			cT.start();
			cT.join();
			perm = cT.getPermiso();
			if (subLuz3 == false){
				if (perm.equalsIgnoreCase("vision")){
					Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
				}
				else{
					check(b);
					//b.setBackgroundColor(Color.parseColor("#00FF00"));
				}
				break;
			}
			else {
				StL3 = String.valueOf(subLuz3);
				StL1 = null;
				StL2 = null;
				cT = new ControlThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), StL1, StL2, StL3, "Permisos", "Luces", "Writer");
				cT.start();
				cT.join();
				perm = cT.getPermiso();
				if (perm.equalsIgnoreCase("vision")){
					Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
				}
				else{
					check(b);
					//b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				}
				break;
			}
		case "Persiana1":
			StP1 = String.valueOf(subPersiana1);
			StP2 = null;
			cT = new ControlThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), StP1, StP2, "Permisos", "Persianas", "Writer");
			cT.start();
			cT.join();
			perm = cT.getPermiso();
			if (subPersiana1 == false){
				if (perm.equalsIgnoreCase("vision")){
					Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
				}
				else{
					check(b);
					//b.setBackgroundColor(Color.parseColor("#00FF00"));
				}
				break;
			}
			else {
				StP1 = String.valueOf(subPersiana1);
				StP2 = null;
				cT = new ControlThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), StP1, StP2, "Permisos", "Persianas", "Writer");
				cT.start();
				cT.join();
				perm = cT.getPermiso();
				if (perm.equalsIgnoreCase("vision")){
					Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
				}
				else{
					check(b);
					//b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				}
				break;
			}
		case "Persiana2":
			StP2 = String.valueOf(subPersiana2);
			StP1 = null;
			cT = new ControlThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), StP1, StP2, "Permisos", "Persianas", "Writer");
			cT.start();
			cT.join();
			perm = cT.getPermiso();
			if (subPersiana2 == false){
				if (perm.equalsIgnoreCase("vision")){
					Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
				}
				else{
					check(b);
					//b.setBackgroundColor(Color.parseColor("#00FF00"));
				}
				break;
			}
			else {
				StP2 = String.valueOf(subPersiana2);
				StP1 = null;
				cT = new ControlThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), StP1, StP2, "Permisos", "Persianas", "Writer");
				cT.start();
				cT.join();
				perm = cT.getPermiso();
				if (perm.equalsIgnoreCase("vision")){
					Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
				}
				else{
					check(b);
					//b.setBackgroundColor(Color.parseColor("#CCCCCC"));
				}
				break;
			}
		}
		//END OF SWITCH
	}

	//Bot�n de tirar para atr�s
	@Override
	public void onBackPressed() {

		if (b){
			super.onBackPressed();
			b = false;
		}
		else {
			new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Salir")
			.setMessage("�Est� usted seguro?")
			.setPositiveButton("S�", new DialogInterface.OnClickListener() {
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
	public void onFragmentInteraction(boolean trm) {
		tr = trm;
		hd = trm;
	}


	@Override
	public void onFragmentTabSwipeInteraction(List<ScenesController> listScenes) {
		// TODO Auto-generated method stub

	}

}