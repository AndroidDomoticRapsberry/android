package com.andoberry.adr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread extends Thread{

	String ip = "";
	int serverport;

	Socket soc;
	String us, pa, a, model;
	String test;
	
	String loginOk;

	public ClientThread (String ip, int serverport, Socket soc, String a){
		this.ip = ip;
		this.serverport = serverport;
		this.soc = soc;
		this.a = a;
	}

	public ClientThread(String ip, int serverport, Socket soc, String us, String pa, String a, String model){
		this.ip = ip;
		this.serverport = serverport;
		this.soc = soc;
		this.a = a;
		this.model = model;
		this.us = us;
		this.pa = pa;
	}

	public void writer(Socket soc, String us, String pa, String a){
		try {
			PrintWriter writer = new PrintWriter(soc.getOutputStream());	
			System.out.println(a);
			writer.println(a);
			writer.flush();
			System.out.println("enviando entra");
			writer.println(model);
			writer.flush();
			System.out.println("Modelo del dispositivo enviado.");
			writer.println(us);
			writer.flush();
			System.out.println("Enviando usuario: " + us);
			writer.println(pa);
			writer.flush();
			System.out.println("Enviando contraseña: " + pa);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
		System.out.println(this.test);
	}

	public String getLoginOk() {
		return loginOk;
	}

	public void setLoginOk(String loginOk) {
		this.loginOk = loginOk;
	}

	@Override
	public void run() {

		try {
			InetAddress serverAddr = InetAddress.getByName(ip);
			System.out.println("Creating Socket");
			soc = new Socket();
			soc.connect(new InetSocketAddress(serverAddr, serverport), 8000);

			System.out.println("Socket Created");

			System.out.println("TimeOut over");
			PrintWriter writer = new PrintWriter(soc.getOutputStream());
			BufferedReader input = new BufferedReader(new InputStreamReader(this.soc.getInputStream()));
			if (a != null && us == null && pa == null){
				System.out.println("Testing if IP is good.");
				System.out.println(a);
				writer.println(a);
				writer.flush();
				test = input.readLine();
				setTest(test);
				//System.out.println(permiso);

				//listener(soc);
			}
			else {
				if (us != null && pa != null){
					writer(soc, us, pa, a);
					String read = input.readLine();
					setLoginOk(read);
				}

			}

		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
