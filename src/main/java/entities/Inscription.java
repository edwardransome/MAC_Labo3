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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = true)
    private int cours_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = true)
    private int etudiant_id;


}
