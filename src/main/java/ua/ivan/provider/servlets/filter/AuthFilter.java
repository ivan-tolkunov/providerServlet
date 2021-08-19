package ua.ivan.provider.servlets.filter;

import ua.ivan.provider.dao.UserDAO;
import ua.ivan.provider.model.Role;
import ua.ivan.provider.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {
    UserDAO userDAO = new UserDAO();
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
        User user = userDAO.getUserByEmail(email);


        @SuppressWarnings("unchecked")
        final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>) req.getServletContext().getAttribute("dao");

        final HttpSession session = req.getSession();
//        Logged user.
        if (nonNull(session) &&
                nonNull(session.getAttribute("email")) &&
                nonNull(session.getAttribute("password"))) {

            moveToMenu(req, res, user.getRole());


        } else if (dao.get().userIsExist(email, password)) {

            final Role role = dao.get().getRoleByLoginPassword(email, password);

            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("password", user.getPassword());
            req.getSession().setAttribute("email", user.getEmail());
            req.getSession().setAttribute("role", role);

            moveToMenu(req, res, role);

        } else {
            moveToMenu(req, res, Role.UNKNOWN);
        }
    }

    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final Role role)
            throws ServletException, IOException {


        if (role.equals(Role.ADMIN)) {
            req.getRequestDispatcher("/main").forward(req, res);

        } else if (role.equals(Role.USER)) {
            req.getRequestDispatcher("/main").forward(req, res);

        } else {
            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, res);
        }
    }


    @Override
    public void destroy() {
    }
}
