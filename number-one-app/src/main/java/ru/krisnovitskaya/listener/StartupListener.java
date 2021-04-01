package ru.krisnovitskaya.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.krisnovitskaya.persist.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;
import java.math.BigInteger;

@WebListener
public class StartupListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(StartupListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Startup Listener");

        ProductRepository productRepository = new ProductRepository();
        productRepository.save(new Product(null, "Product 1", "Description 1", new BigDecimal(100)));
        productRepository.save(new Product(null, "Product 2", "Description 2", new BigDecimal(200)));
        productRepository.save(new Product(null, "Product 3", "Description 3", new BigDecimal(300)));

        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.save(new Customer(null, "Customer name 1", new BigInteger("1234567891"), "Address 1"));
        customerRepository.save(new Customer(null, "Customer name 2", new BigInteger("1234567892"), "Address 2"));
        customerRepository.save(new Customer(null, "Customer name 3", new BigInteger("1234567893"), "Address 3"));

        CategoryRepository categoryRepository = new CategoryRepository();
        categoryRepository.save(new Category(null, "Category title 1"));
        categoryRepository.save(new Category(null, "Category title 2"));
        categoryRepository.save(new Category(null, "Category title 3"));

        sce.getServletContext().setAttribute("productRepository", productRepository);
        sce.getServletContext().setAttribute("customerRepository", customerRepository);
        sce.getServletContext().setAttribute("categoryRepository", categoryRepository);
    }
}
