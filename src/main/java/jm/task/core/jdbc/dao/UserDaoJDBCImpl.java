package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String createTable = "CREATE TABLE if not exists mydb1 (" +
                "                          id INT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
                "                          name VARCHAR(40) NOT NULL," +
                "                          lastName VARCHAR(40) NOT NULL," +
                "                          age INT NOT NULL" +
                ");";
        Connection connection = null;
        try {
            Util util = new Util();
            connection = util.getConnection();
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            statement.executeUpdate(createTable);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                Util.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void dropUsersTable() {
        Connection connection = null;
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS mydb1");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = null;
        String sqlSaveUser = ("INSERT INTO mydb1 (name, lastName, age)" +
                "VALUES (?, ? ,?)");
        try {
            connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSaveUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            connection.createStatement().executeUpdate("INSERT INTO mydb1 (name, lastName, age)" +
                    "VALUES ('" + name + "', " + "'" + lastName + "', " + age + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void removeUserById(long id) {
        Connection connection = null;
        try {
            connection = Util.getConnection();
            connection.createStatement().executeUpdate("DELETE FROM mydb1 WHERE id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Statement statement = null;
        Connection connection = null;
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            String sql = " SELECT * FROM mydb1";
            ResultSet resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                User user = new User();
                user.setId(resultset.getLong("id"));
                user.setName(resultset.getString("name"));
                user.setLastName(resultset.getString("lastName"));
                user.setAge(resultset.getByte("age"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public void cleanUsersTable() {
        Connection connection = null;
        try {
            connection = Util.getConnection();
            connection.createStatement().executeUpdate("DELETE FROM mydb1");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
//