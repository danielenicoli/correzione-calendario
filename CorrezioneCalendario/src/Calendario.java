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
}