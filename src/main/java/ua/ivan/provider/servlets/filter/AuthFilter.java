package ua.ivan.provider.servlets.filter;

import ua.ivan.provider.controller.ConnectionDatabase;
import ua.ivan.provider.dao.UserDAO;
import ua.ivan.provider.model.Role;
import ua.ivan.provider.model.Status;
import ua.ivan.provider.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
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
        User user = new User();
        try {
            String query = "SELECT * FROM user WHERE email = ?";
            con = ConnectionDatabase.initializeDatabase();
            st = con.prepareStatement(query);
            st.setString(1, request.getParameter("email"));
            rs = st.executeQuery();

            while (rs.next()) {
                user.setId(Long.parseLong(rs.getString(1)));
                user.setEmail(rs.getString(2));
                user.setFirstName(rs.getString(3));
                user.setLastName(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setRole(Role.valueOf(rs.getString(6)));
                user.setStatus(Status.valueOf(rs.getString(7)));
                user.setBalance(Integer.parseInt(rs.getString(8)));
            }

            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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

            req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, res);

        } else if (role.equals(Role.USER)) {

            req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, res);

        } else {
            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, res);
        }
    }


    @Override
    public void destroy() {
    }
}
