package ru.krisnovitskaya;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.krisnovitskaya.persist.Product;
import ru.krisnovitskaya.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class);

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (checkPath(req)) {
            resp.getWriter().println("<table>");
            resp.getWriter().println("<tr>");
            resp.getWriter().println("<th>Id</th>");
            resp.getWriter().println("<th>Name</th>");
            resp.getWriter().println("<th>Description</th>");
            resp.getWriter().println("<th>Price</th>");
            resp.getWriter().println("</tr>");

            for (Product product : productRepository.findAll()) {
                resp.getWriter().println("<tr>");
                resp.getWriter().println("<td><a href='" + getServletContext().getContextPath() + "/product/" + product.getId() + "'>" + product.getId() + "</a></td>");
                resp.getWriter().println("<td>" + product.getName() + "</td>");
                resp.getWriter().println("<td>" + product.getDescription() + "</td>");
                resp.getWriter().println("<td>" + product.getPrice() + "</td>");
                resp.getWriter().println("</tr>");
            }
            resp.getWriter().println("</table>");
        } else if (checkParam(req)){
            Product product = productRepository.findById(getIdFromPath(req));

            if(product != null){
                String info = "<p>Product info</p>" +
                          "<p>ID: " + product.getId() + " </p>" +
                          "<p>Name: " + product.getName() + " </p>" +
                          "<p>Description: " + product.getDescription() + " </p>" +
                          "<p>Price: " + product.getPrice().toString() + " </p>";
                resp.getWriter().println(info);
            } else {
                resp.getWriter().println("<p>Product not exist</p>");
                resp.getWriter().println("<a href='" + getServletContext().getContextPath() + "/product" + "'>" + "вернуться к таблице" + "</a>");
            }
        } else {
            resp.getWriter().println("Not found");
        }

    }

    private boolean checkPath(HttpServletRequest req){
        String path = req.getPathInfo();
        return (path == null || path.equals("/"));
    }

    private boolean checkParam(HttpServletRequest req){
        try {
            Long.parseLong(req.getPathInfo().substring(1));
        }catch (NumberFormatException e){
            logger.error("wrong path");
            return false;
        }
        return true;
    }

    private long getIdFromPath(HttpServletRequest req){
        return Long.parseLong(req.getPathInfo().substring(1));
    }
}
