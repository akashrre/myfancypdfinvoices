package com.akashreddy.myfancypdfinvoices.web;

import com.akashreddy.myfancypdfinvoices.model.Invoice;
import com.akashreddy.myfancypdfinvoices.service.InvoiceService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class InvoicesController {

    private final InvoiceService invoiceService;

    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    //@RequestMapping(value = "/invoices", method = RequestMethod.GET)
    public List<Invoice> invoices() {
        return invoiceService.findAll();
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestParam("user_id") @NotBlank String userId, @RequestParam("amount") @Min(10) @Max(50) Integer amount) {
        return invoiceService.create(userId, amount);
    }

//    @PostMapping("/invoices/{userId}/{amount}")
//    public Invoice createInvoice2(@PathVariable String userId, @PathVariable Integer amount) {
//        return invoiceService.create(userId, amount);
//    }

//    @PostMapping("/invoices")
//    public Invoice createInvoice3(@RequestBody @Valid InvoiceDto invoiceDto) {
//        return invoiceService.create(invoiceDto.getUserId(), invoiceDto.getAmount());
//    }
}
