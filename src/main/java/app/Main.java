package app;

import gestionVol.*;
import reservation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Création d'une ville et de deux aéroports
        Ville paris = new Ville("Paris");
        Aeroport cdg = new Aeroport("Charles de Gaulle", paris);
        Aeroport orly = new Aeroport("Orly", paris);

        // Création d'une compagnie et d'un vol
        Compagnie airFrance = new Compagnie("Air France");
        LocalDateTime dep = LocalDateTime.of(2025, 6, 1, 10, 0);
        LocalDateTime arr = LocalDateTime.of(2025, 6, 1, 12, 30);
        Vol volAF123 = new Vol("AF123", dep, arr);
        airFrance.addVol(volAF123);

        // Assignation des aéroports de départ et d'arrivée
        volAF123.setAeroportDepart(cdg);
        volAF123.setAeroportArrivee(orly);

        // Ajout d'une escale
        LocalDateTime escDep = LocalDateTime.of(2025, 6, 1, 11, 0);
        LocalDateTime escArr = LocalDateTime.of(2025, 6, 1, 11, 15);
        Escale escale = new Escale(escDep, escArr);
        volAF123.addEscale(escale);
        escale.setAeroport(cdg);

        // Création d'un client et d'une réservation
        Client client = new Client("Dupont", "REF123", "+33123456789");
        Reservation reservation = new Reservation("R001", LocalDate.of(2025, 5, 15));
        client.addReservation(reservation);
        reservation.setVol(volAF123);

        // Ajout d'un passager
        Passager passager = new Passager("Martin");
        reservation.addPassager(passager);

        // Affichage de l'état
        System.out.println("Ville: " + paris.getNom());
        System.out.println("Aéroports: " + paris.getAeroports());
        System.out.println("Compagnie: " + airFrance.getNom());
        System.out.println("Vol: " + volAF123.getNumero() + " de " + volAF123.getAeroportDepart().getNom()
                + " à " + volAF123.getAeroportArrivee().getNom() + ", durée=" + volAF123.getDuree());
        System.out.println("Nombre d'escales: " + volAF123.getEscales().size());
        System.out.println("Client: " + client.getNom() + ", Réservations: " + client.getReservations().size());
        System.out.println("Passagers sur R001: " + reservation.getPassagers().get(0).getNom());

        // Tests de bidirectionnalité
        System.out.println("\n-- Tests de bidirectionnalité --");
        System.out.println("Compagnie propose vol: " + airFrance.getVols().contains(volAF123));
        System.out.println("Vol appartient à compagnie: " + (volAF123.getCompagnie() == airFrance));
        System.out.println("CDG volsDepart contient vol: " + cdg.getVolsDepart().contains(volAF123));
        System.out.println("Vol aeroportDepart est CDG: " + (volAF123.getAeroportDepart() == cdg));
        System.out.println("Ville Paris contient CDG: " + paris.getAeroports().contains(cdg));
        System.out.println("Aéroport CDG ville est Paris: " + (cdg.getVille() == paris));
        System.out.println("Client Dupont reservations contient R001: " + client.getReservations().contains(reservation));
        System.out.println("Réservation client est Dupont: " + (reservation.getClient() == client));
        System.out.println("Réservation R001 passagers contient Martin: " + reservation.getPassagers().contains(passager));
        System.out.println("Passager Martin reservations contient R001: " + passager.getReservations().contains(reservation));

        // Tests de suppression bidirectionnelle
        System.out.println("\n-- Tests de suppression bidirectionnelle --");
        // Suppression Vol de la Compagnie
        airFrance.removeVol(volAF123);
        System.out.println("Après suppression vol de Compagnie: " + (!airFrance.getVols().contains(volAF123))
                + ", vol.getCompagnie()==null: " + (volAF123.getCompagnie() == null));
        // Suppression Escale du Vol
        volAF123.removeEscale(escale);
        System.out.println("Après suppression escale du Vol: " + (!volAF123.getEscales().contains(escale))
                + ", escale.getVol()==null: " + (escale.getVol() == null));
        // Suppression Reservation du Client
        client.removeReservation(reservation);
        System.out.println("Après suppression réservation du Client: " + (!client.getReservations().contains(reservation))
                + ", reservation.getClient()==null: " + (reservation.getClient() == null));
        // Suppression Passager de la Réservation
        reservation.removePassager(passager);
        System.out.println("Après suppression passager de réservation: " + (!reservation.getPassagers().contains(passager))
                + ", passager.getReservations().contains==false: " + (!passager.getReservations().contains(reservation)));
        // Suppression Aéroport de la Ville
        paris.removeAeroport(cdg);
        System.out.println("Après suppression CDG de la ville: " + (!paris.getAeroports().contains(cdg))
                + ", cdg.getVille()==null: " + (cdg.getVille() == null));
    }
}
