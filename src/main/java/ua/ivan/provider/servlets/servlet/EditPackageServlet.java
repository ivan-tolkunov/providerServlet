package ua.ivan.provider.servlets.servlet;

import ua.ivan.provider.dao.SitePackageDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditPackageServlet extends HttpServlet {
    SitePackageDAO packageDAO = new SitePackageDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("sitePackages", packageDAO.getAllPackages());
        req.getRequestDispatcher("/WEB-INF/view/edit.jsp").forward(req, resp);
    }
}
