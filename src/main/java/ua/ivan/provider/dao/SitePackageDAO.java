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
}
