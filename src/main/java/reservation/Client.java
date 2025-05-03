package reservation;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String nom;
    private String reference;
    private String paiement;
    private String contact;
    private List<Reservation> reservations = new ArrayList<>();

    public Client(String nom, String reference, String paiement, String contact) {
        this.nom = nom;
        this.reference = reference;
        this.paiement = paiement;
        this.contact = contact;
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public String getPaiement() { return paiement; }
    public void setPaiement(String paiement) { this.paiement = paiement; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public List<Reservation> getReservations() { return reservations; }
    public void addReservation(Reservation r) {
        if (r != null && !reservations.contains(r)) {
            reservations.add(r);
            r.setClient(this);
        }
    }

    public void removeReservation(Reservation r) {
        if (reservations.remove(r)) {
            r.setClient(null);
        }
    }
}
