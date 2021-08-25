package ua.ivan.provider.servlets.servlet;

import ua.ivan.provider.dao.SitePackageDAO;
import ua.ivan.provider.dao.UserDAO;
import ua.ivan.provider.model.Status;
import ua.ivan.provider.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAddPackageServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    SitePackageDAO sitePackageDAO = new SitePackageDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        User user = userDAO.buyUserPackage(
                Long.parseLong(request.getParameter("userId")),
                sitePackageDAO.getById(Long.parseLong(request.getParameter("packageId"))));
        request.getSession().setAttribute("user", user);
        if (user.getStatus().equals(Status.ACTIVE)) {
            response.sendRedirect("/user");
        } else {
            response.sendRedirect("/logout?error=2");
        }
    }
}
