package ua.ivan.provider.servlets.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DonatePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/donatePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/donatePage.jsp").forward(request, response);
    }
}
