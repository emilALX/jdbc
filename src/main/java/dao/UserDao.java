package dao;

import config.Database;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    // save
    // update
    // delete
    // findById
    // findAll
    // pierwsze 3 metody wykonuja, 2 ostatnie pobieraja dane

    private Database database = new Database();

    public User save(User user) throws SQLException {
        // insert
        // 1.pobierz polaczenie
        Connection connection = database.getConnection();

        // 2.napisz zapytanie
        /*String sql = "INSERT INTO user (first_name, last_name, email)" +
                "VALUES (" + user.getFirstName() + "," +
                user.getLastName() + ", " +
                user.getEmail() + ")";

        //firstName = "; DROP DATABASE;"
        //potencjalnie SQL Injection
        */
        String sql = "INSERT INTO user (first_name, last_name, email)" +
                "VALUES (?,?,?)";

        //3.utworz obiekt PreparedStatement
        PreparedStatement statement = connection.prepareStatement(sql);

        //4.uzupelnij parametry zapytania
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail());

        //5.wykonaj zapytanie w bazie
        statement.executeUpdate();

        return user;


    }

    public void update(User user) throws SQLException {

        Connection connection = database.getConnection();

        String sql = "UPDATE user (first_name, last_name, email)" +
                "VALUES (?,?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail());

        statement.executeUpdate();
    }


    public void delete(int id) throws SQLException {

        Connection connection = database.getConnection();

        String sql = "DELETE user (id)" +
                "VALUES (?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, id);

        statement.executeUpdate();

    }

    public User findById(int id) throws SQLException {
        Connection connection = database.getConnection();

        String sql = "SELECT id, first_name, last_name, email " +
                "FROM user WHERE id=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        User user = null;

        while (result.next()) {
            id = result.getInt("id");
            String firstName = result.getNString("first_name");
            String lastName = result.getNString("last_name");
            String email = result.getNString("email");
            user = new User(id, firstName, lastName, email);
        }

        return null;
    }

    public List<User> findAll() throws SQLException {
        Connection connection = database.getConnection();

        String sql = "SELECT id, first_name, last_name, email " +
                "FROM user";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        ArrayList user = null;

       while (result.next()) {
            int id = result.getInt("id");
            String firstName = result.getNString("first_name");
            String lastName = result.getNString("last_name");
            String email = result.getNString("email");
            user = new ArrayList(id, firstName, lastName, email);
        }

        return null;
    }

}

