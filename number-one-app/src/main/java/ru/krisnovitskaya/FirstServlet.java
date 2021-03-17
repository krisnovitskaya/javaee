package ru.krisnovitskaya;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;


public class FirstServlet implements Servlet {

    private ServletConfig config;

    private static final Logger logger = LoggerFactory.getLogger(FirstServlet.class);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        logger.info("Servlet init");
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("Catch request");
        servletResponse.getWriter().println("<h1>Hello! I am First Test Servlet</h1>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
