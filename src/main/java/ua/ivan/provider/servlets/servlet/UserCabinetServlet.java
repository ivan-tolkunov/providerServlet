package ua.ivan.provider.servlets.servlet;

import ua.ivan.provider.dao.UserDAO;
import ua.ivan.provider.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserCabinetServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        req.getSession().setAttribute("userSubs", userDAO.getUserPackages(user.getId()));
        req.getRequestDispatcher("/WEB-INF/view/cabinet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        req.getSession().setAttribute("userSubs", userDAO.getUserPackages(user.getId()));
        req.getRequestDispatcher("/WEB-INF/view/cabinet.jsp").forward(req, resp);
    }
}
