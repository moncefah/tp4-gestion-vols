package gestionVol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Représente un aéroport.
 */
public class Aeroport {
    private String nom;
    private Ville ville;
    private final List<Vol> volsDepart = new ArrayList<>();
    private final List<Vol> volsArrivee = new ArrayList<>();
    private final List<Escale> escales = new ArrayList<>();

    public Aeroport(String nom, Ville ville) {
        Objects.requireNonNull(nom, "Le nom de l'aéroport ne peut pas être null");
        Objects.requireNonNull(ville, "La ville ne peut pas être null");
        this.nom = nom;
        this.ville = ville;
        ville.addAeroport(this);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        Objects.requireNonNull(nom, "Le nom de l'aéroport ne peut pas être null");
        this.nom = nom;
    }

    public Ville getVille() {
        return ville;
    }

    /**
     * Définit la ville de cet aéroport, gère la double navigabilité.
     * Accepte null pour retirer l'aéroport de sa ville.
     */
    public void setVille(Ville nouvelleVille) {
        if (this.ville != nouvelleVille) {
            // Retirer de l'ancienne ville
            if (this.ville != null) {
                this.ville.removeAeroport(this);
            }
            // Mettre à jour la référence
            this.ville = nouvelleVille;
            // Ajouter à la nouvelle ville
            if (nouvelleVille != null) {
                nouvelleVille.addAeroport(this);
            }
        }
    }

    public List<Vol> getVolsDepart() {
        return Collections.unmodifiableList(volsDepart);
    }

    public void addVolDepart(Vol vol) {
        Objects.requireNonNull(vol, "Le vol ne peut pas être null");
        if (!volsDepart.contains(vol)) {
            volsDepart.add(vol);
            vol.setAeroportDepart(this);
        }
    }

    public void removeVolDepart(Vol vol) {
        if (volsDepart.remove(vol)) {
            vol.setAeroportDepart(null);
        }
    }

    public List<Vol> getVolsArrivee() {
        return Collections.unmodifiableList(volsArrivee);
    }

    public void addVolArrivee(Vol vol) {
        Objects.requireNonNull(vol, "Le vol ne peut pas être null");
        if (!volsArrivee.contains(vol)) {
            volsArrivee.add(vol);
            vol.setAeroportArrivee(this);
        }
    }

    public void removeVolArrivee(Vol vol) {
        if (volsArrivee.remove(vol)) {
            vol.setAeroportArrivee(null);
        }
    }

    public List<Escale> getEscales() {
        return Collections.unmodifiableList(escales);
    }

    public void addEscale(Escale escale) {
        Objects.requireNonNull(escale, "L'escale ne peut pas être null");
        if (!escales.contains(escale)) {
            escales.add(escale);
            escale.setAeroport(this);
        }
    }

    public void removeEscale(Escale escale) {
        if (escales.remove(escale)) {
            escale.setAeroport(null);
        }
    }

    @Override
    public String toString() {
        return nom;
    }
}