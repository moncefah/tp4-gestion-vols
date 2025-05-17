package reservation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Représente un client effectuant des réservations.
 */
public class Client {
    private String nom;
    private String referencePaiement;
    private String contact;
    private final List<Reservation> reservations = new ArrayList<>();

    public Client(String nom, String referencePaiement, String contact) {
        setNom(nom);
        setReferencePaiement(referencePaiement);
        setContact(contact);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        Objects.requireNonNull(nom, "Le nom du client ne peut pas être null");
        this.nom = nom;
    }

    public String getReferencePaiement() {
        return referencePaiement;
    }

    public void setReferencePaiement(String referencePaiement) {
        Objects.requireNonNull(referencePaiement, "La référence de paiement ne peut pas être null");
        this.referencePaiement = referencePaiement;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        Objects.requireNonNull(contact, "Le contact ne peut pas être null");
        this.contact = contact;
    }
    public String toString() {
        return nom;
    }
    /**
     * Retourne la liste immuable des réservations de ce client.
     */
    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }

    /**
     * Ajoute une réservation pour ce client, met à jour la navigation inverse.
     */
    public void addReservation(Reservation reservation) {
        Objects.requireNonNull(reservation, "La réservation ne peut pas être null");
        if (!reservations.contains(reservation)) {
            reservations.add(reservation);
            reservation.setClient(this);
        }
    }

    /**
     * Retire une réservation de ce client, met à jour la navigation inverse.
     */
    public void removeReservation(Reservation reservation) {
        if (reservations.remove(reservation)) {
            reservation.setClient(null);
        }
    }
}
