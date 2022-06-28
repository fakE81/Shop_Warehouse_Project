package com.visma.internship.shop.controllers;

import com.visma.internship.ItemDTO;
import com.visma.internship.shop.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/shop")
@Controller
public class ShopController {

    @Autowired
    RestTemplate restTemplate;

    final WarehouseService warehouseService;

    public ShopController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @RequestMapping("/items")
    public String listItems(Model model){
        model.addAttribute("items",warehouseService.getItems());
        return "home";
    }

    @RequestMapping("/item/{id}")
    public ItemDTO getItem(@PathVariable("id") int id){
        return warehouseService.getItem(id);
    }

    @RequestMapping("/item/sell/{id}")
    public ResponseEntity<String> sellItem(@PathVariable("id") int id){
        return warehouseService.sellItem(id);
    }
}
