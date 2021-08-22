package ua.ivan.provider.servlets.servlet;

import ua.ivan.provider.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DonateServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userDAO.makeDonateQuery(
                Long.parseLong(request.getParameter("user_id")),
                Integer.parseInt(request.getParameter("sum"))
        );
        response.sendRedirect("/main");
    }
}
