package entities;

import javax.persistence.*;

@Entity
@Table(name = "inscriptions")
public class Inscription {

    private int id;
    private String grade;
    private Cours cours;
    private Etudiant etudiant;

    public Inscription(){
    }

    public Inscription(Cours cours, Etudiant etudiant, String grade) {
        this.cours=cours;
        this.etudiant=etudiant;
        this.grade=grade;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "grade", nullable = true, length = 1)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cours_id", nullable = true)
    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etudiant_id", nullable = true)
    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }


}
