public class Main{
    public static void main(String [] args)
    {
        System.out.println("Hello world");

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();




        //1ere transaction


        //2eme transaction

        session.close();
        sessionFactory.close();

    }
}