package classes;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    private static final String INSERT_QUERY = "insert into users (email, username, password) values (?, ?, ?);";
    private static final String SELECT_QUERY = "select * from users where id = ?;";
    private static final String UPDATE_QUERY = "update users set email = ?, username = ?, password = ? where id = ?;";
    private static final String DELETE_QUERY = "delete from users where id = ?";
    private static final String SELECT_ALL_QUERY = "select * from users;";

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt()) ;
    }

    private User[] addToArray(User u, User[] users) {

        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);

        tmpUsers[users.length] = u;

        return tmpUsers;

    }

    public User[] findAll() {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            User[] users = new User[0];
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(int userId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            System.out.println("Deleted user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(User user) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3,this.hashPassword(user.getPassword()));
            statement.setInt(4, user.getId());
            statement.executeUpdate();
            System.out.println("Update successful");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User read(int userId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
            statement.setString(1, String.valueOf(userId));
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User create(User user) {

        try (Connection conn = DbUtil.getConnection()) {

            PreparedStatement statement =

                    conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getEmail());

            statement.setString(2, user.getUserName());

            statement.setString(3, hashPassword(user.getPassword()));

            statement.executeUpdate();


            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {

                user.setId(resultSet.getInt(1));

            }

            return user;

        } catch (SQLException e) {

            e.printStackTrace();

            return null;

        }

    }
}
