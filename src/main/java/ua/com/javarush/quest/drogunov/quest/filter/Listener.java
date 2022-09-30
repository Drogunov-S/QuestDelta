package ua.com.javarush.quest.drogunov.quest.filter;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ua.com.javarush.quest.drogunov.quest.util.RepositoryLoader;

@WebListener
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        RepositoryLoader.load();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        RepositoryLoader.save();
    }
}
