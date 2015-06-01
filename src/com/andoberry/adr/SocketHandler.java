package com.andoberry.adr;

import java.net.Socket;

public class SocketHandler {
	private static Socket socket;
	private static String ip;
	private static final int SERVERPORT = 9999;
	
	private static String user;
	

    public static synchronized Socket getSocket(){
        return socket;
    }
    
    public static synchronized String getip(){
        return ip;
    }
    
    public static synchronized int getPort(){
        return SERVERPORT;
    }

    public static synchronized void setSocket(Socket socket){
        SocketHandler.socket = socket;
    }
    
    public static synchronized void setIp(String ip){
        SocketHandler.ip = ip;
    }
    
    public static synchronized void setUser(String user){
        SocketHandler.user = user;
    }
    
    public static synchronized String getUser(){
    	return user;
    }
    
}
