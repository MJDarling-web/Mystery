package persistence;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateLifecycle implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //SessionFactoryProvider.createSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        SessionFactoryProvider.shutdown();
        System.out.println("Hibernate SessionFactory shut down — DB connections cleared.");
    }
}
