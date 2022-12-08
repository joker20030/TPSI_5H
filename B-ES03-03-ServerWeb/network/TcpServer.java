/*
Implementazione di un server web utilizzando la comunicazione tramite socket.
Lettura dati multi riga provenienti dal client
cd Es03/ServerWeb-OnOff/src/network
java TcpServer.java
*/
 
package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
	public static void main(String[] args) throws Exception {
		
		final int SERVER_PORT=8765;
		String clientMsg = "";
		String serverMsg = "";
		boolean flag;
		// URL url = new URL("https://www.favicon.cc/");
		// favicon.ico serverMsg += url;

		try {			 
			// Creazione del socket sul server e ascolto sulla porta
			ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("Server: in ascolto sulla porta " + SERVER_PORT);

			flag=false;
			while(!flag) {
				// Attesa della connessione con il client
				System.out.println("\nAttesa ricezione dati dal client ....................... \n");
				Socket clientSocket = serverSocket.accept();
				
				// Create output stream to write data and input stream to read data from socket
				DataOutputStream outStream = new DataOutputStream(clientSocket.getOutputStream());	
				BufferedReader inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
                //Lettura dati dal client un righa alla volta   
                clientMsg=inStream.readLine();
				System.out.println(clientMsg);	
		 
                // Elaborare qui i dati ricevuti dal client 

                clientMsg.trim();	//tolgo gli spazi all'inizio e alla fine della stringa
				String arrayCliMsg[]=clientMsg.split("\\s+");

				//Invio dei dati su stream di rete al client
				serverMsg = "HTTP/1.1 200 OK\r\n";
				//serverMsg += "Connection: close\r\n";
				serverMsg += "Content-Type: text/html\r\n"; 
                serverMsg += "\r\n";
				
                switch(arrayCliMsg[1]) {

				    case "/":
						serverMsg += "<b><h1>Saluti da FORLEO!!</h1></b>";
						serverMsg += "<i><h2>Comandi disponibili:</h2></i>";
						serverMsg += "<h3>'on' : accende le luci;</h3>";
						serverMsg += "<h3>'off' : spegne le luci;</h3>";
						serverMsg += "<h3>'quit' : esce dal server;</h3>";
                        break;

                    case "/on":
						serverMsg += "<h3>Luci accese<h3>";
                        break;

                    case "/off":
						serverMsg += "<h3>Luci spente</h3>";
                        break;

					case "/quit":
						serverMsg += "<h3>Uscita dal server...</h3>";
						flag = true;
                        break;

                    default : serverMsg += "<h3>Errore</h3>";
                }
            	System.out.print(serverMsg + "\n");		
                outStream.write(serverMsg.getBytes());
				outStream.flush();
				
				// Close resources
				clientSocket.close();
				inStream.close();
				outStream.close();
			}
			System.out.println("\n....................... Fine ricezione dati\n");
			// Close resources
			serverSocket.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}