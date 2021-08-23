package ua.ivan.provider.servlets.servlet;

import ua.ivan.provider.dao.UserDAO;
import ua.ivan.provider.model.Status;

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
        if (userDAO.getById(Long.parseLong(request.getParameter("user_id"))).getStatus().equals(Status.ACTIVE)) {
            response.sendRedirect("/main");
        } else {
            response.sendRedirect("/");
        }
    }
}
