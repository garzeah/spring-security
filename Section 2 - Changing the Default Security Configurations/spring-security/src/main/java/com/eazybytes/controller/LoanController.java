package com.eazybytes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class LoanController {

    @GetMapping("myLoan")
    public String getLoanDetails() {
        return "Loan Endpoint";
    }
}
