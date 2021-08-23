package ua.ivan.provider.servlets.filter;

import ua.ivan.provider.dao.UserDAO;
import ua.ivan.provider.model.Role;
import ua.ivan.provider.model.Status;
import ua.ivan.provider.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        User user = (User)req.getSession().getAttribute("user");


        @SuppressWarnings("unchecked")
        final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>) req.getServletContext().getAttribute("dao");

        final HttpSession session = req.getSession();
//        Logged user.
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
            moveToMenu(req, res, new User());
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
                req.getRequestDispatcher("/donatePage").forward(req, res);
            }

        } else {
            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, res);
        }
    }


    @Override
    public void destroy() {
    }
}
