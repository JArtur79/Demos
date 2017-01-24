package com.kaminski.echoClientServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Aufruf: java EchoClient <host> <port>");
			System.exit(1);
		}

		String host = args[0];
		int port = 0;
		try {
			port = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {

			System.err.println("Ungueltige Portnunmer");
			System.exit(1);
		}

		try {
			// Aufbau der Verbindung zum Server
			Socket socket = new Socket(host, port);

			// Ein- und Ausgabeströme für diesen Socket erstellen
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			// Begrüßung durch den Server
			System.out.println(in.readLine());
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("> ");
			String line;
			while ((line = input.readLine()) != null) {
				if (line.length() == 0)
					break;

				// Text senden und Antwort empfangen
				out.println(line);
				System.out.println("Antwort vom Server:");
				System.out.println(in.readLine());
				System.out.print("> ");
			}
			socket.close();
			in.close();
			out.close();
			input.close();
		} catch (Exception e) {
			System.err.println(e);
		}

	}
}
