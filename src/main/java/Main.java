import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;

public class Main{

    private static void afficheCours(Session session){

        List<Cours> cours = session.createQuery("from Cours").list();

        System.out.println("Cours: ");

        for(Cours c: cours){
            System.out.println(c.getTitre());
            c.getEtudiants().forEach(etudiant -> {
                System.out.print("\t- ");
                System.out.println(etudiant);
            });
        }
    } 

    private static void afficheEtudiants(Session session){
        List<Etudiant> etudiants = session.createQuery("from Etudiant").list();

        System.out.println("Etudiants: ");

        for(Etudiant etudiant: etudiants){
            System.out.println(etudiant);
            etudiant.getCours().forEach(course -> {
                System.out.print("\t- ");
                System.out.println(course.getTitre());
            });
        }
    }

    private static void peuplement(Session session){
        session.beginTransaction();
        Etudiant bob = new Etudiant("Bob", "Dupont", LocalDate.of(1990,1,1));
        Etudiant aurelie = new Etudiant("Aurelie", "Eilerua", LocalDate.of(1991,4,5));
        Etudiant michael = new Etudiant("Michael", "Jackson", LocalDate.of(1993,9,8));
        Etudiant eddie = new Etudiant("Eddie", "Malou", LocalDate.of(1994,12,3));

        Enseignant professeur = new Professeur("Jean","Yves");
        Enseignant chargeDeCours = new ChargeDeCours("pionpion");

        Cours tweb = new Cours("TWEB",4,professeur);
        Cours amt = new Cours("AMT",3,professeur);
        Cours mac = new CoursExterieur("MAC",4,chargeDeCours,"HEIG");


        //Incscrire des eleves a des cours
        bob.ajouterCours(tweb);
        bob.ajouterCours(mac);
        bob.ajouterCours(amt);
        michael.ajouterCours(amt);
        michael.ajouterCours(tweb);
        michael.ajouterCours(mac);
        eddie.ajouterCours(tweb);
        eddie.ajouterCours(amt);

        //Save les enseignants
        session.save(professeur);
        session.save(chargeDeCours);

        //Save les etudiants
        session.save(bob);
        session.save(aurelie);
        session.save(michael);
        session.save(eddie);

        //Save les cours
        session.save(tweb);
        session.save(amt);
        session.save(mac);


        session.getTransaction().commit();
    }

    public static void main(String [] args)
    {
        System.out.println("Start session Factory");

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();


        System.out.println("Start transaction 1");
        peuplement(session);

        System.out.println("Start transaction 2");

        session.beginTransaction();


        afficheEtudiants(session);
        afficheCours(session);


        session.getTransaction().commit();

        session.close();
        sessionFactory.close();

        System.out.println("Session Factory closed");

        

    }
}