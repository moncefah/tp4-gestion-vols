package app;

import gestionVol.*;
import reservation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 1. Préparation des données
        List<Ville> villes = initializeCities();
        Compagnie af = initializeCompanyAndFlights(villes);

        // 2. Affichage des villes et de leurs aéroports
        System.out.println("=== Villes et Aéroports ===");
        for (Ville ville : villes) {
            System.out.printf("Ville : %s%n", ville.getNom());
            for (Aeroport ap : ville.getAeroports()) {
                System.out.printf("  - %s%n", ap.getNom());
            }
        }

        // 3. Liste des vols de la compagnie
        System.out.printf("%n=== Vols de %s ===%n", af.getNom());
        for (Vol vol : af.getVols()) {
            System.out.printf(
                "Vol %d : %s -> %s | Départ : %s | Arrivée : %s | Durée : %dh | Ouvert : %b%n",
                vol.getNumero(),
                vol.getDepart().getNom(),
                vol.getArrivee().getNom(),
                vol.getDateDepart(),
                vol.getDateArrivee(),
                vol.getDuree().toHours(),
                vol.isOuvert()
            );
        }

        // 4. Création de réservations (non interactif)
        Reservation r1 = new Reservation(10001, LocalDate.of(2025, 6, 1));
        Client c1 = new Client("Martin", "MRT123", "Visa", "martin@example.com");
        c1.addReservation(r1);
        r1.setVol(af.getVols().get(0)); // premier vol
        r1.addPassager(new Passager("Alice Martin"));
        r1.addPassager(new Passager("Bob Martin"));
        r1.confirmer();

        Reservation r2 = new Reservation(10002, LocalDate.of(2025, 6, 2));
        Client c2 = new Client("Durand", "DRD456", "Mastercard", "durand@example.com");
        c2.addReservation(r2);
        r2.setVol(af.getVols().get(1)); // deuxième vol
        r2.addPassager(new Passager("Claire Durand"));
        // on laisse cette réservation non confirmée

        // 5. Affichage des réservations par vol
        System.out.println("\n=== Réservations par vol ===");
        for (Vol vol : af.getVols()) {
            System.out.printf("Vol %d (%s -> %s) :%n",
                vol.getNumero(),
                vol.getDepart().getNom(),
                vol.getArrivee().getNom()
            );
            if (vol.getReservations().isEmpty()) {
                System.out.println("  (aucune réservation)");
            } else {
                for (Reservation res : vol.getReservations()) {
                    System.out.printf(
                        "  #%d | Client: %s | Passagers: %d | Confirmée: %b%n",
                        res.getNumero(),
                        res.getClient().getNom(),
                        res.getPassagers().size(),
                        res.isConfirme()
                    );
                }
            }
        }

        // 6. Fermeture d'un vol et test d'état
        Vol lastFlight = af.getVols().get(af.getVols().size() - 1);
        lastFlight.fermer();
        System.out.printf("%nLe vol %d est maintenant fermé : ouvert = %b%n",
            lastFlight.getNumero(),
            lastFlight.isOuvert()
        );
    }

    private static List<Ville> initializeCities() {
        Ville paris = new Ville("Paris");
        Ville lyon  = new Ville("Lyon");
        Ville nice  = new Ville("Nice");

        Aeroport cdg  = new Aeroport("CDG");
        Aeroport orly = new Aeroport("Orly");
        Aeroport stEx = new Aeroport("Saint-Exupéry");
        Aeroport nce  = new Aeroport("Nice Côte d'Azur");

        paris.addAeroport(cdg);
        paris.addAeroport(orly);
        lyon.addAeroport(stEx);
        nice.addAeroport(nce);

        List<Ville> villes = new ArrayList<>();
        villes.add(paris);
        villes.add(lyon);
        villes.add(nice);
        return villes;
    }

    private static Compagnie initializeCompanyAndFlights(List<Ville> villes) {
        Compagnie af = new Compagnie("Air France");

        Aeroport cdg  = villes.get(0).getAeroports().get(0);
        Aeroport orly = villes.get(0).getAeroports().get(1);
        Aeroport stEx = villes.get(1).getAeroports().get(0);
        Aeroport nce  = villes.get(2).getAeroports().get(0);

        // Vols avec escales multiples
        Vol v1 = new Vol(2001,
            LocalDateTime.of(2025, 7, 1,  8, 30),
            LocalDateTime.of(2025, 7, 1, 12, 45),
            cdg, nce);
        Escale es1 = new Escale(
            stEx,  // <-- Aéroport de l'escale
            LocalDateTime.of(2025, 7, 1, 10,  0),
            LocalDateTime.of(2025, 7, 1, 10, 45)
        );
        v1.addEscale(es1);

        Vol v2 = new Vol(2002,
            LocalDateTime.of(2025, 7, 2, 14,  0),
            LocalDateTime.of(2025, 7, 2, 15, 30),
            stEx, orly);

        Vol v3 = new Vol(2003,
            LocalDateTime.of(2025, 7, 3,  6, 15),
            LocalDateTime.of(2025, 7, 3,  8,  0),
            orly, cdg);

        af.addVol(v1);
        af.addVol(v2);
        af.addVol(v3);

        // Ouvre tous les vols
        for (Vol v : af.getVols()) {
            v.ouvrir();
        }

        return af;
    }
}
