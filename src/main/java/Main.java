import entities.Cours;
import entities.Etudiant;
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

    public static void main(String [] args)
    {
        System.out.println("Start session Factory");

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();


        System.out.println("Start transaction 1");
        session.beginTransaction();
        Etudiant bob = new Etudiant("Bob", "Dupont", LocalDate.of(1990,1,1));
        Etudiant aurelie = new Etudiant("Aurelie", "Eilerua", LocalDate.of(1991,4,5));
        Etudiant michael = new Etudiant("Michael", "Jackson", LocalDate.of(1993,9,8));
        Etudiant eddie = new Etudiant("Eddie", "Malou", LocalDate.of(1994,12,3));

        Cours tweb = new Cours("TWEB",4);
        Cours amt = new Cours("AMT",3);
        Cours mac = new Cours("MAC",4);

        //Incscrire des eleves a des cours
        bob.ajouterCours(tweb);
        bob.ajouterCours(mac);
        bob.ajouterCours(amt);
        michael.ajouterCours(amt);
        michael.ajouterCours(tweb);
        michael.ajouterCours(mac);
        eddie.ajouterCours(tweb);
        eddie.ajouterCours(amt);

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



        System.out.println("Start transaction 2");
        //michael.attribuerGrade(amt,'2',session);

        session.beginTransaction();


        afficheEtudiants(session);
        afficheCours(session);

        //List<Etudiant> etudiantList = amt.etudiantsEnAttente(session);

        session.getTransaction().commit();

        session.close();
        session = sessionFactory.openSession();

        System.out.println("Start transaction 3");

        session.beginTransaction();

        tweb = (Cours)session.load(Cours.class, new Integer(1));
        session.delete(tweb);
        session.flush();

        session.getTransaction().commit();


        session.close();
        session = sessionFactory.openSession();

        System.out.println("Start transaction 4");

        session.beginTransaction();


        afficheEtudiants(session);
        afficheCours(session);

        session.getTransaction().commit();

        //refresh
        session.beginTransaction();
        michael = (Etudiant) session.load(Etudiant.class, new Integer(2));
        session.getTransaction().commit();

        System.out.println("Start transaction 5 : addGrade");
        michael.attribuerGrade(amt,'2',session);

        session.close();
        sessionFactory.close();

        System.out.println("Session Factory closed");

        

    }
}