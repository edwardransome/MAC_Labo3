package models;

import org.hibernate.Session;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.MetaValue;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Entity representing a cours
 *
 * @author Edward Ransome
 * @author Michaël Spierer
 */

@Entity
@Table(name = "cours")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Cours {
    private final int TITRE_LENGTH = 45;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "titre", nullable = false, length = TITRE_LENGTH)
    private String titre;

    @Column(name = "credit", nullable = false)
    private int credit;

    @OneToMany(targetEntity = Inscription.class, fetch = FetchType.LAZY, mappedBy = "cours")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set inscriptions = new HashSet();

    @Any(metaColumn = @Column(name = "enseignant"))
    @AnyMetaDef(idType = "int", metaType = "string",
            metaValues = {
                    @MetaValue(targetEntity = Professeur.class, value = "PROFESSEUR"),
                    @MetaValue(targetEntity = ChargeDeCours.class, value = "CHARGE_DE_COURS")
            })

    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant;


    public Cours() {
    }

    public Cours(String titre, int credit, Enseignant enseignant) {
        this.titre = titre;
        this.credit = credit;
        this.enseignant = enseignant;
    }

    public String toString(){
        return "Cours : "+titre+" , crédit : "+credit;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getCredit() {
        return credit;
    }

    private void setCredit(int credit) {
        this.credit = credit;
    }

    public String getTitre() {
        return titre;
    }

    private void setTitre(String titre) {
        this.titre = titre;
    }

    public Set<Inscription> getInscriptions() {
        return inscriptions;
    }

    public Set<Etudiant> getEtudiants(){
        Set<Etudiant> set = new HashSet<>();
        for(Object o : inscriptions){
            set.add(((Inscription)o).getEtudiant());
        }
        return set;
    }

    public List<Etudiant> etudiantsEnAttente(Session session){
        return getInscriptions()
                .stream()
                .filter(w -> w.getGrade() == '\0')
                .map(w -> w.getEtudiant())
                .collect(Collectors.toList());
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }
}