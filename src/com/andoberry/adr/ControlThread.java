package com.andoberry.adr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ControlThread extends Thread{

	String ip = "";
	int serverport;

	Socket soc;
	String ctrl = "control";
	String a = "listener";
	String opt;
	String b, bL1, bL2, bL3, bP1, bP2, bT1, bH1;
	String n1 = null;
	String n2 = null;
	String choice;
	
	String permiso;

	public ControlThread (String ip, int serverport, Socket soc, String bL1, String bL2, String bL3, String opt, String b, String choice){
		this.ip = ip;
		this.serverport = serverport;
		this.soc = soc;
		this.b = b;
		this.bL1 = bL1;
		this.bL2 = bL2;
		this.bL3 = bL3;
		this.opt = opt;
		this.choice = choice;
	}

	public ControlThread (String ip, int serverport, Socket soc, String bP1, String bP2,  String opt, String b, String choice){
		this.ip = ip;
		this.serverport = serverport;
		this.soc = soc;
		this.b = b;
		this.bP1 = bP1;
		this.bP2 = bP2;
		this.opt = opt;
		this.choice = choice;
	}

	public ControlThread (String ip, int serverport, Socket soc, String bT1, String opt, String b, String choice){
		this.ip = ip;
		this.serverport = serverport;
		this.soc = soc;
		this.b = b;
		this.bT1 = bT1;
		this.bH1 = bT1;
		this.opt = opt;
		this.choice = choice;
	}
	
	public ControlThread (String ip, int serverport, Socket soc, String opt, String choice){
		this.ip = ip;
		this.serverport = serverport;
		this.soc = soc;
		this.opt = opt;
		this.choice = choice;
	}


	public void writer (Socket soc, String bL1, String bL2, String bL3, String ctrl, String user, String tag) throws IOException{
		PrintWriter writer = new PrintWriter(soc.getOutputStream());
		writer.println(ctrl);
		writer.flush();
		System.out.println("Control enviado");
		writer.println(user);
		writer.flush();
		System.out.println("Usuario enviado");
		writer.println(tag);
		writer.flush();
		System.out.println("Tag enviada");
		writer.println(bL1);
		writer.flush();
		System.out.println("Luz 1 / Persiana 1 / Termostato / Humidificador enviado");
		if(bL1 != null){
		System.out.println(bL1);
		}
		writer.println(bL2);
		writer.flush();
		System.out.println("Luz 2 / Persiana 2 enviada");
		if(bL2 != null){
		System.out.println(bL2);
		}
		writer.println(bL3);
		writer.flush();
		System.out.println("Luz 3 enviada");
		if (bL3 != null){
		System.out.println(bL3);
		}
	}

	public void listener(Socket soc) throws IOException{
		BufferedReader input = new BufferedReader(new InputStreamReader(this.soc.getInputStream()));
		String permiso = input.readLine();
		setPermiso(permiso);
	}
	
	Boolean tr = true;
	
	public void listener(Socket soc, String L1, String L2, String L3, String P1, String P2, String T, String H) throws IOException{
		if (tr){
		PrintWriter writer = new PrintWriter(soc.getOutputStream());
		writer.println(a);
		writer.flush();
		tr = false;
		}
		System.out.println("Enviando notificación de escucha");
		BufferedReader input = new BufferedReader(new InputStreamReader(this.soc.getInputStream()));
		String l1 = input.readLine();
		setbL1(l1);
		System.out.println("Luz 1 recibida, estado: " + l1);
		String l2 = input.readLine();
		setbL2(l2);
		System.out.println("Luz 2 recibida, estado: " +l2);
		String l3 = input.readLine();
		setbL3(l3);
		System.out.println("Luz 3 recibida, estado: " +l3);
		String p1 = input.readLine();
		setbP1(p1);
		System.out.println("Persiana 1 recibida, estado: " +p1);
		String p2 = input.readLine();
		setbP2(p2);
		System.out.println("Persiana 2 recibida, estado: " +p2);
		String t1 = input.readLine();
		setbT1(t1);
		System.out.println("Termostato recibido, estado: " +t1);
		String h1 = input.readLine();
		setbH1(h1);
		System.out.println("Humidificador recibido, estado: " +h1);
	}

	public String getPermiso() {
		return permiso;
	}

	public void setPermiso(String permiso) {
		this.permiso = permiso;
		System.out.println(this.permiso);
	}

	public String getbL1() {
		return bL1;
	}

	public void setbL1(String bL1) {
		this.bL1 = bL1;
	}

	public String getbL2() {
		return bL2;
	}

	public void setbL2(String bL2) {
		this.bL2 = bL2;
	}

	public String getbL3() {
		return bL3;
	}

	public void setbL3(String bL3) {
		this.bL3 = bL3;
	}

	public String getbP1() {
		return bP1;
	}

	public void setbP1(String bP1) {
		this.bP1 = bP1;
	}

	public String getbP2() {
		return bP2;
	}

	public void setbP2(String bP2) {
		this.bP2 = bP2;
	}
	
	public String getbT1() {
		return bT1;
	}

	public void setbT1(String bT1) {
		this.bT1 = bT1;
	}

	public String getbH1() {
		return bH1;
	}

	public void setbH1(String bH1) {
		this.bH1 = bH1;
	}

	public void write() throws IOException{
		System.out.println("Inside Writer");
		if (b.equalsIgnoreCase("Luces")){
			System.out.println("Inside Luces");
			if (bL1 != null && bL2 != null && bL3 != null){
				writer(soc, bL1, bL2, bL3, ctrl, SocketHandler.getUser(), "luces");
			}
			else {
				if (bL1 != null && bL2 == null && bL3 == null){
					writer(soc, bL1, n1, n2, ctrl, SocketHandler.getUser(),"luz1");
				}
				else {
					if (bL1 == null && bL2 != null && bL3 == null){
						writer(soc, n1, bL2, n2, ctrl, SocketHandler.getUser(),"luz2");
					}
					else {
						if (bL1 == null && bL2 == null && bL3 != null){
							writer(soc, n2, n1, bL3, ctrl, SocketHandler.getUser(),"luz3");
						}
					}
				}
			}
		}
		else {
			if(b.equalsIgnoreCase("Persianas")){
				if(bP1 != null && bP2 != null){
					writer(soc, bP1, bP2, n1, ctrl, SocketHandler.getUser(), "persianas");
				}
				else {
					if(bP1 != null && bP2 == null){
						writer(soc, bP1, n1, n2,  ctrl, SocketHandler.getUser(), "persiana1");
					}
					else {
						if(bP1 == null && bP2 != null){
							writer(soc, n2, bP2, n1, ctrl, SocketHandler.getUser(), "persiana2");
						}
					}
				}
			}
			else {
				if(b.equalsIgnoreCase("Termostato")){
					writer(soc, bT1, n1, n2, ctrl, SocketHandler.getUser(), "termostato");
				}
				else{
					if(b.equalsIgnoreCase("Humidificador")){
						writer(soc, bH1, n1, n2, ctrl, SocketHandler.getUser(), "humidificador");
					}
				}
			}
		}
	}
	
	
	public void listen(String opt) throws IOException{
		if(opt.equalsIgnoreCase("permisos")){
			listener(soc);	
		}
		
		else{
			if (opt.equalsIgnoreCase("lectura")){
				listener(soc, bL1, bL2, bL3, bP1, bP2, bT1, bH1);
			}
		}
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
			
			if (choice.equalsIgnoreCase("Writer")){
				System.out.println("In Writer");
				write();
			}
			else {
				if (choice.equalsIgnoreCase("Listener"))
				listen(opt);
			}
			
			if(opt.equalsIgnoreCase("permisos")){
				listener(soc);	
			}


		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
