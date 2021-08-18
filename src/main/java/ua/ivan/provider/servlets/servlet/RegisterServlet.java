package ua.ivan.provider.servlets.servlet;

import ua.ivan.provider.controller.ConnectionDatabase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = null;
        PreparedStatement st = null;

        try {

            con = ConnectionDatabase.initializeDatabase();

            String query = " insert into user (email, first_name, last_name, password)"
                    + " values (?, ?, ?, ?)";
            st = con.prepareStatement(query);

            st.setString (1, request.getParameter("email"));
            st.setString (2, request.getParameter("firstName"));
            st.setString   (3, request.getParameter("lastName"));
            st.setString(4, request.getParameter("password"));

            st.executeUpdate();
            st.close();
            con.close();

            response.sendRedirect("/login");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
