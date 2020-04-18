package pl.coderslab.model;

//import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDao {

    private static final String GET_USER_BY_ID = "SELECT * FROM USERS WHERE ID = ?;";
    private static final String CREATE_USER = "INSERT INTO USERS (EMAIL, USERNAME, PASSWORD, USERS_GROUPS_ID) VALUES (?, ?, ?, ?);";
    private static final String DELETE_USER = "DELETE FROM USERS WHERE ID = ?;";
    private static final String UPDATE_USER = "UPDATE USERS SET EMAIL = ?, USERNAME = ?, PASSWORD = ?, USERS_GROUPS_ID = ? WHERE ID = ?;";
    private static final String GET_ALL_USERS = "SELECT * FROM USERS ORDER BY id DESC LIMIT 5;";


    public User getUserById(int id) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(GET_USER_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUserGroupId(resultSet.getInt("users_groups_id"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User createUser(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =  conn.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getUserGroupId());
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

    public void deleteUser(int id) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER );
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public   User updateUser(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =  conn.prepareStatement(UPDATE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getUserGroupId());
            statement.setInt(5, user.getId());
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

//  public  User[] getAllUsers{
//      try (Connection conn = DbUtil.getConnection()) {
//          User[] users = new User[0];
//          PreparedStatement statement = conn.prepareStatement(GET_ALL_USERS);
//          ResultSet resultSet = statement.executeQuery();
//          while (resultSet.next()) {
//              User user = new User();
//              user.setId(resultSet.getInt("id"));
//              user.setUsername(resultSet.getString("username"));
//              user.setEmail(resultSet.getString("email"));
//              user.setPassword(resultSet.getString("password"));
//              users = addToArray(user, users);
//          }
//          return users;
//      } catch (SQLException e) {
//          e.printStackTrace();
//          return null;
//      }
//  }

    public List<User> findAll() {
        try (Connection conn = DbUtil.getConnection()) {
            List<User> users = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(GET_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }

    }

