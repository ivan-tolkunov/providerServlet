package ua.ivan.provider.servlets.filter;

import ua.ivan.provider.controller.BCrypt;
import ua.ivan.provider.dao.UserDAO;
import ua.ivan.provider.model.Role;
import ua.ivan.provider.model.Status;
import ua.ivan.provider.model.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)

            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final String email = req.getParameter("email");
        final String password = req.getParameter("password");
        User user = (User) req.getSession().getAttribute("user");


        @SuppressWarnings("unchecked") final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>) req.getServletContext().getAttribute("dao");

        final HttpSession session = req.getSession();
        if (nonNull(session) &&
                nonNull(session.getAttribute("email")) &&
                nonNull(session.getAttribute("password"))) {

            moveToMenu(req, res, user);


        } else if (dao.get().userIsExist(email, password)) {

            User userFirst = dao.get().getUserByEmail(email);

            req.getSession().setAttribute("user", userFirst);
            req.getSession().setAttribute("password", userFirst.getPassword());
            req.getSession().setAttribute("email", userFirst.getEmail());
            req.getSession().setAttribute("role", userFirst.getRole());

            moveToMenu(req, res, userFirst);

        } else {
            User newUser = new User();
            newUser.setRole(Role.UNKNOWN);
            moveToMenu(req, res, newUser);
        }
    }

    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final User user)
            throws ServletException, IOException {


        if (user.getRole().equals(Role.ADMIN)) {
            req.getRequestDispatcher("/main").forward(req, res);

        } else if (user.getRole().equals(Role.USER)) {
            if (user.getStatus().equals(Status.ACTIVE)) {
                req.getRequestDispatcher("/main").forward(req, res);
            } else {
                req.setAttribute("userBalance", user.getBalance());
                req.getRequestDispatcher("/donatePage").forward(req, res);
            }
        } else {
            res.sendRedirect("/login?error=1");
        }
    }


    @Override
    public void destroy() {
    }
}
