package reservation;

import gestionVol.Vol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private int numero;
    private LocalDate date;
    private Client client;
    private Vol vol;
    private List<Passager> passagers = new ArrayList<>();
    private boolean confirme = false;

    public Reservation(int numero, LocalDate date) {
        this.numero = numero;
        this.date = date;
    }

    public int getNumero() { return numero; }
    public LocalDate getDate() { return date; }

    public Client getClient() { return client; }
    public void setClient(Client c) {
        if (this.client != c) {
            Client old = this.client;
            this.client = c;
            if (old != null) old.removeReservation(this);
            if (c != null && !c.getReservations().contains(this)) {
                c.addReservation(this);
            }
        }
    }

    public Vol getVol() { return vol; }
    public void setVol(Vol v) {
        if (this.vol != v) {
            Vol old = this.vol;
            this.vol = v;
            if (old != null) old.removeReservation(this);
            if (v != null && !v.getReservations().contains(this)) {
                v.addReservation(this);
            }
        }
    }

    public List<Passager> getPassagers() { return passagers; }
    public void addPassager(Passager p) {
        if (p != null && !passagers.contains(p)) {
            passagers.add(p);
            p.setReservation(this);
        }
    }

    public void removePassager(Passager p) {
        if (passagers.remove(p)) {
            p.setReservation(null);
        }
    }

    public void confirmer() { confirme = true; }
    public void annuler() { confirme = false; }
    public boolean isConfirme() { return confirme; }
}
