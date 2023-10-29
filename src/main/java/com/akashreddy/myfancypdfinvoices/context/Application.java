package com.akashreddy.myfancypdfinvoices.context;

import com.akashreddy.myfancypdfinvoices.service.InvoiceService;
import com.akashreddy.myfancypdfinvoices.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Application {

    public static final UserService userService = new UserService();
    public static final InvoiceService invoiceService = new InvoiceService(userService);
    public static final ObjectMapper objectMapper = new ObjectMapper();
}
