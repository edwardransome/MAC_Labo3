package entities;

import javax.persistence.*;

@Entity
@Table(name = "inscriptions")
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "grade", nullable = true, length = 1)
    private String grade;

    
    private int cours_id;

    private int etudiant_id;


}
