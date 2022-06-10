package com.visma.internship.shop;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World!";
    }
}
