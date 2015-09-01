package org.robockets.runqueue.server;

import java.io.IOException;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import java.util.LinkedList;
import java.util.Queue;

import org.json.JSONException;
import org.json.JSONObject;

import org.robockets.runqueue.common.SocketWrapper;

public class Server {
	public static void main(String args[]) {
		// CLI argument handling.
		ServerSocket serverSocket = null;
		// Defaults
		int port = 1033; // 1033 is pretty random.
		int backlog = 0;
		InetAddress bindAddr = null;
		
		switch (args.length){
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
			if (bindAddr != null){
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
				String command;
				JSONObject parsed;
				
				try {
					parsed = new JSONObject(message);
					command = parsed.getString("command");
				} catch (JSONException e) {
					System.err.println("Error in JSON! Skipping!");
					e.printStackTrace();
					wrapper.close();
					connection.close();
					continue;
				}
				
				switch (command) {
					case "print":
						wrapper.sendMessage("{message:\"Hello World!\"}");
						break;
				}
				
				wrapper.close();
				connection.close(); // TODO: wrapper should close this without me having to.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
