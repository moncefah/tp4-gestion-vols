package gestionVol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Représente une ville.
 */
public class Ville {
    private String nom;
    private final List<Aeroport> aeroports = new ArrayList<>();

    public Ville(String nom) {
        setNom(nom);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        Objects.requireNonNull(nom, "Le nom de la ville ne peut pas être null");
        this.nom = nom;
    }
    @Override
    public String toString() {
     return nom;
    }

    /**
     * Retourne la liste immuable des aéroports de cette ville.
     */
    public List<Aeroport> getAeroports() {
        return Collections.unmodifiableList(aeroports);
    }

    /**
     * Ajoute un aéroport à la ville avec double navigabilité.
     */
    public void addAeroport(Aeroport aeroport) {
        Objects.requireNonNull(aeroport, "L'aéroport ne peut pas être null");
        if (!aeroports.contains(aeroport)) {
            aeroports.add(aeroport);
            aeroport.setVille(this);
        }
    }

    /**
     * Retire un aéroport de la ville en mettant à jour la référence inverse.
     */
    public void removeAeroport(Aeroport aeroport) {
        if (aeroports.remove(aeroport)) {
            aeroport.setVille(null);
        }
    }
}
