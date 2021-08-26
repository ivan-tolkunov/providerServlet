package ua.ivan.provider.servlets.servlet;

import ua.ivan.provider.dao.SitePackageDAO;
import ua.ivan.provider.dao.UserDAO;
import ua.ivan.provider.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class UserCabinetServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    SitePackageDAO sitePackageDAO = new SitePackageDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (nonNull(user)) {
            req.getSession().setAttribute("userBalance", userDAO.getUserBalance(user.getId()));
            req.getSession().setAttribute("userSubs", sitePackageDAO.sortByMethodPackages(req.getParameter("method"),
                    userDAO.getUserPackages(user.getId())));
            req.getRequestDispatcher("/WEB-INF/view/cabinet.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (nonNull(user)) {
            req.getSession().setAttribute("userBalance", userDAO.getUserBalance(user.getId()));
            req.getSession().setAttribute("userSubs", sitePackageDAO.sortByMethodPackages(req.getParameter("method"),
                    userDAO.getUserPackages(user.getId())));
            req.getRequestDispatcher("/WEB-INF/view/cabinet.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }
}
