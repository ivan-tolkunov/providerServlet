package ua.ivan.provider.servlets.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Logout.
 * Delete session.
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        final HttpSession session = req.getSession();

        session.removeAttribute("password");
        session.removeAttribute("login");
        session.removeAttribute("role");
        session.removeAttribute("user");
        session.removeAttribute("userFirst");
        session.removeAttribute("userBalance");
        if ("2".equals(req.getParameter("error"))) {
            resp.sendRedirect("/login?error=2");
        } else {
            resp.sendRedirect("/login");
        }
    }

}
