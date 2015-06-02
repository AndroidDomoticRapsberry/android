package com.andoberry.adr;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity {

	EditText et1;

	private Socket socket;
	
	TextView tv;

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
		
		setContentView(R.layout.activity_login);
		tv = (TextView) findViewById(R.id.textView2);
		et1 = (EditText) findViewById(R.id.editText2);
		et1.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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

	public void connect(View view) throws InterruptedException {
		String IP = et1.getText().toString();
		String a = "hola";
		SocketHandler.setSocket(socket);
		SocketHandler.setIp(IP);
		ClientThread cT = new ClientThread(IP, SocketHandler.getPort(), SocketHandler.getSocket(), a);
		System.out.println("cT created");
		cT.start();
		cT.join();
		String p = cT.getTest();
		if (p == null){
			System.out.println("arios");
			tv.setText("Esta IP es invalida. Por favor, inserte una correcta");
		}
		else {
			tv.setText("");
			Intent intent = new Intent(getBaseContext(), SignIn.class);
			startActivity(intent);	
		}
	}

	//Botón de tirar para atrás
	@Override
	public void onBackPressed() {

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