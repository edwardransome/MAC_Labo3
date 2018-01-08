package models;

import org.hibernate.annotations.Cascade;
import javax.persistence.*;

@Entity
@Table(name = "inscriptions")
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "grade", nullable = true, length = 1)
    private char grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cours_id", nullable = true)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Cours cours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etudiant_id", nullable = true)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Etudiant etudiant;

    public Inscription(){
    }

    public Inscription(Cours cours, Etudiant etudiant, char grade) {
        this.grade=grade;
        setCours(cours);
        setEtudiant(etudiant);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
        cours.getInscriptions().add(this);
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
        etudiant.getInscriptions().add(this);
    }


}
