package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    private final int PRENOM_LENGTH = 45;
    private final int NOM_LENGTH = 45;
    private int id;
    private String prenom;
    private String nom;
    private LocalDate date;


    @OneToMany(targetEntity = Inscription.class, fetch = FetchType.LAZY,
    cascade = {CascadeType.ALL}, mappedBy = "etudiant")
    private Set inscriptions = new HashSet();

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

    @Column(name = "prenom", nullable = false, length = PRENOM_LENGTH)
    public String getPrenom() {
        return prenom;
    }

    private void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Column(name = "nom", nullable = false, length = NOM_LENGTH)
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

    public Set<Cours> getCours(){
        Set<Cours> set = new HashSet<>();
        for(Object o : inscriptions){
            set.add(((Inscription)o).getCours());
        }
        return set;
    }

    public void ajouterCours(Cours cours){
        Inscription i = new Inscription(cours,this,null);
        this.inscriptions.add(i);
        cours.getInscriptions().add(i);
    }
}