// Auteur: Eric Lefrançois - Janvier. 2015
package controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;

// Du jar hibernate-core-4.3.5.Final
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import models.*;

//------------------------------------------------------------------------------
public class ORMAccess  {


   @SuppressWarnings("all") 

   private static SessionFactory sessionFactory;

   public ORMAccess() {
      sessionFactory = new Configuration()
            .configure()
            .buildSessionFactory();
   }

   public void peuplerLaBase() throws Exception {
      Session session = sessionFactory.openSession();
      Transaction tx = null;
      List<Etudiant> etudiants = null;
      try {

         tx= session.beginTransaction();

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

         tx.commit();
      }
      catch (Exception e) {
         if (tx!= null){
            try {
               tx.rollback();
               System.out.println(e.toString());
               throw e;
            }
            catch (HibernateException he){
               System.out.println(he.toString());
               throw he;
            }
         }
      }
      finally {
         try{
            session.close();
         }
         catch (HibernateException he){
            System.out.println(he.toString());
         }
      }
   }

   public List<Etudiant> GET_ETUDIANTS() throws Exception {
      Session session = sessionFactory.openSession();
      Transaction tx = null;
      List<Etudiant> etudiants = null;
      try {

         tx= session.beginTransaction();

         etudiants = session.createQuery("from Etudiant").list();

         tx.commit();
      }
      catch (Exception e) {
         if (tx!= null){
            try {
               tx.rollback();
               System.out.println(e.toString());
               throw e;
            }
            catch (HibernateException he){
               System.out.println(he.toString());
               throw he;
            }
         }
      }
      finally {
         try{
            session.close();
         }
         catch (HibernateException he){
            System.out.println(he.toString());
         }
      }

      return etudiants;
   }

   public Etudiant GET_ETUDIANT(int studentId) throws Exception {
      Session session = sessionFactory.openSession();
      Transaction tx = null;
      Etudiant etudiant = null;
      try {

         tx= session.beginTransaction();

         etudiant = session.get(Etudiant.class, studentId);

         tx.commit();
      }
      catch (Exception e) {
         if (tx!= null){
            try {
               tx.rollback();
               System.out.println(e.toString());
               throw e;
            }
            catch (HibernateException he){
               System.out.println(he.toString());
               throw he;
            }
         }
      }
      finally {
         try{
            session.close();
         }
         catch (HibernateException he){
            System.out.println(he.toString());
         }
      }
      return etudiant;
   }

   public void SAVE_ETUDIANT(Etudiant etudiant) throws Exception {
      Session session = sessionFactory.openSession();
      Transaction tx = null;
      try {

         tx= session.beginTransaction();

         session.save(etudiant);

         tx.commit();
      }
      catch (Exception e) {
         if (tx!= null){
            try {
               tx.rollback();
               System.out.println(e.toString());
               throw e;
            }
            catch (HibernateException he){
               System.out.println(he.toString());
               throw he;
            }
         }
      }
      finally {
         try{
            session.close();
         }
         catch (HibernateException he){
            System.out.println(he.toString());
         }
      }
   }

   public void UPDATE_ETUDIANT(Etudiant etudiant) throws Exception {
      Session session = sessionFactory.openSession();
      Transaction tx = null;
      try {

         tx= session.beginTransaction();

         session.update(etudiant);

         tx.commit();
      }
      catch (Exception e) {
         if (tx!= null){
            try {
               tx.rollback();
               System.out.println(e.toString());
               throw e;
            }
            catch (HibernateException he){
               System.out.println(he.toString());
               throw he;
            }
         }
      }
      finally {
         try{
            session.close();
         }
         catch (HibernateException he){
            System.out.println(he.toString());
         }
      }
   }

  public void DELETE_ETUDIANT(int studentId) throws Exception {
      Session session = sessionFactory.openSession();
      Transaction tx = null;
      try {

         tx= session.beginTransaction();

        session.delete(GET_ETUDIANT(studentId));

         tx.commit();
      }
      catch (Exception e) {
         if (tx!= null){
            try {
               tx.rollback();
               System.out.println(e.toString());
               throw e;
            }
            catch (HibernateException he){
               System.out.println(he.toString());
               throw he;
            }
         }
      }
      finally {
         try{
            session.close();
         }
         catch (HibernateException he){
            System.out.println(he.toString());
         }
      }
   }

   public static void terminate() {
        try {
            if ( sessionFactory != null ) {
               sessionFactory.close();
            }
        }
        catch(Exception e) {
            System.out.println (e.toString());
        }      
   }

}