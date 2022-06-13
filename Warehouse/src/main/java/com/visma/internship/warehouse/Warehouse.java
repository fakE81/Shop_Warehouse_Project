package com.visma.internship.warehouse;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Warehouse {
    @GetMapping("/")
    public static String getTools(){
        return "Hello World!!!";
    }

}
