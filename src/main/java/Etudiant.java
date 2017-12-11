import java.time.LocalDate;

/**
 * Entity representing a student
 *
 * @author Edward Ransome
 * @author Michaël Spierer
 */

public class Etudiant {
    private int id;
    private String prenom;
    private String nom;
    private LocalDate date;

    public Etudiant() {
    }

    public Etudiant(String prenom, String nom, LocalDate date) {
        this.prenom = prenom;
        this.nom = nom;
        this.date = date;
    }

    public String toString(){
        return prenom + " " + nom + " né le " + date;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    private void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDate() {
        return date;
    }

    private void setDate(LocalDate date) {
        this.date = date;
    }
}