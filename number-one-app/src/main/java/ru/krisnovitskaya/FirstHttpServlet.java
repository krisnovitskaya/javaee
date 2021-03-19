package ru.krisnovitskaya;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/http-servlet/*")
public class FirstHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("test-header", "test-value");

        resp.getWriter().println("<h1>Hello from HTTP Servlet</h1>");
        resp.getWriter().println("<h2>Привет от HTTP сервлета</h2>");

        resp.getWriter().println("<p>contextPath: " + req.getContextPath() + "</p>");
        resp.getWriter().println("<p>servletPath: " + req.getServletPath() + "</p>");
        resp.getWriter().println("<p>pathInfo: " + req.getPathInfo() + "</p>");
        resp.getWriter().println("<p>queryString: " + req.getQueryString() + "</p>");
        resp.getWriter().println("<p>param1: " + req.getParameter("param1") + "</p>");
        resp.getWriter().println("<p>param2: " + req.getParameter("param2") + "</p>");
        resp.getWriter().println("<p> Header - User-Agent: " + req.getHeader("User-Agent") + "</p>");
    }
}
/*
    ""                  /first-web-app
    "/http-servlet"     /first-web-app/http-servlet
    "/http-servlet/*"   /first-web-app/http-servlet
                        /first-web-app/http-servlet/sddadas
                        /first-web-app/http-servlet/sdfsdf/sdfsdf/sdfsdfsd/sdfsdfds
    "*.jpeg"            /first-web-app/dsfasdf/asdfasdf/asdfasdf.jpeg
    "/*"                 не рекомендуется использовать для сервлетов
    "/"                  запросы на все внешние префиксы сервера приложений
 */
