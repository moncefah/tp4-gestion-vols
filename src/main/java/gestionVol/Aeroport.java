package gestionVol;

import java.util.ArrayList;
import java.util.List;

public class Aeroport {
    private String nom;
    private List<Ville> villes = new ArrayList<>();
    private List<Vol> volsDepart = new ArrayList<>();
    private List<Vol> volsArrivee = new ArrayList<>();
    private List<Escale> escales = new ArrayList<>();

    public Aeroport(String nom) {
        this.nom = nom;
    }

    // ----- Gestion des vols au départ -----
    public List<Vol> getVolsDepart() {
        return volsDepart;
    }

    public void addVolDepart(Vol vol) {
        if (vol != null && !volsDepart.contains(vol)) {
            volsDepart.add(vol);
            // appel corrigé
            vol.setDepart(this);
        }
    }

    public void removeVolDepart(Vol vol) {
        if (volsDepart.remove(vol)) {
            // appel corrigé
            vol.setDepart(null);
        }
    }

    // ----- Gestion des vols à l'arrivée -----
    public List<Vol> getVolsArrivee() {
        return volsArrivee;
    }

    public void addVolArrivee(Vol vol) {
        if (vol != null && !volsArrivee.contains(vol)) {
            volsArrivee.add(vol);
            // appel corrigé
            vol.setArrivee(this);
        }
    }

    public void removeVolArrivee(Vol vol) {
        if (volsArrivee.remove(vol)) {
            // appel corrigé
            vol.setArrivee(null);
        }
    }

    // ----- Gestion des escales -----
    public List<Escale> getEscales() {
        return escales;
    }

    public void addEscale(Escale escale) {
        if (escale != null && !escales.contains(escale)) {
            escales.add(escale);
            escale.setAeroport(this);
        }
    }

    public void removeEscale(Escale escale) {
        if (escales.remove(escale)) {
            escale.setAeroport(null);
        }
    }

    // ----- Gestion des villes desservies -----
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void addVille(Ville ville) {
        if (ville != null && !villes.contains(ville)) {
            villes.add(ville);
            ville.addAeroport(this);
        }
    }

    public void removeVille(Ville ville) {
        if (villes.remove(ville)) {
            ville.removeAeroport(this);
        }
    }
}
