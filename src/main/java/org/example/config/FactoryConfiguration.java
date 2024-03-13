package org.example.config;



import org.example.entity.Book;
import org.example.entity.Branch;
import org.example.entity.Log;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private static SessionFactory session;

    private FactoryConfiguration(){
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("hibernate.properties"));
        }catch (IOException e) {
            e.printStackTrace();
        }
        Configuration configuration = new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(User.class).addAnnotatedClass(Branch.class).addAnnotatedClass(Book.class)
                .addAnnotatedClass(Log.class);
        session = configuration.buildSessionFactory();
    }
     public static FactoryConfiguration getInstance() {
        return factoryConfiguration == null ? new FactoryConfiguration() : factoryConfiguration;
    }
    public  Session getSession(){
        return session.openSession();
    }
}
