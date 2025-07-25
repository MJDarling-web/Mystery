package entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import persistence.SessionFactoryProvider;

public class Main {

    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
            Session session = sessionFactory.openSession();

            session.beginTransaction();

            System.out.println("Connected to Supabase PostgreSQL using Hibernate!");

            session.getTransaction().commit();
            session.close();
            sessionFactory.close();

        } catch (Exception e) {
            e.printStackTrace();  // very important to show errors!
        }
    }

    // ✅ If you had a static helper class like this, make sure it's static
    public static class DBTest {
        public static void runTest() {
            System.out.println("Running DB Test...");
        }
    }
}
