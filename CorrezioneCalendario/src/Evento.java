import java.time.LocalDateTime;

public class Evento {
    private String titolo;
    private String descrizione;
    private LocalDateTime dataOra;

    public Evento(String titolo, String descrizione, LocalDateTime dataOra) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.dataOra = dataOra;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDateTime getDataOra() {
        return dataOra;
    }

    public void setDataOra(LocalDateTime dataOra) {
        this.dataOra = dataOra;
    }

    @Override
    public String toString() {
        return "Evento " + this.titolo + " del " + this.dataOra + " (" + this.descrizione + ")";
    }
}