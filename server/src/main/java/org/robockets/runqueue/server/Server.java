package org.robockets.runqueue.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

import org.robockets.runqueue.common.SocketWrapper;

public class Server {
	public static Queue<User> userQueue = new LinkedList<User>();
	public static void main(String args[]) {
		// CLI argument handling.
		ServerSocket serverSocket = null;
		// Defaults
		int port = 211; // Texas instruments occupies port 211, but who cares?
		int backlog = 0;
		InetAddress bindAddr = null;
		
		switch(args.length){
		/*
		 * NOTE: No case 0: because that is default.
		 * NOTE: Descending order without breaks because of the logic that the ones below it should be executed.
		 */
			case 3: 
				try {
					InetAddress.getByName(args[2]);
				} catch (UnknownHostException e) {
					System.err.println(args[2]+" is not an Internet Address!");
					e.printStackTrace();
					System.exit(0);
				}
			case 2:
				try {
					backlog = Integer.parseInt(args[1]);
				} catch (NumberFormatException ne){
					System.err.println(args[1]+" is not something that can be cast into a number for the backlog argument.");
					ne.printStackTrace();
					System.exit(0);
				}
			case 1:
				try {
					port = Integer.parseInt(args[0]); // TODO: Port needs more checking (no negative numbers and more).
				} catch (NumberFormatException ne){
					System.err.println(args[0]+" is not something that can be cast into an int for the port.");
					System.exit(0);
				}
		}
		
		try {
			if(bindAddr != null){
				// NOTE: Not dead code, eclipse is wrong.
				serverSocket = new ServerSocket(port, backlog, bindAddr);
			} else {
				serverSocket = new ServerSocket(port, backlog);
			}
		} catch (IOException e){
			e.printStackTrace();
			System.exit(0);
		}
		
		try {
			while (true) {
				Socket connection = serverSocket.accept();
				SocketWrapper wrapper = new SocketWrapper(connection);
				
				String message = wrapper.getMessage();
				
				wrapper.close();
				connection.close(); // TODO: wrapper should close this without me having to.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
