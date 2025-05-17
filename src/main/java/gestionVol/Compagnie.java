package gestionVol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Représente une compagnie aérienne.
 */
public class Compagnie {
    private String nom;
    private final List<Vol> vols = new ArrayList<>();

    public Compagnie(String nom) {
        setNom(nom);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        Objects.requireNonNull(nom, "Le nom de la compagnie ne peut pas être null");
        this.nom = nom;
    }
    @Override
    public String toString() {
        return nom;
    }

    /**
     * Retourne la liste des vols proposés par cette compagnie.
     */
    public List<Vol> getVols() {
        return Collections.unmodifiableList(vols);
    }

    /**
     * Ajoute un vol à la compagnie avec double navigabilité.
     */
    public void addVol(Vol vol) {
        Objects.requireNonNull(vol, "Le vol ne peut pas être null");
        if (!vols.contains(vol)) {
            vols.add(vol);
            vol.setCompagnie(this);
        }
    }

    /**
     * Retire un vol de la compagnie en mettant à jour la référence inverse.
     */
    public void removeVol(Vol vol) {
        if (vols.remove(vol)) {
            vol.setCompagnie(null);
        }
    }
}
