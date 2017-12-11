import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class Main{
    public static void main(String [] args)
    {
        System.out.println("Start session Factory");

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();


        //1ere transaction
        session.beginTransaction();
        Etudiant bob = new Etudiant("Bob", "Dupont", LocalDate.of(1990,1,1));
        Etudiant aurelie = new Etudiant("Aurelie", "Eilerua", LocalDate.of(1991,4,5));
        Etudiant michael = new Etudiant("Michael", "Jackson", LocalDate.of(1993,9,8));
        Etudiant eddie = new Etudiant("eddie", "Malou", LocalDate.of(1994,12,3));

        Cours tweb = new Cours("TWEB",4);
        Cours amt = new Cours("AMT",3);
        Cours mac = new Cours("MAC",4);

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

        //2eme transaction

        session.close();
        sessionFactory.close();

        System.out.println("Session Factory closed");


    }
}