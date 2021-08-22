package ua.ivan.provider.servlets.servlet;

import ua.ivan.provider.dao.UserDAO;
import ua.ivan.provider.model.Status;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UserActionServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("status").equals(Status.BANNED.name())) {
            request.getSession().setAttribute("user", userDAO.updateUserStatus(Long.parseLong(request.getParameter("user_id")),
                    Status.valueOf(request.getParameter("status"))));
        } else {
            request.getSession().setAttribute("user", userDAO.updateUserStatus(Long.parseLong(request.getParameter("user_id")),
                    Status.valueOf(request.getParameter("status"))));
        }
        response.sendRedirect("/admin");
    }
}
