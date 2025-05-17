package gestionVol;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import reservation.*;

/**
 * Représente un vol.
 */
public class Vol {
    private String numero;
    private LocalDateTime dateDepart;
    private LocalDateTime dateArrivee;
    private Compagnie compagnie;
    private Aeroport aeroportDepart;
    private Aeroport aeroportArrivee;
    private final List<Escale> escales = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();
    private boolean ouvert = false;

    public Vol(String numero, LocalDateTime dateDepart, LocalDateTime dateArrivee) {
        setNumero(numero);
        setDateDepart(dateDepart);
        setDateArrivee(dateArrivee);
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        Objects.requireNonNull(numero, "Le numéro du vol ne peut pas être null");
        this.numero = numero;
    }

    public LocalDateTime getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDateTime dateDepart) {
        Objects.requireNonNull(dateDepart, "La date de départ ne peut pas être null");
        if (dateArrivee != null && dateDepart.isAfter(dateArrivee)) {
            throw new IllegalArgumentException("La date de départ doit être antérieure ou égale à la date d'arrivée");
        }
        this.dateDepart = dateDepart;
    }

    public LocalDateTime getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(LocalDateTime dateArrivee) {
        Objects.requireNonNull(dateArrivee, "La date d'arrivée ne peut pas être null");
        if (dateDepart != null && dateArrivee.isBefore(dateDepart)) {
            throw new IllegalArgumentException("La date d'arrivée doit être postérieure ou égale à la date de départ");
        }
        this.dateArrivee = dateArrivee;
    }

    /**
     * Durée du vol calculée à la volée.
     */
    public Duration getDuree() {
        return Duration.between(dateDepart, dateArrivee);
    }

    public boolean isOuvert() {
        return ouvert;
    }

    public void ouvrir() {
        this.ouvert = true;
    }

    public void fermer() {
        this.ouvert = false;
    }

    public Compagnie getCompagnie() {
        return compagnie;
    }

    /**
     * Définit la compagnie avec double navigabilité.
     */
    public void setCompagnie(Compagnie compagnie) {
        if (this.compagnie != compagnie) {
            Compagnie ancienne = this.compagnie;
            this.compagnie = compagnie;
            if (ancienne != null) {
                ancienne.removeVol(this);
            }
            if (compagnie != null) {
                compagnie.addVol(this);
            }
        }
    }

    public Aeroport getAeroportDepart() {
        return aeroportDepart;
    }

    public void setAeroportDepart(Aeroport aeroportDepart) {
        if (this.aeroportDepart != aeroportDepart) {
            Aeroport ancienne = this.aeroportDepart;
            this.aeroportDepart = aeroportDepart;
            if (ancienne != null) {
                ancienne.removeVolDepart(this);
            }
            if (aeroportDepart != null) {
                aeroportDepart.addVolDepart(this);
            }
        }
    }

    public Aeroport getAeroportArrivee() {
        return aeroportArrivee;
    }

    public void setAeroportArrivee(Aeroport aeroportArrivee) {
        if (this.aeroportArrivee != aeroportArrivee) {
            Aeroport ancienne = this.aeroportArrivee;
            this.aeroportArrivee = aeroportArrivee;
            if (ancienne != null) {
                ancienne.removeVolArrivee(this);
            }
            if (aeroportArrivee != null) {
                aeroportArrivee.addVolArrivee(this);
            }
        }
    }

    public List<Escale> getEscales() {
        return Collections.unmodifiableList(escales);
    }
    


    public void addEscale(Escale escale) {
        Objects.requireNonNull(escale, "L'escale ne peut pas être null");
        if (!escales.contains(escale)) {
            escales.add(escale);
            escale.setVol(this);
        }
    }

    public void removeEscale(Escale escale) {
        if (escales.remove(escale)) {
            escale.setVol(null);
        }
    }

    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }

    public void addReservation(Reservation reservation) {
        Objects.requireNonNull(reservation, "La réservation ne peut pas être null");
        if (!reservations.contains(reservation)) {
            reservations.add(reservation);
            reservation.setVol(this);
        }
    }

    public void removeReservation(Reservation reservation) {
        if (reservations.remove(reservation)) {
            reservation.setVol(null);
        }
    }
    public String toString() {
    return String.format("%s [%s → %s]", 
        numero, 
        dateDepart.toLocalTime(), 
        dateArrivee.toLocalTime());
    }
}
