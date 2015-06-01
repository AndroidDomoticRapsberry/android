package com.andoberry.adr;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class SignIn extends Activity {

	EditText user;
	EditText pass; 
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);

		tv = (TextView) findViewById(R.id.textView2);
		user = (EditText) findViewById(R.id.usuario);
		pass = (EditText) findViewById(R.id.contrasena);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_in, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void entrar(View view) throws InterruptedException {
		String us = user.getText().toString();
		String pa = pass.getText().toString();
		String a = "entra";
		String model = android.os.Build.MODEL;
		SocketHandler.setUser(us);
		ClientThread cT = new ClientThread(SocketHandler.getip(), SocketHandler.getPort(), SocketHandler.getSocket(), us, pa, a, model);
		cT.start();
		cT.join();
		String ok = cT.getLoginOk();
		System.out.println(ok);
		if (ok.equalsIgnoreCase("ok") && ok != null){
			tv.setText("");
			Intent intent = new Intent(getBaseContext(), Menu_Principal.class);
			startActivity(intent);
		}
		else {
			tv.setText("Usuario/Contraseña incorrectos. Por favor, inserte un usuario/contraseña correctos");
		}
	}

	//Botón de tirar para atrás
	@Override
	public void onBackPressed() {
		
		super.onBackPressed();
		
//		new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Salir")
//		.setMessage("¿Está usted seguro?")
//		.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//
//				Intent intent2 = new Intent(Intent.ACTION_MAIN);
//				intent2.addCategory(Intent.CATEGORY_HOME);
//				intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				startActivity(intent2);
//				finish();
//			}
//		}).setNegativeButton("No", null).show();
	}
}