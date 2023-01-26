package ua.com.javarush.quest.drogunov.quest.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import ua.com.javarush.quest.drogunov.quest.model.entity.*;

import java.util.Properties;

import static java.util.Objects.isNull;

public class DbSession {
    private static DbSession DB;
    private final SessionFactory sessionFactory;
    
    public DbSession() {
        Configuration configuration = new Configuration();
        Configuration configure = configuration.configure();
        LiquibaseChecker.updateDataBase(configuration);
        sessionFactory = configure
                .buildSessionFactory();
        DB = this;
    }
    
    public static SessionFactory getSessionFactory() {
        if (isNull(DB)) {
            new DbSession();
        }
        return DB.sessionFactory;
    }
}
