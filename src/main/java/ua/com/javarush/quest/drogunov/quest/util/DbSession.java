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
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/quest");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "root");
        properties.put(Environment.HBM2DDL_AUTO, "validate");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        sessionFactory = new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(Answer.class)
                .addAnnotatedClass(Game.class)
                .addAnnotatedClass(Quest.class)
                .addAnnotatedClass(Question.class)
                .addAnnotatedClass(User.class)
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
