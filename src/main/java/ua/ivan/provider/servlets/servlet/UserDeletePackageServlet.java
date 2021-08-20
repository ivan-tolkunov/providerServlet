package ua.ivan.provider.servlets.servlet;

import ua.ivan.provider.dao.UserDAO;
import ua.ivan.provider.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UserDeletePackageServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userDAO.deleteUserPackage(Long.parseLong(req.getParameter("userId")),
                Long.parseLong(req.getParameter("packageId")));
        req.getRequestDispatcher("/user").forward(req, resp);
    }
}
