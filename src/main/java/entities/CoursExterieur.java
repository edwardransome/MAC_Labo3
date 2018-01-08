package entities;

import javax.persistence.Entity;

@Entity
public class CoursExterieur extends Cours {
    private String ecole;

    public CoursExterieur(){
        super();
    }

    public CoursExterieur(String ecole){
        super();
        this.ecole = ecole;
    }

    public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    @Override
    public String toString(){
        return super.toString() + ", ecole : "+ ecole;
    }
}
