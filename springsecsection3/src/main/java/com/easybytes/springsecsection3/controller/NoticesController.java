package com.easybytes.springsecsection3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {

    @GetMapping("/notices")
    public String getNotices() {
        return "Here Notices from the DB";
    }
}
