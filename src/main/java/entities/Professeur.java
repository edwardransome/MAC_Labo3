package entities;

import javax.persistence.Entity;

@Entity
public class Professeur implements Enseignant {

    private String prenom;
    private String nom;
    private String sigle;

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
        return "Pr: " + nom + " " + prenom + " [" + sigle + "]";
    }

}
