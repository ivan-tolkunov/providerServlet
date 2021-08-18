package ua.ivan.provider.servlets;

import ua.ivan.provider.controller.ConnectionDatabase;
import ua.ivan.provider.dao.UserDAO;
import ua.ivan.provider.model.Role;
import ua.ivan.provider.model.Status;
import ua.ivan.provider.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;



/**
 * ContextListener put user dao to servlet context.
 */
@WebListener
public class ContextListener implements ServletContextListener {

    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    /**
     * Fake database connector.
     */
    private AtomicReference<UserDAO> dao;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        dao = new AtomicReference<>(new UserDAO());

        try {
            String query = "SELECT * FROM user";
            con = ConnectionDatabase.initializeDatabase();
            st = con.prepareStatement(query);
            rs = st.executeQuery();

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
                dao.get().add(user);
            }

            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        servletContext.setAttribute("dao", dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dao = null;
    }
}
