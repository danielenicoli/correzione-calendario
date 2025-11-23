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