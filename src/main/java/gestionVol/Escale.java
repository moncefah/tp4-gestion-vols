package gestionVol;

import java.time.Duration;
import java.time.LocalDateTime;

public class Escale {
    private LocalDateTime depart;
    private LocalDateTime arrivee;
    private Duration duree;
    private Vol vol;
    private Aeroport aeroport;

    public Escale(Aeroport aeroport,
              LocalDateTime depart,
              LocalDateTime arrivee) {
    this.aeroport = aeroport;
    this.depart   = depart;
    this.arrivee  = arrivee;
    this.duree    = Duration.between(depart, arrivee);
    if (aeroport != null) aeroport.addEscale(this);
    }

    public LocalDateTime getDepart() { return depart; }
    public void setDepart(LocalDateTime depart) {
        this.depart = depart;
        updateDuree();
    }

    public LocalDateTime getArrivee() { return arrivee; }
    public void setArrivee(LocalDateTime arrivee) {
        this.arrivee = arrivee;
        updateDuree();
    }

    public Duration getDuree() { return duree; }
    private void updateDuree() {
        if (depart != null && arrivee != null) {
            duree = Duration.between(depart, arrivee);
        }
    }

    public Vol getVol() { return vol; }
    public void setVol(Vol v) {
        if (this.vol != v) {
            Vol old = this.vol;
            this.vol = v;
            if (old != null) old.removeEscale(this);
            if (v != null && !v.getEscales().contains(this)) v.addEscale(this);
        }
    }
    public Aeroport getAeroport() { return aeroport; }
    public void setAeroport(Aeroport a) {
        if (this.aeroport != a) {
            Aeroport old = this.aeroport;
            this.aeroport = a;
            if (old != null) old.removeEscale(this);
            if (a != null && !a.getEscales().contains(this)) a.addEscale(this);
        }
    }
    
}
