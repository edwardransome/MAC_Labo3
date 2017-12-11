package entities;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Entity representing a student
 *
 * @author Edward Ransome
 * @author Michaël Spierer
 */

@Entity
@Table(name = "etudiants")
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

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Column(name = "prenom", nullable = false)
    public String getPrenom() {
        return prenom;
    }

    private void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Column(name = "nom", nullable = false)
    public String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        this.nom = nom;
    }

    @Column(name = "date", nullable = false)
    public LocalDate getDate() {
        return date;
    }

    private void setDate(LocalDate date) {
        this.date = date;
    }
}