package models;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.annotations.Cascade;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "prenom", nullable = false, length = PRENOM_LENGTH)
    private String prenom;

    @Column(name = "nom", nullable = false, length = NOM_LENGTH)
    private String nom;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @OneToMany(targetEntity = Inscription.class, fetch = FetchType.LAZY, mappedBy = "etudiant")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
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

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
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

    public LocalDate getDate() {
        return date;
    }

    private void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDateInscription(){ return date; }


    public Set<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(Set<Inscription> inscriptions) {
        this.inscriptions=inscriptions;
    }

    public Set<Cours> getCours(){
        Set<Cours> set = new HashSet<>();
        for(Object o : inscriptions){
            set.add(((Inscription)o).getCours());
        }
        return set;
    }

    public void ajouterCours(Cours cours){
        Inscription i = new Inscription(cours,this,'\0');
    }

    public void attribuerGrade(Cours cours, char grade, Session session){
        session.beginTransaction();

        boolean found = false;
        for(Object o : inscriptions){
            if(cours == ((Inscription) o).getCours()){
                ((Inscription) o).setGrade(grade);
                found = true;
            }
        }
        if(!found){
            throw new IllegalArgumentException("L'etudiant n'est pas inscrit a ce cours");
        }
        session.save(this);
        session.getTransaction().commit();
    }

    public List<Enseignant> getEnseignants(Session session){
        LinkedList<Enseignant> result = new LinkedList<>();

        Criteria criteria = session.createCriteria(Inscription.class);
        Criterion critere = Restrictions.eq("etudiant" , this);
        criteria.add(critere);

        List<Inscription> inscriptions = criteria.list();

        for(Inscription inscription : inscriptions){
            Enseignant enseignant = inscription.getCours().getEnseignant();
            if(!result.contains(enseignant)){
                result.add(enseignant);
            }
        }

        return result;
    }


}