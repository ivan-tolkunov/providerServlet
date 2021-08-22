package ua.ivan.provider.dao;

import ua.ivan.provider.controller.ConnectionDatabase;
import ua.ivan.provider.model.SitePackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SitePackageDAO {


    public List<SitePackage> getAllPackages() {
        List<SitePackage> sitePackages = new ArrayList<>();
        try {
            String query = "SELECT * FROM packages";
            Connection con = ConnectionDatabase.initializeDatabase();
            PreparedStatement st = con.prepareStatement(query);
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

            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return sitePackages;
    }

    public SitePackage getById(Long id) {
        SitePackage sitePackage = new SitePackage();
        try {
            String query = "SELECT * FROM packages WHERE id = ?";
            Connection con = ConnectionDatabase.initializeDatabase();
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, String.valueOf(id));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                sitePackage.setId(Long.parseLong(rs.getString(1)));
                sitePackage.setName(rs.getString(2));
                sitePackage.setDescription(rs.getString(3));
                sitePackage.setPrice(Integer.parseInt(rs.getString(4)));
                sitePackage.setType(rs.getString(5));
            }

            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return sitePackage;
    }

    public boolean addNewPackage(String name, String description, String price, String type) {
        try {
            String query = "insert into packages (name, description, price, type)"
                    + " values (?, ?, ?, ?)";
            Connection con = ConnectionDatabase.initializeDatabase();
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, name);
            st.setString(2, description);
            st.setString(3, price);
            st.setString(4, type);
            st.executeUpdate();

            st.close();
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePackageById(String id) {
        try {
            String query = "delete from subscribe where package_id = ?";
            Connection con = ConnectionDatabase.initializeDatabase();
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, id);
            st.execute();
            query = "delete from packages where id = ?";
            st = con.prepareStatement(query);
            st.setString(1, id);
            st.execute();

            st.close();
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editPackage(String id, String name, String description, String price, String type) {
        try {
            String query = "update packages set name = ?, description = ?, price = ?, type = ? where id = ?";
            Connection con = ConnectionDatabase.initializeDatabase();
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, name);
            st.setString(2, description);
            st.setString(3, price);
            st.setString(4, type);
            st.setString(5, id);
            st.executeUpdate();

            st.close();
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<SitePackage> sortByMethodPackages(String method, List<SitePackage> packages) {
        switch (String.valueOf(method)) {
            case "A-Z":
                return packages
                        .stream()
                        .sorted(Comparator.comparing(SitePackage::getName))
                        .collect(Collectors.toList());
            case "Z-A":
                return packages
                        .stream()
                        .sorted(Comparator.comparing(SitePackage::getName).reversed())
                        .collect(Collectors.toList());
            case "price":
                return packages
                        .stream()
                        .sorted(Comparator.comparing(SitePackage::getPrice))
                        .collect(Collectors.toList());

            default:
                return packages;
        }
    }

}
