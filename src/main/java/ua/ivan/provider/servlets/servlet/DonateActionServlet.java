package ua.ivan.provider.servlets.servlet;

import ua.ivan.provider.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DonateActionServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("sum") != null) {
            userDAO.acceptDonateQuery(Long.parseLong(request.getParameter("id_user")),
                    Integer.parseInt(request.getParameter("sum")),
                    Long.parseLong(request.getParameter("id_donate")));
        } else {
            userDAO.rejectDonateQuery(Long.parseLong(request.getParameter("id_donate")));
        }
        response.sendRedirect("/admin");
    }
}
