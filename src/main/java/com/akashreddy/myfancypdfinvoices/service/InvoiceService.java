package com.akashreddy.myfancypdfinvoices.service;

import com.akashreddy.myfancypdfinvoices.model.Invoice;
import com.akashreddy.myfancypdfinvoices.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {

    private List<Invoice> invoices = new CopyOnWriteArrayList<>();

    private final UserService userService;
    private final String cdnUrl;

    public InvoiceService(UserService userService, @Value("${cdn.url}") String cdnUrl) {
        this.userService = userService;
        this.cdnUrl = cdnUrl;
    }
    public List<Invoice> findAll() {
        return invoices;
    }
    public Invoice create(String userId, Integer amount) {

        User user = userService.findById(userId);
        if (user == null) {
            throw new IllegalStateException();
        }

        // TODO real PDF creation and storing it on network server
        Invoice invoice = new Invoice(userId, amount, cdnUrl + "/images/default/sample.pdf");
        invoices.add(invoice);
        return invoice;
    }

    @PostConstruct
    public void init() {
        System.out.println("Fetching PDF template from S3...");
        // TODO download from s3 and save locally
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("Deleting downloaded templates...");
        // TODO actual deletion of PDFs
    }
}
