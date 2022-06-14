package com.visma.internship.warehouse;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class WarehouseController {
    @GetMapping()
    public static String getTools(){
        return "Sveikisveiki!!";
    }

}
