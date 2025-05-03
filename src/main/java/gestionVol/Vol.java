package gestionVol;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import reservation.Reservation;

public class Vol {
    private int numero;
    private LocalDateTime dateDepart;
    private LocalDateTime dateArrivee;
    private Compagnie compagnie;
    private Aeroport depart;
    private Aeroport arrivee;
    private List<Escale> escales = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();
    private boolean ouvert = false;

    public Vol(int numero, LocalDateTime dateDepart, LocalDateTime dateArrivee, Aeroport depart, Aeroport arrivee) {
        this.numero = numero;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.depart = depart;
        this.arrivee = arrivee;
        if (depart != null) depart.addVolDepart(this);
        if (arrivee != null) arrivee.addVolArrivee(this);
    }

    public int getNumero() { return numero; }
    public LocalDateTime getDateDepart() { return dateDepart; }
    public LocalDateTime getDateArrivee() { return dateArrivee; }
    public Duration getDuree() { return Duration.between(dateDepart, dateArrivee); }

    public Compagnie getCompagnie() { return compagnie; }
    public void setCompagnie(Compagnie compagnie) {
        if (this.compagnie != compagnie) {
            Compagnie old = this.compagnie;
            this.compagnie = compagnie;
            if (old != null) old.removeVol(this);
            if (compagnie != null && !compagnie.getVols().contains(this)) {
                compagnie.addVol(this);
            }
        }
    }

    public Aeroport getDepart() { return depart; }
    public void setDepart(Aeroport depart) {
        if (this.depart != depart) {
            if (this.depart != null) this.depart.removeVolDepart(this);
            this.depart = depart;
            if (depart != null) depart.addVolDepart(this);
        }
    }

    public Aeroport getArrivee() { return arrivee; }
    public void setArrivee(Aeroport arrivee) {
        if (this.arrivee != arrivee) {
            if (this.arrivee != null) this.arrivee.removeVolArrivee(this);
            this.arrivee = arrivee;
            if (arrivee != null) arrivee.addVolArrivee(this);
        }
    }

    public List<Escale> getEscales() { return escales; }
    public void addEscale(Escale escale) {
        if (escale != null && !escales.contains(escale)) {
            escales.add(escale);
            escale.setVol(this);
        }
    }

    public void removeEscale(Escale escale) {
        if (escales.remove(escale)) {
            escale.setVol(null);
        }
    }

    public List<Reservation> getReservations() { return reservations; }
    public void addReservation(Reservation reservation) {
        if (reservation != null && !reservations.contains(reservation)) {
            reservations.add(reservation);
            reservation.setVol(this);
        }
    }

    public void removeReservation(Reservation reservation) {
        if (reservations.remove(reservation)) {
            reservation.setVol(null);
        }
    }

    public void ouvrir() { this.ouvert = true; }
    public void fermer() { this.ouvert = false; }
    public boolean isOuvert() { return ouvert; }
}
