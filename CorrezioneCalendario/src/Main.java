import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // CARTA 1
        System.out.println("--- CARTA 1 ---");
        // 1 - Creare un evento da console
        Evento e = new Evento("Fare la spesa", "Comprare pane", LocalDateTime.of(2025, 12, 1, 10, 30));

        // 2 - Stampare i suoi dati in console
        System.out.println(e); // Viene automaticamente richiamato il toString() che abbiamo riscritto

        // 3 - Leggere i singoli campi
        e.setDescrizione("Comprare pane e uova");

        // 4 - Stampare i singoli campi
        System.out.println("Titolo: " + e.getTitolo());
        System.out.println("Descrizione: " + e.getDescrizione());
        System.out.println("Data e ora: " + e.getDataOra());

        // CARTA 2
        System.out.println("\n--- CARTA 2 ---");
        Calendario calendario = new Calendario();

        // 1 - Test addEvento(Evento e) : void
        calendario.addEvento(e);
        calendario.addEvento(new Evento("Leggere", "Finire fino al capitolo 3", LocalDateTime.of(2025, 12, 5, 10, 45)));

        // 2 - Test getEventi() : void
        calendario.getEventi();

        // CARTA 3
        System.out.println("\n--- CARTA 3 ---");

        if(calendario.removeEvento(65)) {
            System.out.println("Evento cancellato dal calendario");
        }
        else {
            System.out.println("Evento non esistente nel calendario");
        }

        calendario.getEventi();

        // CARTA 4
        System.out.println("\n--- CARTA 4 ---");
        Evento risultato = calendario.cercaEvento("pane");

        if(risultato == null) {
            System.out.println("Nessun evento corrispondente");
        }
        else {
            System.out.println("Risultato alla ricerca: " + risultato);
        }
    }
}