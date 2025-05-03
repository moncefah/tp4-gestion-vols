package gestionVol;

import java.util.ArrayList;
import java.util.List;

public class Ville {
    private String nom;
    private List<Aeroport> aeroports = new ArrayList<>();

    public Ville(String nom) {
        this.nom = nom;
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public List<Aeroport> getAeroports() { return aeroports; }
    public void addAeroport(Aeroport a) {
        if (a != null && !aeroports.contains(a)) {
            aeroports.add(a);
            a.addVille(this);
        }
    }
    public void removeAeroport(Aeroport a) {
        if (aeroports.remove(a)) {
            a.removeVille(this);
        }
    }
}
