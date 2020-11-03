package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        User user = new User("Greg", "Scott", (byte) 10);
        userDaoHibernate.saveUser("Robin", "Shulz", (byte) 76);
        userDaoHibernate.saveUser("Jason", "Sager", (byte) 43);
        Util.getSessionFactory().close();
    }
}