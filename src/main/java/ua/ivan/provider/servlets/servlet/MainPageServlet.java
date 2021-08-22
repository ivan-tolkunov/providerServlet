package ua.ivan.provider.servlets.servlet;

import ua.ivan.provider.dao.SitePackageDAO;
import ua.ivan.provider.model.SitePackage;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainPageServlet extends HttpServlet {
    private final SitePackageDAO sitePackageDAO = new SitePackageDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("sitePackages", sitePackageDAO.sortByMethodPackages(req.getParameter("method"), sitePackageDAO.getAllPackages()));
        req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("sitePackages", sitePackageDAO.sortByMethodPackages(req.getParameter("method"), sitePackageDAO.getAllPackages()));
        req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
    }
}
