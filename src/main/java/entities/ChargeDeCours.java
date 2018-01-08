package entities;

import javax.persistence.Entity;

@Entity
public class ChargeDeCours implements Enseignant {

    private String nomComplet;

    public ChargeDeCours(){}
    public ChargeDeCours(String nomComplet){
        this.nomComplet=nomComplet;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    @Override
    public String toString() {
        return "Charge de cours:" + nomComplet;
    }

}