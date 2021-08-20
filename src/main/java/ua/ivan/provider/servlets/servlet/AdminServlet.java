package ua.ivan.provider.servlets.servlet;

import ua.ivan.provider.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listOfUsers", userDAO.getAllUsers());
        req.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listOfUsers", userDAO.getAllUsers());
        req.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(req, resp);
    }
}
