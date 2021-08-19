package ua.ivan.provider.servlets.servlet;

import ua.ivan.provider.dao.SitePackageDAO;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainPageServlet extends HttpServlet {
    private final SitePackageDAO sitePackageDAO = new SitePackageDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("sitePackages", sitePackageDAO.getAllPackages());
        req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
    }
}
