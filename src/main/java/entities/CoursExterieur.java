package entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CoursExterieur extends Cours {

    @Column(name = "ecole", nullable = false, length = 45)
    private String ecole;

    public CoursExterieur() {
        super();
    }

    public CoursExterieur(String ecole) {
        super();
        this.ecole = ecole;
    }

    public CoursExterieur(String titre, int credit,Enseignant enseignant ,String ecole) {
        super(titre, credit, enseignant);
        this.ecole = ecole;
    }

    public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    @Override
    public String toString() {
        return super.toString() + ", ecole : " + ecole;
    }
}
