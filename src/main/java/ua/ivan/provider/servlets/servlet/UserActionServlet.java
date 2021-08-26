package ua.ivan.provider.servlets.servlet;

import ua.ivan.provider.dao.UserDAO;
import ua.ivan.provider.model.Status;
import ua.ivan.provider.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UserActionServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        request.getSession().setAttribute("userBalance", userDAO.getUserBalance(user.getId()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        request.getSession().setAttribute("userBalance", userDAO.getUserBalance(user.getId()));
        userDAO.updateUserStatus(Long.parseLong(request.getParameter("user_id")),
                Status.valueOf(request.getParameter("status")));
        response.sendRedirect("/admin");
    }
}
