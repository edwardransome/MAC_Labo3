package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Professeur implements Enseignant {

    @Column(name = "prenom", nullable = false, length = 45)
    private String prenom;

    @Column(name = "nom", nullable = false, length = 45)
    private String nom;

    @Column(name = "sigle", nullable = false, length = 10)
    private String sigle;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    public Professeur(){}

    public Professeur(String prenom,String nom) {
        this.prenom = prenom;
        this.nom = nom;
        if (!prenom.isEmpty() && !nom.isEmpty()) {
            this.sigle = ""+prenom.charAt(0) + nom.charAt(0);
        }
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    @Override
    public String toString() {
        return "Prof. " + nom + " " + prenom + " [" + sigle + "]";
    }

}
