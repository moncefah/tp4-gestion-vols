package reservation;

import gestionVol.Vol;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Représente une réservation effectuée par un client pour un vol.
 */
public class Reservation {
    private String numero;
    private LocalDate date;
    private Client client;
    private Vol vol;
    private final List<Passager> passagers = new ArrayList<>();
    private boolean confirme = false;

    public Reservation(String numero, LocalDate date) {
        setNumero(numero);
        setDate(date);
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        Objects.requireNonNull(numero, "Le numéro de réservation ne peut pas être null");
        this.numero = numero;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        Objects.requireNonNull(date, "La date de réservation ne peut pas être null");
        this.date = date;
        // Validation par rapport au vol si déjà défini
        if (vol != null && date.isAfter(vol.getDateDepart().toLocalDate())) {
            throw new IllegalArgumentException("La date de réservation doit être antérieure ou égale à la date de départ du vol");
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if (this.client != client) {
            Client ancien = this.client;
            this.client = client;
            if (ancien != null) {
                ancien.removeReservation(this);
            }
            if (client != null) {
                client.addReservation(this);
            }
        }
    }

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        if (this.vol != vol) {
            Vol ancien = this.vol;
            this.vol = vol;
            if (ancien != null) {
                ancien.removeReservation(this);
            }
            if (vol != null) {
                vol.addReservation(this);
                // Validation date réservation vs date départ du vol
                if (date != null && date.isAfter(vol.getDateDepart().toLocalDate())) {
                    throw new IllegalArgumentException("La date de réservation doit être antérieure ou égale à la date de départ du vol");
                }
            }
        }
    }
    public String toString() {
        return numero + " du " + date;
    }

    public List<Passager> getPassagers() {
        return Collections.unmodifiableList(passagers);
    }

    public void addPassager(Passager passager) {
        Objects.requireNonNull(passager, "Le passager ne peut pas être null");
        if (!passagers.contains(passager)) {
            passagers.add(passager);
            passager.addReservation(this);
        }
    }

    public void removePassager(Passager passager) {
        if (passagers.remove(passager)) {
            passager.removeReservation(this);
        }
    }

    /**
     * Marque la réservation comme confirmée.
     */
    public void confirmer() {
        this.confirme = true;
    }

    /**
     * Annule la réservation.
     */
    public void annuler() {
        this.confirme = false;
    }

    public boolean isConfirme() {
        return confirme;
    }
}
