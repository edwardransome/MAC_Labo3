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
        session.save(bob);
        session.getTransaction().commit();

        //2eme transaction

        session.beginTransaction();
        Etudiant loaded = session.load(Etudiant.class, 1);
        System.out.println("Loaded student: " + loaded);

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();

        System.out.println("Session Factory closed");


    }
}