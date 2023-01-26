package ua.com.javarush.quest.drogunov.quest;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.com.javarush.quest.drogunov.quest.util.LiquibaseChecker;

public class lqstart {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        try (SessionFactory sessionFactory = configuration.configure().buildSessionFactory();
        ) {
            LiquibaseChecker.updateDataBase(configuration);
        }
        System.out.println("////////////////////////////////////////////////////////////////");
        System.out.println("finish");
        System.out.println("////////////////////////////////////////////////////////////////");
    }
}
