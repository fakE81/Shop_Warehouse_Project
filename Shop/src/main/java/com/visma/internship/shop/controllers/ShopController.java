package com.visma.internship.shop.controllers;

import com.visma.internship.ItemDTO;
import com.visma.internship.shop.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping("/home")
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

    @RequestMapping(value = "/activity/download", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> downloadLastHourActivity(){
        return warehouseService.downloadActivity();
    }
}
