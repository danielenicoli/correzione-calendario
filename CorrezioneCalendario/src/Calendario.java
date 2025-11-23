import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Calendario {
    private ArrayList<Evento> eventi;

    // Costruttore per quando creiamo un nuovo calendario vuoto
    public Calendario() {
        this.eventi = new ArrayList<>();
    }

    // Costruttore per quando creiamo un calendario e importiamo già gli eventi
    public Calendario(ArrayList<Evento> eventi) {
        this.eventi = eventi;
    }

    // Ottiene copia degli eventi presenti in calendario
    public ArrayList<Evento> getListaEventi() {
        return new ArrayList<>(eventi); // Ritorniamo una copia, diversamente avrebbe ritornato il riferimento (e quindi l'indirizzo originale) della lista
    }

    // Stampa eventi presenti in calendario con numero progressivo
    public void getEventi() {
        for(int i = 0; i < this.eventi.size(); i++) {
            System.out.println(i+1 + ". " + eventi.get(i));
        }
    }

    // Aggiunge un evento in calendario
    public void addEvento(Evento e)  {
        this.eventi.add(e);
    }

    // Rimuove un evento in posizione i, se disponibile (boolean perché la rimozione potrebbe fallire)
    public boolean removeEvento(int i)  {
        if(i >= 0 && i < eventi.size()) {
            eventi.remove(i);
            return true;
        }
        return false;
    }

    // Ricerca evento per parola chiave contenuta nel titolo e/o nella descrizione
    public Evento cercaEvento(String keyword) {
        for(Evento e : eventi) {
            if(e.getTitolo().toLowerCase().contains(keyword.toLowerCase()) ||
                    e.getDescrizione().toLowerCase().contains(keyword.toLowerCase())) {
                return e;  // Ritorna il primo evento che soddisfa la ricerca
            }
        }
        return null; // Se non trova nulla
    }

    // Modifica di un evento dato l'indice e i nuovi valori
    public boolean modificaEvento(int i, String titolo, String descrizione, LocalDateTime dataOra) {
        if(i >= 0 && i < eventi.size()) {
            Evento e = eventi.get(i);
            e.setTitolo(titolo);
            e.setDescrizione(descrizione);
            e.setDataOra(dataOra);
            return true;
        }
        return false;
    }

    // Filtro eventi per data di svolgimento
    public ArrayList<Evento> cercaEvento(LocalDate d) {
        ArrayList<Evento> risultati = new ArrayList<>();
        for(Evento e : eventi) {
            if(e.getDataOra().toLocalDate().equals(d)) {
                risultati.add(e);
            }
        }
        return risultati;
    }

    // Overload getEventi per stampare eventi passati come parametro
    public void getEventi(ArrayList<Evento> eventi) {
        if(eventi.isEmpty()) {
            System.out.println("Nessun evento da mostrare");
            return;
        }
        int count = 1;
        for(Evento evento : eventi) {
            System.out.println(count + ". " + evento);
            count++;
        }
    }

}