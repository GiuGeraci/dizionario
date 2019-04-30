package soluzione;
import java.io.*;
import java.util.*;

/**
 * Contiene il {@code main} che avvia l'applicazione. <br>
 * Inoltre inizializza la struttura dati {@code TreeSet<String>} adibita a contenere il dizionario
 * 
 * @author Federico Cremona (729302)
 * @author Giuliano Geraci (729129)
 * 
 */
public class Dizionario {
	
	/**
	 * Struttura dati utilizzata per contenere il dizionario.
	 * 
	 */
	TreeSet<String> dizionario = new TreeSet<String>();
	
	/**
	 * Stampa a schermo l'istruzione per l'utente e prende in input il nome del {@code fileComandi}. <br>
	 * Chiama il metodo {@code analisiFile} della classe {@code Lettura} per analizzare il file
	 * in oggetto ed effettuare le operazioni corrette
	 * 
	 * @throws IOException se {@code fileComandi} non esiste o non è nella directory indicata
	 */
	public static void main(String[] args) throws IOException {
		
		
		
		//Stampo a schermo l'istruzione
		System.out.print("Inserire il nome del file comandi\n> ");
		
		//Salvo il file specificato dall'utente tramite linea di comando
		Scanner in = new Scanner(System.in);
		File fileComandi = new File(in.nextLine());
		
		/* Eseguo il metodo analisiFile della classe lettura, inserendo come parametri
		   il file contenente i comandi e la struttura dati per contenere il dizionario */
		Dizionario d = new Dizionario();
		Lettura l = new Lettura();
		l.analisiFile(fileComandi, d.dizionario);
		
		//Chiudo lo Scanner
		in.close();
		
		//Uscita dal programma
		System.exit(0);
	}
}