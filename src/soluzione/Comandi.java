package soluzione;

import java.io.*;
import java.util.*;


/**
 * Implementa tutti i metodi che corrispondono alle operazioni
 * eseguibili sul dizionario.
 * 
 * 
 */
public class Comandi {
	
	/**
	 * Inserisce nel dizionario le parole contenute nel File di nome {@code fileDizionario}.
	 * Prima di inserirle controlla che queste parole siano formate solo da caratteri 
	 * alfabetici e non da numeri. <br>
	 * Se si incontra una parola contenente numeri viene generato un errore e 
	 * un messaggio viene stampato a schermo
	 * 
	 * @param dizionario struttura dati nella quale aggiungere le parole
	 * @param fileDizionario nome file testo contenente parole separate da uno o più caratteri di spaziatura
	 * @throws IOException se {@code fileDizionario} non esiste o non è nella directory indicata
	 */
	public void inserisciDizionario (TreeSet<String> dizionario, File fileDizionario) throws IOException {             
	
		//Intercetto l'eccezione
		try {
			
			//Creo un BufferedReader per leggere una riga alla volta del fileDizionario
			FileReader fr = new FileReader(fileDizionario);											
			BufferedReader br = new BufferedReader(fr);
			
			//Definisco il delimitatore da utilizzare nello StringTokenizer
			String delimitatori = new String (",.; !/()?'=[]{}°#*\t");
			
			
			
			
			while (true) {
				//Leggo, una alla volta, tutte le righe del fileComandi
				String ln = br.readLine();
				if (ln==null) break;
			
				//Suddivido la rigaInLettura in token utilizzando i delimitatori definiti
				StringTokenizer st = new StringTokenizer(ln,delimitatori);
				
				//Inserisco nel dizionario tutte le parole (token) trasformansole in minuscole
				while (st.hasMoreTokens()) {
					String parola = st.nextToken();
					
					//Inserisco la parola nel dizionario solo nel caso in cui non contenga numeri
					if(!parola.matches(".*\\d+.*")) {
						
						dizionario.add(parola.toLowerCase());
					}
					
					//altrimenti stampo a schermo un messaggio di errore
					else {
						System.out.println("La parola '"+ parola + "' contiene numeri, non verrà inserita!");
					}			
				}
			}//Fine while
			
		//Chiudo il FileReader
		fr.close(); 

		}
		/* Gestisco l'errore che potrebbe essere generato all'interno del codice e stampo a 
		schermo un messaggio di errore */
		catch (FileNotFoundException ex) {
			System.out.println("Il file specificato non esiste, oppure non è nella directory indicata!");
		}
	}//Fine Metodo inserisciDizionario

	
	
	/**
	 * Inserisce nel dizionario la {@code parolaDaAggiungere}, se è già presente non viene inserita.
	 * Se {@code parolaDaAggiungere} contiene caratteri numerici non viene inserita e 
	 * viene generato a schermo un messaggio d'errore
	 * 
	 * @param dizionario struttura dati nella quale aggiungere la parola specificata
	 * @param parolaDaAggiungere parola che si vuole aggiungere al dizionario
	 */
	public void inserisciParola (TreeSet<String> dizionario, String parolaDaAggiungere) {
		
		
		//Inserisco la parola nel dizionario solo nel caso in cui non contenga numeri
		if(!parolaDaAggiungere.matches(".*\\d+.*")) {
			dizionario.add(parolaDaAggiungere);
		}
		
		//altrimenti stampo a schermo un messaggio di errore
		else {
			System.out.println("La parola '"+ parolaDaAggiungere + "' contiene numeri, non verrà inserita!");
		}
	}//Fine Metodo inserisciParola

	
	
	/**
	 * Stampa a schermo l'elenco delle parole contenute nel dizionario in ordine alfabetico.
	 * Il metodo stampa sette parole per riga, questo per rendere l'output visibilmente più gradevole
	 * 
	 * @param dizionario struttura dati contenente le parole da visualizzare
	 */
	public void visualizzaDizionario (TreeSet<String> dizionario) {
		
		//Se il dizionario contiene delle parole, le stampo a schermo *
		if (!dizionario.isEmpty()) {
			
			Iterator<String> it = dizionario.iterator();
			System.out.print("v: ");
			
			int i = 0;
			
			//Itero la stampa di 7 parole per riga
			while (it.hasNext()) {
				if(i<7) {
					i++;
				System.out.print(it.next()+" ");
				}
				else {
					i=0;
					System.out.print("\n   " + it.next() +" ");
				}
					
			}
			System.out.println();
			}
			
		// * altrimenti avviso l'utente che il dizionario è vuoto
		else {
			System.out.print("v: ");
			System.out.println("Il dizionario è vuoto!");
		}
	}//Fine metodo visualizzaDizionario
	
	
	
	/**
	 * Elimina la {@code parolaDaEliminare} dal dizionario solo nel caso in cui sia contenuta
	 * 
	 * @param dizionario struttura dati dalla quale eliminare la parola specificata
	 * @param parolaDaEliminare parola che si vuole eliminare
	 */
	public void elimina (TreeSet<String> dizionario, String parolaDaEliminare ) {
		
		//controllo se il dizionario è pieno e cancello la parola indicata
		if (!dizionario.isEmpty()) {
			
			dizionario.remove(parolaDaEliminare.toLowerCase());
		}
		
	}//Fine metodo elimina
	
	
	
	
	 /**
     * 
     * Effettua la ricerca nel Dizionario delle parole che sono istanze dello {@code schema}. <br>
     * Uno schema è una sequenza finita di caratteri, maiuscoli o minuscoli, appartenenti all'alfabeto.
     * Le lettere maiuscole rappresentano delle variabili su cui agisce la funzione di assegnamento
     * che associa a una lettera maiuscola una lettera minuscola. <br>
     * Nel caso in cui nello schema siano contenute più lettere maiuscole uguali, la funzione deve
     * associare, alla stessa lettera maiuscola, la stessa lettera minuscola. <br>
     * 
     * Per essere istanza dello schema la parola deve: <br>
     * - Avere lo stesso numero di lettere dello schema <br>
     * - Rispettare la funzione di assegnamento per le lettere maiuscole dello schema <br>
     * - Rispettare la proprietà per cui se lo schema ha delle lettere minuscole in posizioni note, 
     *   la parola deve avere le stesse lettere minuscole nelle stesse posizioni
     * 
     * @param dizionario struttura dati nella quale ricercare le parole
     * @param schema sequenza finita di caratteri alfabetici, maiuscoli o minuscoli
     */
	public void ricerca(TreeSet<String> dizionario, String schema){
		
		
		System.out.print("r " + schema +": ");
	
		/* Inizializzo un Array (maiuscole) della stessa lunghezza dello schema
		che associa ad ogni lettera maiuscola dello schema, un numero crescente partendo da 1.
		Lettere maiuscole uguali avranno lo stesso numero */
		 
		int[] maiuscole =new int[schema.length()];
		
		int count = 0;
		
		//Per ogni lettera dello schema
		for(int i = 0;i<schema.length();i++) {
			//Se la lettera è maiuscola
			if(Character.isUpperCase(schema.charAt(i))) {
				
				//Se l'array in posizione i è 0
				if(maiuscole[i]==0) {
					
					//Aumento il contatore e lo inserisco nella posizione i dell'array
					count++;
					maiuscole[i]= count;
					
					//Per ogni lettera dello schema, partendo da i
					for (int r=i+1; r<schema.length();r++) {
						
						//Se ci sono lettere maiuscole uguali nello schema
						if(schema.charAt(i)==schema.charAt(r)) {
							
							/*Inserisco nell'array, in posizione di quelle lettere maiuscole,
							  il numero contenuto in cout.
							  (In questo modo, lettere maiuscole uguali, avranno stesso numero
							   nell'array maiuscole) */
							maiuscole[r]=count;
						}
					}
				}
			}
		}//Ho controllato tutte le lettere dello schema
		
		//Funzione che ho utilizzato per controllare che l'output fosse corretto
		//for (int k=0;k<schema.length();k++) {
		//System.out.print(maiuscole[k]);}
		
		
		//Definisco un oggetto della classe Iterator<String> per poter scandire il dizionario
		Iterator<String> it = dizionario.iterator();
		
		//Scandisco il dizionario ricercando le parole che sono istanze dello schema
		while (it.hasNext()) {
				
			//Parola del dizionario su cui effettuiamo il controllo
			String parola = it.next();
		
			//Scarto le parole che non rispettano la lunghezza dello schema
			if (parola.length() == schema.length()) {
				
				//Variabile che mi indica se la parola è istanza dello schema
				boolean corretta = true;
				
				/*Variabile che mi indica la lettera maiuscola uscita.
				  Mi serve per sapere se è la prima volta che incontro quella determinata 
				  lettera maiuscola*/
				int letteraUscita = 1;
				
				/*Variabile che conterrà la lettera minuscola associata dalla funzione assegnamento
				  Mi serve per vedere se nella parola ci sono lettere uguali per ogni lettera maiuscola uguale 
				 */
				char letteraCampione;
				
				//Per ogni lettera dello schema
				for ( int i=0; i<schema.length();i++) {
			
					//Nel caso in cui la lettera in posizione i dello schema sia minuscola
					if (Character.isLowerCase(schema.charAt(i))){
					
						/*Se la lettera in posizione i della parola è uguale alla 
						  lettera dello schema in posizione i, continuo*/
						if (parola.charAt(i)==schema.charAt(i)) {
							continue;	
						} 
						//Atrimenti pongo la variabile corretta falsa ed esco dal ciclo 
						else {
							corretta = false;
							break;
						}
					}
					
					
					//Nel caso in cui la lettera dello schema sia MAIUSCOLA
					if (Character.isUpperCase(schema.charAt(i))){
					
						/*Se il numero contenuto nell'Array maiuscole in posizione i è uguale alla letteraUscita
						  (Cioè se è la prima volta che incontro la lettera maiuscola)*/
						if(maiuscole[i]==letteraUscita) {
							
							/*Incremento la variabile in modo che possa verificare la prossima
							lettera maiuscola alla prossima iterazione*/
							letteraUscita++;
							
							//Salvo la lettera minuscola associata dalla funzione assegnamento
							letteraCampione = parola.charAt(i);
							
							//Per ogni lettera maiuscola uguale a quella uscita
							for(int j=i+1;j<schema.length();j++) {
								//Per ogni lettera maiuscola uguale a quella uscita
								if(maiuscole[i]==maiuscole[j]) {
									
									if(letteraCampione != parola.charAt(j)) {
										corretta = false;
										break;
									}
								}
							}
							
							//Se la variabile 'corretta' è falsa esco 
							if (!corretta) {
								break;
							}
						}

					}//Fine caso lettera MAIUSCOLA
					
				//Se la variabile 'corretta' è falsa esco 
				if (!corretta) {
					break;
				}
					
					
				}//Fine for (controllato tutte le lettere dello schema)
				
				//Stampo la parola solo se la variabile 'corretta' è true
				if (corretta) {
					System.out.print(parola+" ");
				}

			}
				//Se la parola non è della lunghezza dello schema, passo alla successiva
			else continue;
			
		} System.out.println();
		
	}//Fine metodo ricerca
	
	
	
	
	/**
	 * Si occupa di stampare a schermo il numero e la descrizione delle
	 * operazioni da effettuare per passare dalla parola {@code parolaX}
	 * alla {@code parolaY}.
	 * 
	 * E' prevista l'aggiunta al dizionario di {@code parolaX} e {@code parolaY}
	 * se non presenti
	 * 
	 * @param dizionario struttura dati alla quale aggiungere le parole
	 * @param parolaX parola di partenza
	 * @param parolaY parola che si vuole ottenere
	 */
	public void distanza(TreeSet<String> dizionario, String parolaX, String parolaY) {
		
		//Effettuo un controllo sulle parole per verificare che non contengano numeri
		if(!parolaX.matches(".*\\d+.*") & !parolaY.matches(".*\\d+.*")) {
			
			// Aggiungo le parole al dizionario 
			dizionario.add(parolaX);
			dizionario.add(parolaY);
			
			MetodiDistanza md = new MetodiDistanza();
		
			//Stampo a schermo le informazioni richieste nelle specifiche del progetto 
			System.out.print("d " + parolaX + " " + parolaY + ": ");
			
			//Stampo la distanza di editing utilizzando il metodo getDistanza della classe MetodiDistanza
			System.out.println(md.getDistanza(parolaX, parolaY));
			
			//Stampo le operazioni utilizzando il metodo stampaOperazioni della classe MetodiDistanza
			int[][] matriceParole = md.levenshtein(parolaX, parolaY);
			md.stampaOperazioni(matriceParole, parolaX, parolaY);
			
		}
		
		//Se le parole contengono numeri stampo a schermo un messaggio di errore
		else {
		
			if(parolaX.matches(".*\\d+.*")) {
		
				System.out.println("La parola '"+ parolaX + "' contiene numeri: operazione non eseguibile!");
			}
			
			if(parolaY.matches(".*\\d+.*")) {
				
				System.out.println("La parola '"+ parolaY + "' contiene numeri: operazione non eseguibile!");
			}
		}
		
	}//Fine metodo distanza
	
	
	
	
	
}//Fine Classe
