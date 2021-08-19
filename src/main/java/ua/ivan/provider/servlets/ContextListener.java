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



@WebListener
public class ContextListener implements ServletContextListener {

    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    private AtomicReference<UserDAO> dao;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        dao = new AtomicReference<>(new UserDAO());


        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        servletContext.setAttribute("dao", dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dao = null;
    }
}
