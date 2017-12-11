package entities;

import javax.persistence.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Entity representing a cours
 *
 * @author Edward Ransome
 * @author Michaël Spierer
 */

@Entity
@Table(name = "cours")
public class Cours {
    private final int TITRE_LENGTH = 45;

    private int id;
    private String titre;
    private int credit;

    @OneToMany(targetEntity = Inscription.class, fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}, mappedBy = "cours")
    private Set inscriptions = new HashSet();

    public Cours() {
    }

    public String toString(){
        return "Cours : "+titre+" , crédit : "+credit;
    }

    public Cours(String titre, int credit) {
        this.titre = titre;
        this.credit = credit;
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

    @Column(name = "credit", nullable = false)
    public int getCredit() {
        return credit;
    }

    private void setCredit(int credit) {
        this.credit = credit;
    }

    @Column(name = "titre", nullable = false, length = TITRE_LENGTH)
    public String getTitre() {
        return titre;
    }

    private void setTitre(String titre) {
        this.titre = titre;
    }
}