/*
Implementazione di un server web utilizzando la comunicazione tramite socket.
Lettura dati multi riga provenienti dal cliente
cd Es03/ServerWeb-OnOff/src/network
java TcpServer.java
*/
 
 rete pacchetto ;

importa  java . io .*;
importa  java . netto . ServerSocket ;
importa  java . netto . Presa ;

 classe  pubblica TcpServer {
	public  static  void  main ( String [] args ) lancia  Exception {
		
		final  int  SERVER_PORT = 8765 ;
		String  clientMsg = "" ;
		Stringa  serverMsg = "" ;
		 flag booleano ;
		// URL url = nuovo URL("https://www.favicon.cc/");
		// favicon.ico serverMsg += url;

		prova {			 
			// Creazione del socket sul server e ascolto sulla porta
			ServerSocket  serverSocket = new  ServerSocket ( SERVER_PORT );
			Sistema . fuori . println ( "Server: in ascolto sulla porta " + SERVER_PORT );

			bandiera = falso ;
			while (! flag ) {
				// Attesa della connessione con il client
				Sistema . fuori . println ( "\nAttesa ricezione dati dal client ....................... \n" );
				Socket  clientSocket = serverSocket . accettare ();
				
				// Crea un flusso di output per scrivere i dati e un flusso di input per leggere i dati dal socket
				DataOutputStream  outStream = nuovo  DataOutputStream ( clientSocket . getOutputStream ());	
				BufferedReader  inStream = nuovo  BufferedReader ( nuovo  InputStreamReader ( clientSocket . getInputStream ()));
				
                //Lettura dati dal client un diritto alla volta   
                clientMsg = inStream . readLine ();
				Sistema . fuori . println ( clientMsg );	
		 
                // Elaborare qui i dati ricevuti dal client

                clientMsg . tagliare ();	//tolgo gli spazi all'inizio e alla fine della stringa
				Stringa  arrayCliMsg []= clientMsg . diviso ( "\\s+" );

				//Invio dei dati su stream di rete al client
				serverMsg = "HTTP/1.1 200 OK\r\n" ;
				//serverMsg += "Connessione: chiudi\r\n";
				serverMsg += "Tipo di contenuto: testo/html\r\n" ;
                serverMsg += "\r\n" ;
				
                interruttore ( arrayCliMsg [ 1 ]) {

				    caso  "/" :
						serverMsg += "<b><h2>Saluti da Marco Zambelan!!</h2></b>" ;
						serverMsg += "<b><h3>Digita 'info' per vedere i comandi disponibili." ;
                        rompere ;

					caso  "/info" :
						serverMsg += "<b><h2>Comandi disponibili:</h2></b>" ;
						serverMsg += "<b><h3>'on' : accende le luci;</h3></b>" ;
						serverMsg += "<b><h3>'off' : spegne le luci;</h3></b>" ;
						serverMsg += "<b><h3>'esci' : esce dal server;</h3></b>" ;
						rompere ;

                    maiuscole e minuscole  "/on" :
						serverMsg += "<b><h2>Luci accese</h2></b>" ;
                        rompere ;

                    caso  "/off" :
						serverMsg += "<b><h2>Luci spente</h2></b>" ;
                        rompere ;

					case  "/esci" :
						serverMsg += "<b><h2>Uscita dal server...</h2></b>" ;
						contrassegno = vero ;
                        rompere ;

                    default : serverMsg += "<b><h2>Errore</h2></b>" ;
                }
            	Sistema . fuori . stampa ( serverMsg + "\n" );		
                outstream . scrivere ( serverMsg . getBytes ());
				outstream . filo ();
				
				// Chiudi le risorse
				clientSocket . chiudere ();
				inStream . chiudere ();
				outstream . chiudere ();
			}
			Sistema . fuori . println ( "\n.......................Fine ricezione dati\n" );
			// Chiudi le risorse
			serverSocket . chiudere ();

		} catch ( Eccezione  e ) {
			Sistema . fuori . println ( e );
		}
	}
}