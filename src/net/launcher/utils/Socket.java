package net.launcher.utils;

import java.io.IOException;
import java.net.ServerSocket;

public class Socket extends Thread {

	   private final ServerSocket socket;


	   public Socket(ServerSocket Sock) {
	      this.socket = Sock;
	   }

	   public void run() {
	      while(true) {
	         try {
	            while(true) {
	               this.socket.accept();
	            }
	         } catch (IOException var2) {}
	      }
	   }
}
