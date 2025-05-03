package gestionVol;

import java.util.ArrayList;
import java.util.List;

public class Compagnie {
    private String nom;
    private List<Vol> vols = new ArrayList<>();

    public Compagnie(String nom) {
        this.nom = nom;
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public List<Vol> getVols() { return vols; }

    public void addVol(Vol vol) {
        if (vol != null && !vols.contains(vol)) {
            vols.add(vol);
            vol.setCompagnie(this);
        }
    }

    public void removeVol(Vol vol) {
        if (vols.remove(vol)) {
            vol.setCompagnie(null);
        }
    }
}
