package com.akashreddy.myfancypdfinvoices.web;

import com.akashreddy.myfancypdfinvoices.context.Application;
import com.akashreddy.myfancypdfinvoices.model.Invoice;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyFancyPdfInvoicesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws  IOException{

        if (httpServletRequest.getRequestURI().equalsIgnoreCase("/invoices")) {

            String userId = httpServletRequest.getParameter("user_id");
            Integer amount = Integer.valueOf(httpServletRequest.getParameter("amount"));

            Invoice invoice = Application.invoiceService.create(userId, amount);

            httpServletResponse.setContentType("application/json; charset=UTF-8");
            String json = Application.objectMapper.writeValueAsString(invoice);
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
            List<Invoice> invoices = Application.invoiceService.findAll();
            httpServletResponse.getWriter().print(Application.objectMapper.writeValueAsString(invoices));
        }

    }
}
