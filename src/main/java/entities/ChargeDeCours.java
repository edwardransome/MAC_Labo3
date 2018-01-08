package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class ChargeDeCours implements Enseignant {

    @Column(name = "nomComplet", nullable = false, length = 45)
    private String nomComplet;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

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
        return "Chargé de cours: " + nomComplet;
    }

}