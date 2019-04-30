package soluzione;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Si occupa di leggere e analizzare un file di testo
 * e di invocare l'operazione associato ad ogni comando.
 * Le operazioni associate ad ogni comando sono indicate nelle specifiche tecniche del progetto dizionario.
 *
 */
public class Lettura {

	
	/**
	 * Legge il {@code fileComandi},
	 * il cui nome è specificato dall'utente, che 
	 * contiene una sequenza di comandi.
	 * Analizza il {@code fileComandi} ed esegue il metodo, implementato nella classe {@code Comandi}, 
	 * associato alla lettera indicata
	 * 
	 * @param fileComandi file di testo contenente le operazioni da effettuare
	 * @param dizionario struttura dati adibita al contenimento delle parole da aggiungere
	 * @throws IOException se {@code fileComandi} non esiste o non è nella directory indicata
	 */
	public void analisiFile(File fileComandi, TreeSet<String> dizionario) throws IOException {
	
		//Intercetto l'eccezione
		try {
		
			//Creo un BufferedReader che scandisce, una riga alla volta, il fileComandi
			FileReader fileReader;
			BufferedReader bufferedReader;
			fileReader = new FileReader(fileComandi);
			bufferedReader = new BufferedReader(fileReader);
			
			//Definisco il delimitatore da utilizzare nello StringTokenizer
			String delimitatori = new String (",; ");
			
			//Creo un oggetto della classe Comandi per chiamare i metodi implementati in quella classe
			Comandi c = new Comandi();
			
			
			while (true) {
				
				//Leggo, una alla volta, tutte le righe del fileComandi
				String rigaInLettura = bufferedReader.readLine();
				if (rigaInLettura == null) break;
				
				//Suddivido la rigaInLettura in token utilizzando i delimitatori definiti
				StringTokenizer st = new StringTokenizer(rigaInLettura,delimitatori);
					
				//Leggo il primo token della riga, lo salvo in comando e lo rendo minuscolo
				if(st.hasMoreTokens()) {
				String comando = st.nextToken();	
				comando.toLowerCase();
						
				//Eseguo il metodo, implementato nella classe Comandi, associato alla lettera indicata
				switch (comando) {
						
					case "c": 
							c.inserisciDizionario(dizionario, new File(st.nextToken()));
							
						break;
							
						
					case "v": 
							c.visualizzaDizionario(dizionario);
							
						break;
							
						
					case "i":	
							c.inserisciParola(dizionario, st.nextToken());
									
						break;
						
						
					case "e": 
							c.elimina(dizionario, st.nextToken());
								
						break;
							
						
					case "r": 	
							c.ricerca(dizionario, st.nextToken());
									
						break; 
						
							
					case "d": 	
							c.distanza(dizionario ,st.nextToken(), st.nextToken());
									
									
						break;
							
						
					case "f": 
							System.exit(0);
							
								
					default:
							System.out.println("L'operazione "+ "'"+ comando +"'" +" non è implementata!");
							
					}//Fine Switch
				}
				
			} //Fine while
			
		// Chiusura FileReader
		fileReader.close();
				
		} 
		/* Gestisco l'errore che potrebbe essere generato all'interno del codice e stampo a 
		schermo un messaggio di errore */
		catch (FileNotFoundException ex) {
			System.out.println("Il file specificato non esiste, oppure non è nella cartella corretta!");
		}
		
	}//Fine Metodo
	
}//Fine Classe