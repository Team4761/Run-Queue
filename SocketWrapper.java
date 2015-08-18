package org.robockets.runqueue.client;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.net.InetAddress;
import java.net.Proxy;
import java.net.Socket;

/**
*
*	Wrapper for {@link Socket} that allows them to easily send and recieve messages in a variety of formats.
*
**/
public class SocketWrapper extends Socket
{
	private SocketMessageType type = SocketMessageType.JSON;
	private InputStream lineIn;
	private OutputStream lineOut;
	
	/**
	*
	*	Call corresponding constructor of {@link java.net.Socket}, and set up SocketWrapper I/O.
	*
	*	@see java.net.Socket
	*
	**/
	public SocketWrapper () {
		super();
		createIO();
	}
	
	/**
	*
	*	Call corresponding constructor of {@link java.net.Socket}, and set up SocketWrapper I/O.
	*	
	*	@param address		the IP address
	*	@param port			the port number
	*	
	*	@see java.net.Socket
	*
	**/
	public SocketWrapper (InetAddress address, int port) throws IOException {
		super(address, port);
		createIO();
	}
	
	/**
	*
	*	Call corresponding constructor of {@link java.net.Socket}, and set up SocketWrapper I/O.
	*
	*	@param address		the remote address
	*	@param port			the remote port
	*	@param localAddr	the local address the socket is bound to, or null for the anyLocal address
	*	@param localPort	the local port the socket is bound to or zero for a system selected free port
	*
	*	@see java.net.Socket
	*
	**/
	public SocketWrapper (InetAddress address, int port, InetAddress localAddr, int localPort) throws IOException {
		super(address, port, localAddr, localPort);
		createIO();
	}
	
	/**
	*
	*	Call corresponding constructor of {@link java.net.Socket}, and set up SocketWrapper I/O.
	*
	*	@param proxy		a Proxy object specifying what kind of proxying should be used
	*
	*	@see java.net.Socket
	*
	**/
	public SocketWrapper (Proxy proxy) {
		super(proxy);
		createIO();
	}
	
	/**
	*
	*	Call corresponding constructor of {@link java.net.Socket}, and set up SocketWrapper I/O.
	*
	*	@param host			the host name, or null for the loopback address
	*	@param port			the port number
	*
	*	@see java.net.Socket
	*
	**/
	public SocketWrapper (String host, int port) throws IOException {
		super(host, port);
		createIO();
	}
	
	/**
	*
	*	Call corresponding constructor of {@link java.net.Socket}, and set up SocketWrapper I/O.
	*
	*	@param host			the name of the remote host, or null for the loopback address
	*	@param port			the remote port
	*	@param localAddr	the local address the socket is bound to, or null for the anyLocal address
	*	@param localPort	the local port the socket is bound to, or zero for a system selected free port
	*
	*	@see java.net.Socket
	*
	**/
	public SocketWrapper (String host, int port, InetAddress localAddr, int localPort) throws IOException {
		super(host, port, localAddr, localPort);
		createIO();
	}
	
	/**
	*
	*	Create the {@link java.io.InputStream} and {@link java.io.OutputStream} used by the {@link SocketWrapper}.
	*
	**/
	private void createIO () {
		try {
			lineIn = getInputStream();
			lineOut = getOutputStream();
		} catch (Exception ex) {
			System.err.println("Could not initiate SocketWrapper I/O.");
			ex.printStackTrace();
		}
	}
	
	/**
	*
	*	Specify the type of message that the {@link SocketWrapper} will expect.
	*	
	*	All {@link SocketWrapper}s default to a message type of <code>SocketMessageType.JSON</code>.
	*	
	*	@param messageType		the message type to set
	*
	*	@see SocketMessageType	
	*
	**/
	public void setMessageType (SocketMessageType messageType) {
		type = messageType;
	}
	
	/**
	*
	*	Get the type of message the {@link SocketWrapper} expects.
	*
	*	@return		the message type
	*
	*	@see SocketMessageType
	*
	**/
	public SocketMessageType getMessageType () {
		return type;
	}
	
	/**
	*
	*	Send one or more preformatted message(s) over the {@link SocketWrapper}.
	*	
	*	@param messages		the messages to be sent
	*
	**/
	public void sendMessage (String... messages) {
		try {
			for (String message : messages) {
				for (char c : message.toCharArray()) {
					lineOut.write((byte)c);
				}
			}
		} catch (Exception ex) {
			System.err.println("Error writing SocketWrapper message.");
			ex.printStackTrace();
		}
	}
	
	/**
	*
	*	Recieve a message over the {@link SocketWrapper}.
	*	
	*	@return 		the recieved message
	*
	*	@throws SocketMessageFormatException if the message is formatted incorrectly
	*
	**/
	public String getMessage () {
		String message = "";
		try {
			if (type == SocketMessageType.JSON) {
				int bracketLevel = 0;
				char c = (char) lineIn.read();
				if (c != '{') {
					throw new SocketMessageFormatException("JSON Message malformed (starting '{' not present)");
				}
				bracketLevel ++;
				for (c = (char) lineIn.read(); bracketLevel != 0; c = (char) lineIn.read()) {
					switch (c) {
						case '{':
							bracketLevel ++;
							break;
						case '}':
							bracketLevel --;
							break;
					}
					message += c;
				}
				return message;
			}
			char sep = type.getSeperator();
			for (char c = (char) lineIn.read(); c != sep; c = (char) lineIn.read()) {
				if (c == '\\') {
					char d = (char) lineIn.read();
					if (d == sep) {
						message += d;
					} else {
						message += c + d;
					}
				} else {
					message += c;
				}
			}
		} catch (Exception ex) {
			System.err.println("Error reading SocketWrapper message.");
			ex.printStackTrace();
		}
		return message;
	}
}

/**
*
*	Store information about the type of messages a {@link SocketWrapper} uses.
*
**/
enum SocketMessageType {
	JSON ((char) -1), COMMA_SEPERATED (','), NULL_TERMINATED ((char) 0x0);
	private final char seperationChar;
	SocketMessageType (char c) {
		seperationChar = c;
	}
	char getSeperator () {
		return seperationChar;
	}
}

/**
*
*	Exception that represents a formatting error in a message sent throught a {@link SocketWrapper}
*
**/
class SocketMessageFormatException extends RuntimeException {
	public SocketMessageFormatException (String message) {
		super(message);
	}
}