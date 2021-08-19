package ua.ivan.provider.dao;


import ua.ivan.provider.controller.ConnectionDatabase;
import ua.ivan.provider.model.Role;
import ua.ivan.provider.model.SitePackage;
import ua.ivan.provider.model.Status;
import ua.ivan.provider.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM user";
            Connection con = ConnectionDatabase.initializeDatabase();
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(Long.parseLong(rs.getString(1)));
                user.setEmail(rs.getString(2));
                user.setFirstName(rs.getString(3));
                user.setLastName(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setRole(Role.valueOf(rs.getString(6)));
                user.setStatus(Status.valueOf(rs.getString(7)));
                user.setBalance(Integer.parseInt(rs.getString(8)));
                users.add(user);
            }

            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getById(Long id) {
        User user = new User();
        try {
            String query = "SELECT * FROM user WHERE id = ?";
            Connection con = ConnectionDatabase.initializeDatabase();
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, String.valueOf(id));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                user.setId(Long.parseLong(rs.getString(1)));
                user.setEmail(rs.getString(2));
                user.setFirstName(rs.getString(3));
                user.setLastName(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setRole(Role.valueOf(rs.getString(6)));
                user.setStatus(Status.valueOf(rs.getString(7)));
                user.setBalance(Integer.parseInt(rs.getString(8)));
            }

            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User getUserByEmail(String email) {

        User user = new User();
        try {
            String query = "SELECT * FROM user WHERE email = ?";
            Connection con = ConnectionDatabase.initializeDatabase();
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                user.setId(Long.parseLong(rs.getString(1)));
                user.setEmail(rs.getString(2));
                user.setFirstName(rs.getString(3));
                user.setLastName(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setRole(Role.valueOf(rs.getString(6)));
                user.setStatus(Status.valueOf(rs.getString(7)));
                user.setBalance(Integer.parseInt(rs.getString(8)));
            }

            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean add(String email, String firstName, String lastName, String password) {
        try {

            Connection con = ConnectionDatabase.initializeDatabase();

            String query = "insert into user (email, first_name, last_name, password)"
                    + " values (?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(query);

            st.setString (1, email);
            st.setString (2, firstName);
            st.setString   (3, lastName);
            st.setString(4, password);

            st.executeUpdate();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public Role getRoleByLoginPassword(final String login, final String password) {
        Role result = Role.UNKNOWN;

        for (User user : getAllUsers()) {
            if (user.getEmail().equals(login) && user.getPassword().equals(password)) {
                result = user.getRole();
            }
        }

        return result;
    }

    public boolean userIsExist(final String login, final String password) {

        boolean result = false;

        for (User user : getAllUsers()) {
            if (user.getEmail().equals(login) && user.getPassword().equals(password)) {
                result = true;
                break;
            }
        }

        return result;
    }

    public List<SitePackage> getUserPackages(Long userId) {
        List<SitePackage> sitePackages = new ArrayList<>();
        try {
            Connection con = ConnectionDatabase.initializeDatabase();

            String query = "select * from packages natural join subscribe where subscribe.user_id = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, String.valueOf(userId));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SitePackage sitePackage = new SitePackage();
                sitePackage.setId(Long.parseLong(rs.getString(1)));
                sitePackage.setName(rs.getString(2));
                sitePackage.setDescription(rs.getString(3));
                sitePackage.setPrice(Integer.parseInt(rs.getString(4)));
                sitePackage.setType(rs.getString(5));
                sitePackages.add(sitePackage);
            }
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sitePackages;
    }

    public boolean buyUserPackage(Long userId, SitePackage sitePackage) {
        try {
            Connection con = ConnectionDatabase.initializeDatabase();
            User user = getById(userId);
            user.setBalance(getById(userId).getBalance() - sitePackage.getPrice());
            if (user.getBalance() >= 0) {
                String query = "insert into subscribe (user_id, package_id)"
                        + " values (?, ?)";
                PreparedStatement st = con.prepareStatement(query);

                st.setString (1, String.valueOf(userId));
                st.setString (2, String.valueOf(sitePackage.getId()));
                st.executeUpdate();
                st.close();

                query = "update user set balance = ? where id = ?";
                st = con.prepareStatement(query);

                st.setString (1, String.valueOf(user.getBalance()));
                st.setString (2, String.valueOf(userId));
                st.executeUpdate();
                st.close();
                return true;
            }
            con.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


}
