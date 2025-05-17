package gestionVol;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Représente une escale d'un vol dans un aéroport.
 */
public class Escale {
    private LocalDateTime depart;
    private LocalDateTime arrivee;
    private Vol vol;
    private Aeroport aeroport;

    public Escale(LocalDateTime depart, LocalDateTime arrivee) {
        setDepart(depart);
        setArrivee(arrivee);
    }

    public LocalDateTime getDepart() {
        return depart;
    }

    public void setDepart(LocalDateTime depart) {
        Objects.requireNonNull(depart, "La date de départ de l'escale ne peut pas être null");
        if (arrivee != null && depart.isAfter(arrivee)) {
            throw new IllegalArgumentException("La date de départ doit être antérieure ou égale à la date d'arrivée");
        }
        this.depart = depart;
    }

    public LocalDateTime getArrivee() {
        return arrivee;
    }

    public void setArrivee(LocalDateTime arrivee) {
        Objects.requireNonNull(arrivee, "La date d'arrivée de l'escale ne peut pas être null");
        if (depart != null && arrivee.isBefore(depart)) {
            throw new IllegalArgumentException("La date d'arrivée doit être postérieure ou égale à la date de départ");
        }
        this.arrivee = arrivee;
    }

    /**
     * Durée de l'escale calculée à la volée.
     */
    public Duration getDuree() {
        return Duration.between(depart, arrivee);
    }

    public Vol getVol() {
        return vol;
    }

    /**
     * Définit le vol associé à cette escale avec double navigabilité.
     */
    public void setVol(Vol vol) {
        if (this.vol != vol) {
            Vol ancien = this.vol;
            this.vol = vol;
            if (ancien != null) {
                ancien.removeEscale(this);
            }
            if (vol != null) {
                vol.addEscale(this);
            }
        }
    }

    public Aeroport getAeroport() {
        return aeroport;
    }

    /**
     * Définit l'aéroport associé à cette escale avec double navigabilité.
     */
    public void setAeroport(Aeroport aeroport) {
        if (this.aeroport != aeroport) {
            Aeroport ancien = this.aeroport;
            this.aeroport = aeroport;
            if (ancien != null) {
                ancien.removeEscale(this);
            }
            if (aeroport != null) {
                aeroport.addEscale(this);
            }
        }
    }
}
