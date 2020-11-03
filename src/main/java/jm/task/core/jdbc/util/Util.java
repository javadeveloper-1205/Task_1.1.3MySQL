package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.dialect.SybaseASE15Dialect;
import org.hibernate.metamodel.Metadata;
import org.hibernate.metamodel.MetadataSources;
import org.omg.CORBA.Environment;


import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Util {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            try {
                //Create   registry
//                registry = new StandardServiceRegistryBuilder().configure().build();
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydb1?serverTimezone=UTC&useSSL=false");
                properties.setProperty("hibernate.connection.username", "root");
                properties.setProperty("hibernate.connection.password", "vagon2015");
                properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);
                //Create MetadataSources
//                MetadataSources sources = new MetadataSources(registry);
                //Create metadata
//                Metadata metadata = sources.getMetadataBuilder().build();
                //Create sessionFactory
                sessionFactory = configuration.buildSessionFactory();
//                        metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
            }
//            if (registry != null) {
//                StandardServiceRegistryBuilder.destroy(registry);
//            }
        }
        return sessionFactory;
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1?user=root&password=vagon2015&serverTimezone=UTC&useSSL=false");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

