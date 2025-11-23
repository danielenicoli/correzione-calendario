# Calendario in Java
Vogliamo ricreare l'applicazione calendario del telefono, all'interno della quale è possibile aggiungere, modificare, cancellare e cercare degli eventi.

## Suggerimenti preliminari
Alcuni suggerimenti iniziali per affrontare un esercizio di programmazione:
1. Cerca di collegare l'esercizio a una situazione concreta che già conosci per studiarne il funzionamento e provare a replicarlo
    - Pensa, per esempio, a come usi il calendario sul telefono
    - A quali operazioni puoi fare
    - A come tu come utente interagisci con il programma
2. Crea un modello di massima dei dati (UML)
    - Cerca di capire come interagiscono e sono collegati tra loro
    - Se il modello non è completo, non importa: man mano che ti rendi conto che mancano delle cose, le aggiungerai

## Carta 1 - Modello dei dati
Dal testo, capiamo subito che tutta l'applicazione si basa su un principale oggetto: l'evento. Il testo, ci dice anche da cosa un evento è caratterizzato:
- Un titolo (che immagino sarà una `String`)
- Una descrizione (anche questa potrebbe essere una `String`)
- Una data e un'ora (non sappiamo ancora come memorizzare correttamente questa informazione)

### Memorizzare data e ora
Dal momento che non sappiamo come memorizzare questo valore, facciamo una rapida ricerca _in inglese_ su Google e vediamo i risultati. 

Il primo risultato alla ricerca _"Date and time in Java"_ ci restituisce [questo risultato](https://www.w3schools.com/java/java_date.asp). Decidiamo di usare la classe `LocalDateTime` in quanto _"Represents both a date and a time (yyyy-MM-dd-HH-mm-ss-ns)"_. 

Essendo un tipo non standard, dovremo includere la sua libreria nell'intestazione di ogni classe in cui dovrà essere usato. Per farlo `import java.time.LocalDateTime;`.

Nella relativa [JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html) troveremo anche i metodi per modificarla, visualizzarla ed eseguire altre operazioni (ricerca, aggiunta giorni, ...).

### Lettura e scrittura degli attributi
Chiediamoci ora quali degli attributi possono essere modificati e letti dall'esterno della classe (cioè dal `Main` o da altre classi) e quali invece no.

Per gli attributi che devono essere letti, implementaremo dei metodi `public` detti _getter_, mentre per quelli che devono essere modificati implementermo dei metodi `public` detti _setter_.

Per impedire modifiche o letture non previste o non autorizzate ai dati, tutti gli attributi saranno `private` mentre i getter / setter saranno `public` per consentire "pubblicamente" la lettura e scrittura ma in modo controllato (ovvero come definiremo all'interno del getter / setter).

I getter sono metodi dello stesso tipo dell'attributo, i setter in genere sono di tipo `void` (`boolean` se il loro compito può fallire o devo avere certezza che la modifica ha avuto successo).

Per ulteriori spiegazioni su questa sezione, fai riferimento ai documenti _Introduzione alla OOP - Esempio Televisore_ e _Suggerimenti procedura derivazione UML_ disponibili su Moodle.

### Modello definitivo dei dati
Se abbiamo compreso tutti i ragionamenti fatti, passiamo adesso a rappresentare il modello definitivo dei dati fin qui identificati (più avanti, potrebbe nascere l'esigenza di aggiungere altre strutture dati).

Classe `Evento`:
- Attributi
    - `- titolo : String`
    - `- descrizione : String`
    - `- dataOra : LocalDateTime`
- Getter e setter
    - `- getTitolo() : String`
    - `- setTitolo(String) : void`
    - `- getDescrizione() : String`
    - `- setDescrizione(String) : void`
    - `- getDataOra() : LocalDateTime`
    - `- setDataOra(LocalDateTime) : void`
- Metodi
    - Al momento non ne identifichiamo

### Implementazione in Java della classe e relativi test
Implementiamo quindi la classe `Evento` in Java e eseguiamo i test richiesti, per accertarci del completo funzionamento (vedi il file `Evento.java` per il codice sorgente).

#### Override di `toString() : String`
Il metodo `toString()` permette di rappresentare lo stato (contenuto) dell'oggetto sotto forma di stringa. Siccome il metodo è presente nella classe `Object` di Java e tutte le classi ereditano da lei (sono sue figlie), allora dobbiamo fare un override (riscrittura di un metodo già esistente).

```java
@Override
public String toString() {
    return "Evento " + this.titolo + " del " + this.dataOra + " (" + this.descrizione + ")";
}
```

#### Test della classe e dei metodi
Per testare i metodi, dobbiamo prima istanziare la classe e creare quindi un oggetto di quel tipo. Per farlo [usiamo la sintassi prevista](https://www.w3schools.com/java/java_classes.asp) e chiamiamo nel Main i metodi da testare, ricordandoci di memorizzare i valori di ritorno delle funzioni (return) con variabili dello stesso tipo.

Per assegnare correttamente i valori dei tipi "nuovi", consultiamo la [JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html#of-int-int-int-int-int-) oppure cerchiamo _"How do I create a LocalDateTime instance?"_

```java
 Evento e = new Evento("Fare la spesa", "Comprare pane", LocalDateTime.of(2025, 12, 1, 10, 30));
```

Implementato il modello dei dati, raccolta la documentazione utile in futuro e testato quanto implementato, possiamo passare alla seconda carta.

## Carta 2 - Gestire oggetti in una collezione
Fino ad adesso, abbiamo gestito un singolo evento. Tuttavia, l'applicazione calendario ci permette di gestire un insieme (collezione) di eventi.

La carta ci suggerisce di creare una classe `Calendario` all'interno della quale inseriremo una lista di `Evento`.

### Scelta della struttura per memorizzare un insieme di dati
Come lista, useremo la classe `ArrayList` di Java, che include sia le funzionalità di un `Array` (es. accesso agli elementi per indice), sia quelle tipiche delle `List` (es. dimensione dinamica, ordinamento, ricerca, rimozione, ...). Qui la [JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html) delle `ArrayList`.

Anche questa classe, come tutte le altre non già incluse, deve essere importata all'inizio di ogni file che la utilizza.

La sintassi per istanziare è come quella di tutte le altre classi. 

### Creazione classe `Calendario` e relativi metodi
#### Costruttori
Creiamo un costruttore da usare quando creiamo un calendario vuoto:

```java
public Calendario() {
    this.eventi = new ArrayList<>();
}
```

E uno per quanto creiamo un calendario e importiamo già gli eventi:

```java
public Calendario(ArrayList<Evento> eventi) {
    this.eventi = eventi;
}
```

#### Getter
Creiamo un getter per restituire la lista. Invece di restituire l'indirizzo della lista originale restituiamo una nuova lista (copia), perché altrimenti chi la usa potrebbe modificare la struttura originale con conseguenze indesiderate su tutto il codice che la condivide.

```java
public ArrayList<Evento> getListaEventi() {
    return new ArrayList<>(eventi); 
}
```

Predisponiamo anche una stampa degli eventi nel calendario, come richiesto dalla traccia con la segnatura `getEventi() : void`. 

```java
public void getEventi() {
    for(int i = 0; i < this.eventi.size(); i++) {
        System.out.println(i+1 + ". " + eventi.get(i));
    }
}
```

#### Metodo per aggiunta evento
Gli `ArrayList` hanno un metodo (consultabile dalla JavaDoc allegata più sopra) per aggiungere un elemento alla collezione.

```java
public void addEvento(Evento e)  {
    this.eventi.add(e);
}
```

### Test
```java
Calendario calendario = new Calendario();

// 1 - Test addEvento(Evento e) : void
calendario.addEvento(e);
calendario.addEvento(new Evento("Leggere", "Finire fino al capitolo 3", LocalDateTime.of(2025, 12, 5, 10, 45)));

// 2 - Test getEventi() : void
calendario.getEventi();
```

Output
```
1. Evento Fare la spesa del 2025-12-01T10:30 (Comprare pane e uova)
2. Evento Leggere del 2025-12-05T10:45 (Finire fino al capitolo 3)
```

## Carta 3 - Rimuovere un oggetto dalla collezione
Rimuovere un oggetto da una collezione è facilissimo: basta leggere la [JavaDoc](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#remove-int-) delle `ArrayList` e cercare in inglese ciò che vogliamo fare, ovvero _remove_ un oggetto a partire dalla sua posizione. 

Ricordiamoci che la cancellazione non avviene sempre. Infatti, se un evento non esiste, non può essere cancellato. Gestire i casi di errore (es. se l'utente vuole rimuovere un evento non esistente) e restituire un valore booleano che indica il risultato dell'operazione.

```java
public boolean removeEvento(int i)  {
    if(i >= 0 && i < eventi.size()) {
        eventi.remove(i);
        return true;
    }
    return false;
}
```

### Test 1 (evento presente e correttamente cancellato)

```java
if(calendario.removeEvento(0)) {
    System.out.println("Evento cancellato dal calendario");
}
else {
    System.out.println("Evento non esistente nel calendario");
}

calendario.getEventi();
```

Output
```
Evento cancellato dal calendario
1. Evento Leggere del 2025-12-05T10:45 (Finire fino al capitolo 3)
```

### Test 2 (evento _non_ presente)

```java
if(calendario.removeEvento(65)) {
    System.out.println("Evento cancellato dal calendario");
}
else {
    System.out.println("Evento non esistente nel calendario");
}

calendario.getEventi();
```

Output
```
Evento non esistente nel calendario
1. Evento Fare la spesa del 2025-12-01T10:30 (Comprare pane e uova)
2. Evento Leggere del 2025-12-05T10:45 (Finire fino al capitolo 3)
```

## Carta 4 - Cercare un oggetto nella collezione
La funzione `cercaEvento(String keyword) : Evento` permette di cercare un evento nella lista eventi in base a una parola chiave fornita dall'utente e restituisce il primo evento che corrispondei ai criteri di ricerca. La ricerca considera sia il titolo sia la descrizione dell’evento e ignora le differenze tra maiuscole e minuscole.

### Implementazione ricerca
#### Logica in pseudocodice
Usiamo il ciclo [foreach](https://www.w3schools.com/java/java_foreach_loop.asp) per scorrere ogni elmento nella lista.

1. Scorrere la lista degli eventi
    - La funzione usa un ciclo for-each per analizzare ogni oggetto `Evento` nella lista eventi
2. Confronto tra keyword e getter dell'evento
    - Per ciascun evento, si confronta la parola chiave con:
        - `e.getTitolo()`: il titolo dell'evento
        - `e.getDescrizione()`: la descrizione dell'evento
    - Si verifica se la keyword è contenuta in almeno una delle due stringhe (`OR`)
3. Uso di toLowerCase()
    - Convertire sia la keyword sia i campi dell'evento in minuscolo serve a rendere la ricerca case-insensitive
    - Esempio: se l'utente cerca "Pane" e il titolo dell'evento è "Comprare pane e uova", la funzione riuscirà a trovarlo anche se le maiuscole non corrispondono
4. Restituzione del primo evento trovato
    - Appena si trova un evento che soddisfa la condizione, viene immediatamente ritornato
    - Se non ci sono eventi corrispondenti, la funzione ritorna `null` (riferimento a oggetto nullo, non punta ad alcun oggetto)

#### Java
```java
public Evento cercaEvento(String keyword) {
    for(Evento e : eventi) {
        if(e.getTitolo().toLowerCase().contains(keyword.toLowerCase()) ||
                e.getDescrizione().toLowerCase().contains(keyword.toLowerCase())) {
            return e;  // Ritorna il primo evento che soddisfa la ricerca
        }
    }
    return null; // Se non trova nulla
}
```

#### Test
```java
Evento risultato = calendario.cercaEvento("pane");

if(risultato == null) {
    System.out.println("Nessun evento corrispondente");
}
else {
    System.out.println("Risultato alla ricerca: " + risultato);
}
```

```
Risultato alla ricerca: Evento Fare la spesa del 2025-12-01T10:30 (Comprare pane e uova)
```