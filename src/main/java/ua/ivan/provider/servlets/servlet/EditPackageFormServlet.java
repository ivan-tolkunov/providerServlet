package ua.ivan.provider.servlets.servlet;

import ua.ivan.provider.dao.SitePackageDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditPackageFormServlet extends HttpServlet {
    SitePackageDAO packageDAO = new SitePackageDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("editPackage", packageDAO.getById(Long.parseLong(req.getParameter("packageId"))));
        req.getRequestDispatcher("/WEB-INF/view/editPackageForm.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        packageDAO.editPackage(req.getParameter("id"),
                req.getParameter("name"),
                req.getParameter("description"),
                req.getParameter("price"),
                req.getParameter("type"));
        resp.sendRedirect("/admin/editPackagePage");

    }

}
