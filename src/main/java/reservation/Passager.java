package reservation;

public class Passager {
    private String nom;
    private Reservation reservation;

    public Passager(String nom) {
        this.nom = nom;
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Reservation getReservation() { return reservation; }
    public void setReservation(Reservation r) {
        if (this.reservation != r) {
            Reservation old = this.reservation;
            this.reservation = r;
            if (old != null) old.removePassager(this);
            if (r != null && !r.getPassagers().contains(this)) {
                r.addPassager(this);
            }
        }
    }
}
