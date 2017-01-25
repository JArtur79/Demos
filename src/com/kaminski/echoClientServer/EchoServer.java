package com.kaminski.echoClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.h2.tools.Server;

public class EchoServer extends Thread {

	private Socket client;

	public EchoServer(Socket client) {
		this.client = client;
	}

	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;

		try {
			// Ein- und Ausgabeströme für den Socket " c l i e n t erstellen
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);

			out.println("Hallo, ich bin der EchoServer");
			String input;
			while ((input = in.readLine()) != null) {
				System.out.println(input);
			}
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			try {
				if (client != null)
					client.close();
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			} catch (IOException e) {
			}
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Aufruf: java EchoServer <port>");
			System.exit(1);
		}
		int port = 0;
		try {
			port = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.err.println("Ungueltige Portnunmer");
			System.exit(1);
		}

		try {
			// Hierüber werden eingehende Verbindungen aufgenommen
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(port);
			System.out.println("EchoServer auf " + port + " gestartet ...");

			while (true) {
				// Auf eingehende Verbindung warten
				Socket client = server.accept();
				// Ein Thread wird gestartet
				new EchoServer(client).start();
			} // end while
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
}
