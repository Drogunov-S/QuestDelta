package ua.com.javarush.quest.drogunov.quest.util;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.Scope;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class LiquibaseChecker {
    @SneakyThrows
    public static void updateDataBase(Configuration configuration) {
        String url = configuration.getProperty("hibernate.connection.url");
        String userLogin = configuration.getProperty("hibernate.connection.username");
        String password = configuration.getProperty("hibernate.connection.password");
        Connection connection = DriverManager.getConnection(url, userLogin, password);
        Map<String, Object> config = new HashMap<>();
        try (connection) {
            Scope.child(config, () -> {
                Database dataBase = DatabaseFactory
                        .getInstance()
                        .findCorrectDatabaseImplementation(new JdbcConnection(connection));
                Liquibase liquibase = new Liquibase(
                        "classpath:/liquibase/changelog.xml",
                        new ClassLoaderResourceAccessor(),
                        dataBase
                );
                liquibase.update(new Contexts(), new LabelExpression());
            });
        }
    }
}
