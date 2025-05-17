package reservation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Représente un passager inclus dans une ou plusieurs réservations.
 */
public class Passager {
    private String nom;
    private final List<Reservation> reservations = new ArrayList<>();

    public Passager(String nom) {
        setNom(nom);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        Objects.requireNonNull(nom, "Le nom du passager ne peut pas être null");
        this.nom = nom;
    }

    /**
     * Retourne la liste immuable des réservations de ce passager.
     */
    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }
    public String toString() {
        return nom;
    }
    /**
     * Ajoute une réservation à ce passager, met à jour la navigation inverse.
     */
    public void addReservation(Reservation reservation) {
        Objects.requireNonNull(reservation, "La réservation ne peut pas être null");
        if (!reservations.contains(reservation)) {
            reservations.add(reservation);
            reservation.addPassager(this);
        }
    }

    /**
     * Retire une réservation de ce passager, met à jour la navigation inverse.
     */
    public void removeReservation(Reservation reservation) {
        if (reservations.remove(reservation)) {
            reservation.removePassager(this);
        }
    }
}