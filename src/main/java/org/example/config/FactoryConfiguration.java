package org.example.config;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    public static FactoryConfiguration factoryConfiguration;
    public static SessionFactory session;

    private FactoryConfiguration(){
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass();
        session = configuration.buildSessionFactory();
    }
     public static FactoryConfiguration getInstance() {
        return factoryConfiguration == null ? new FactoryConfiguration() : factoryConfiguration;
    }
    public  Session getSession(){
        return session.openSession();
    }
}
