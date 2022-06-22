package com.visma.internship.shop.controllers;

import com.visma.internship.Item;
import com.visma.internship.shop.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/shop")
@RestController
public class ShopController {

    @Autowired
    RestTemplate restTemplate;

    final WarehouseService warehouseService;

    @Autowired
    public ShopController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @RequestMapping("/items")
    public List<Item> getItems(){
        return warehouseService.getItems();
    }

    @RequestMapping("/item/{id}")
    public Item getItem(@PathVariable("id") int id){
        return warehouseService.getItem(id);
    }

    @RequestMapping("/item/sell/{id}")
    public ResponseEntity<String> sellItem(@PathVariable("id") int id){
        return warehouseService.sellItem(id);
    }
}
