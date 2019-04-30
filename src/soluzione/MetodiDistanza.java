package soluzione;
import java.util.*;


/**
 * Gestisce i metodi associati al comando distanza
 *
 */
public class MetodiDistanza {
	
	/**
	 * Calcola il valore minimo tra tre interi
	 * @param a primo intero
	 * @param b secondo intero
	 * @param c terzo intero
	 * @return Il minimo tra gli interi
	 */
	private int calcolaMinimo(int a, int b, int c) {                            
        return Math.min(Math.min(a, b), c);                                      
    }
   
	
	/**
	 * 
	 * Stampa a schermo le operazioni da effettuare per ottenere la parolaY partendo dalla parolaX.
	 * Esso prende in ingresso come argomenti la matrice creata dall’algoritmo di levenshtein {@code matriceParole},
	 * la parola di partenza {@code ParolaX} e la parola che si vuole ottenere {@code parolaY}.
	 * L’algoritmo processa la matriceParole ed effettuando dei controlli individua e stampa a schermo le operazioni.<br>
	 * Il formato delle operazioni stampate sono: <br>
	 * - "ins(c,i)" {@code Inserzione} del carattere c in posizione i <br>
	 * - "can(i)" {@code Cancellazione} del carattere in posizione i <br>
	 * - "sos(i,c)" {@code Sostituzione} del carattere in posizione i con c <br>
	 * - "sca(i)" {@code Scambio} del carattere in posizione i con quello in posizione i+1 <br>
	 * 
	 * @param matriceParole matrice costruita dal metodo {@code levenshtein} della classe {@code MetodiDistanza}
	 * @param parolaX parola di partenza
	 * @param parolaY parola che si vuole ottenere
	 */
	 public void stampaOperazioni(int[][] matriceParole, String parolaX, String parolaY) {
		
		//Definisco uno Stack che conterrà tutte le operazioni
		Stack<String> operazioni = new Stack<String>();
        
		//Definisco due variabili che mi indicano la lunghezza delle parole
        int i = parolaX.length();
        int j = parolaY.length();
        
        String operazione;
        
        /*Percorro la matrice partendo dall'ultima posizione in basso a destra fino a quando 
        * entrambi gli indici (i,j) rimangono maggiori di zero 
        */
        while (i>0 & j>0) {
        	
        	//
        	int valoreAttuale = matriceParole[i][j];
        	
        	/*Calcolo il minimo dei valori adiacenti al valoreAttuale
        	  (sopra, sinistra e in diagonale in alto a sinistra)*/
        	int minimo = calcolaMinimo(matriceParole[i - 1][j],                                  
        						matriceParole[i][j - 1],                                  
        						(matriceParole[i - 1][j - 1]));
        
        	
        	
        	//OPERAZIONE SCAMBIA
        	//Se il valoreAttuale è uguale a tutti e tre i valori adiacenti avremo uno scambio
        	if (valoreAttuale == matriceParole[i-1][j] && valoreAttuale == matriceParole[i][j-1] && valoreAttuale == matriceParole[i-1][j-1] ) {
        	
        		int lettera = i-1;
        	
        		//System.out.println("sca (" + (lettera) +")");
        		operazione = ("sca(" + (lettera) +")");
        	
        		operazioni.push(operazione);
        	
        	
        		i = i-2;
        		j = j-2;
        	}
	       
        	
        	//NIENTE
        	else if (valoreAttuale == minimo) { 
        
        	
        		/*System.out.println("Niente");
        		System.out.println(i);
        		System.out.println(j);*/
        		i = i-1;
        		j = j-1;
        	
        	}
        
        	//METODO SOSTITUZIONE
        	//se il valore minimo è uguale al valore nella diagonale in alto a sinistra avremo una sostituzione
        	else if (minimo == matriceParole[i-1][j-1]) {
        	
        		char ins = parolaY.charAt(j-1);
        		//System.out.println("Sostituzione(" + i + ","+ ins + ")");
        		operazione =("sos(" + i + ","+ ins + ")");
        		operazioni.push(operazione);
        		i = i-1;
        		j = j-1;
        	
        	
        	}
        	
        	//METODO INSERIMENTO
        	//se il valore minimo è uguale al valore a sinistra avremo un inserimento
        	else if (minimo == matriceParole[i][j-1]) {
        	
        		char ins = parolaY.charAt(j-1);
        		//System.out.println("Inserimento(" + (j) + "," + ins + ")");
        		operazione =("ins(" + (j) + "," + ins + ")");
        		operazioni.push(operazione);
        		//i = i;
        		j = j-1;
        	}
        	//METODO CANCELLAZIONE
        	//se il valore minimo è uguale al valore in alto avremo una cancellazione
        	else if (minimo == matriceParole[i-1][j]) {
        	
        		char ins = parolaX.charAt(i-1);
        		//System.out.println("Cancellazione(" + (i) +"," + ins + ")");
        		operazione =("can(" + (i) +"," + ins + ")");
        		operazioni.push(operazione);
        		i = i-1;
        		//j = j;
        	}
        
        }
        
        //nel caso in cui dovessi trovarmi due parole con lunghezza differente e quindi se le righe e 
        //le colonne hanno lunghezze differenti, itero per le posizioni rimanenti
        while(i==0 || j==0) {
        	//quando i e j sono 0
        	if(i==0 && j==0) {
        		break;
        	}
        	//METODO INSERIMENTO
        	//se l'indice i è uguale a 0 avremo un inserimento
        	else if(i==0) {
	        	char ins = parolaY.charAt(j-1);
	        	//System.out.println("Inserimento(" + (j) + "," + ins + ")");
	        	operazione =("ins(" + (j) + "," + ins + ")");
	        	operazioni.push(operazione);
	        	j = j-1;
        	
       
        	}
        	//METODO CANCELLAZIONE
        	//se l'indice j è uguale a 0 avremo una cancellazione
        	else if(j==0) {
	        	char ins = parolaX.charAt(i-1);
	        	//System.out.println("Cancellazione(" + (i) +"," + ins + ")");
	        	operazione =("can(" + (i) +"," + ins + ")");
	        	operazioni.push(operazione);
	        	i= i-1;

	        }
        }
	        //STAMPA OPERAZIONI
        	//itero il pop dello Stack fino a quando contiene elementi per stampare a schermo 
        	//le operazioni in ordine corretto
	        System.out.print("op: ");
	        while (!operazioni.empty()) {
	        	
	        	System.out.print(operazioni.pop() + " ");
	        }	
	        System.out.println();
	}
	
	
	/**
	 * Restituisce la distanza di editing ovvero il minor numero di operazioni necessarie 
	 * per passare dalla parola {@code ParolaX}  alla {@code parolaY}.
	 * Questo valore è contenuto nell'ultima cella in basso a sinistra della {@code matriceParole}
	 * creata dall'algoritmo di levenshtein
	 * 
	 * @param parolaX parola di partenza
	 * @param parolaY parola che si vuole ottenere
	 * @return distanza di editing tra la {@code ParolaX} e la {@code parolaY}
	 */
	 public int getDistanza(String parolaX, String parolaY) {
		
		//Eseguo il metodo levenshtein per creare la matrice
		MetodiDistanza md = new MetodiDistanza();
		int matriceParole[][] = md.levenshtein(parolaX, parolaY);
		
		//Restituisco il valore della distanza di editing
		return matriceParole[parolaX.length()][parolaY.length()];
	}//Fine metodo getDistanza
	
	
	
	/**
	 * Algoritmo che crea una matrice ({@code matriceParola}) di dimensioni della lunghezza delle due parole
	 * fornite come parametri ({@code parolaX}, {@code parolaY}).
	 * I valori contenuti all'interno della {@code matriceParola} sono calcolati attraverso funzioni specifiche.
	 * Questo algoritmo è utilizzato per calcolare il valore della distanza di editing tra le due parole,
	 * cioè il minor numero di operazioni per ottenere la parolaY partendo dalla parolaX, che si troverà nell’ultima
	 * cella in basso a destra. Il metodo restituisce l’intera matrice aggiornata.
	 * 
	 * @param parolaX parola di partenza
	 * @param parolaY parola che si vuole ottenere
	 * @return distanza tra le due parole
	 */
     public int[][] levenshtein(String parolaX, String parolaY) {
    	
    	
    	//Inizializzo la matrice che verrà riempita successivamente
    	int[][] matriceParole = new int[parolaX.length() + 1][parolaY.length() + 1];        
       
        
        
        for (int i = 0; i <= parolaX.length(); i++)     
        	//Inserisco nelle celle della prima colonna i numeri corrispondenti al valore della variabile i
        	matriceParole[i][0] = i;            
        
        for (int j = 1; j <= parolaY.length(); j++)   
        	//Inserisco nelle celle della prima riga i numeri corrispondenti al valore della variabile j
        	matriceParole[0][j] = j;          
        
        
        int costo;
        for (int i = 1; i <= parolaX.length(); i++) {                                
            for (int j = 1; j <= parolaY.length(); j++) {
            	
            	/*Se la parolaX in posizione i-1 ha la stessa lettera 
            	 * di parolaY in posizione j-1 allora pone la variabile costo uguale a 0, altrimenti uguale a 1
            	 */
            	if(parolaX.charAt(i-1) == parolaY.charAt(j-1))
            		costo = 0;
            	else
            		costo = 1;
            	
            	//Scrivo nella matriceParole in posizione (i,j) il minimo dei valori
            	matriceParole[i][j] = calcolaMinimo(                                        
            			matriceParole[i - 1][j] + 1,                                  
            			matriceParole[i][j - 1] + 1,                                  
                        (matriceParole[i - 1][j - 1] + costo));
            	
                //Se sia i che j sono maggiori stretti di 1
                if (i>1 && j>1) {
                	
            		if( (parolaX.charAt(i-1) == parolaY.charAt(j-2)) && (parolaX.charAt(i-2) == parolaY.charAt(j-1)) ) {
            			
            			matriceParole[i][j] = Math.min(matriceParole[i][j], matriceParole[i-2][j-2]+costo);
            		}
                }
            }
        }
        
        /*Stampa della matrice
        
        for(int i=0;i<distanza.length;i++) {
        	for(int j=0;j<distanza[i].length;j++){ 
        	
        	System.out.print("\t"+distanza[i][j]); } 
        	
        	System.out.println(" ");} */
        
        //Ritorno la matrice
        return matriceParole;
    }
        
   
}//Fine Classe