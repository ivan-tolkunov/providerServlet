package ua.ivan.provider.servlets.servlet;

import ua.ivan.provider.dao.SitePackageDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewPackageServlet extends HttpServlet {
    SitePackageDAO sitePackageDAO = new SitePackageDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/addPackageForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sitePackageDAO.addNewPackage(req.getParameter("name"),
                req.getParameter("description"),
                req.getParameter("price"),
                req.getParameter("type"));
        resp.sendRedirect("/admin");
    }
}
