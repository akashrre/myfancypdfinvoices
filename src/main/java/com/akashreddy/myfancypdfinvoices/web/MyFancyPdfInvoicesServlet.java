package com.akashreddy.myfancypdfinvoices.web;

import com.akashreddy.myfancypdfinvoices.context.MyFancyPdfInvoicesApplicationConfiguration;
import com.akashreddy.myfancypdfinvoices.model.Invoice;
import com.akashreddy.myfancypdfinvoices.service.InvoiceService;
import com.akashreddy.myfancypdfinvoices.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyFancyPdfInvoicesServlet extends HttpServlet {

    private UserService userService;
    private InvoiceService invoiceService;
    private ObjectMapper objectMapper;
    @Override
    public void init() throws ServletException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyFancyPdfInvoicesApplicationConfiguration.class);
        this.userService = ctx.getBean(UserService.class);
        this.invoiceService = ctx.getBean(InvoiceService.class);
        this.objectMapper = ctx.getBean(ObjectMapper.class);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws  IOException{

        if (httpServletRequest.getRequestURI().equalsIgnoreCase("/invoices")) {

            String userId = httpServletRequest.getParameter("user_id");
            Integer amount = Integer.valueOf(httpServletRequest.getParameter("amount"));

            Invoice invoice = invoiceService.create(userId, amount);

            httpServletResponse.setContentType("application/json; charset=UTF-8");
            String json = objectMapper.writeValueAsString(invoice);
            httpServletResponse.getWriter().print(json);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        if (httpServletRequest.getRequestURI().equalsIgnoreCase("/")){
            httpServletResponse.setContentType("text/html; charset=UTF-8");
            httpServletResponse.getWriter().print(
                    "<html>\n" +
                            "<body>\n" +
                            "<h1>Hello World</h1>\n" +
                            "<p>This is my very first, embedded Tomcat, HTML Page!</p>\n" +
                            "</body>\n" +
                            "</html>");
        }
        else if (httpServletRequest.getRequestURI().equalsIgnoreCase("/invoices")) {
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            List<Invoice> invoices = invoiceService.findAll();
            httpServletResponse.getWriter().print(objectMapper.writeValueAsString(invoices));
        }

    }
}
