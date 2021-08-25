package ua.ivan.provider.servlets.servlet;

import ua.ivan.provider.dao.SitePackageDAO;
import ua.ivan.provider.dao.UserDAO;
import ua.ivan.provider.model.User;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainPageServlet extends HttpServlet {
    SitePackageDAO sitePackageDAO = new SitePackageDAO();
    UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("sitePackages", sitePackageDAO.sortByMethodPackages(req.getParameter("method"), sitePackageDAO.getAllPackages()));
        User user = (User)req.getSession().getAttribute("user");
        req.getSession().setAttribute("userBalance", userDAO.getUserBalance(user.getId()));
        req.setAttribute("subInternet", !userDAO.isSubscriber(user.getId(), "Internet"));
        req.setAttribute("subTelephone", !userDAO.isSubscriber(user.getId(), "Cellular communication"));
        req.setAttribute("subIPTV", !userDAO.isSubscriber(user.getId(), "IP-TV"));
        req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("sitePackages", sitePackageDAO.sortByMethodPackages(req.getParameter("method"), sitePackageDAO.getAllPackages()));
        User user = (User)req.getSession().getAttribute("user");
        req.getSession().setAttribute("userBalance", userDAO.getUserBalance(user.getId()));
        req.setAttribute("subInternet", !userDAO.isSubscriber(user.getId(), "Internet"));
        req.setAttribute("subTelephone", !userDAO.isSubscriber(user.getId(), "Cellular communication"));
        req.setAttribute("subIPTV", !userDAO.isSubscriber(user.getId(), "IP-TV"));
        req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
    }
}
