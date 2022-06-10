package com.visma.internship.shop;

import com.visma.internship.warehouse.Warehouse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
        System.out.println(Warehouse.getTools());
        System.out.println();
    }
}