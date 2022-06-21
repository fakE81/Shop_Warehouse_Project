package com.visma.internship.warehouse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class WarehouseController {

    private final WarehouseRepository warehouseRepository;
    @Autowired
    public WarehouseController(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @GetMapping("/items")
    public ArrayList<Item> printWarehouseItems(){
        return warehouseRepository.getItems();
    }

    @GetMapping("/item/{id}")
    public Optional<Item> printWarehouseItem(@PathVariable("id") int id){
        return warehouseRepository.getItemById(id);
    }

    @PostMapping("/items")
    public void addItem(@RequestBody Item item){
        warehouseRepository.addItem(item);
    }

    @PutMapping("/items/{id}")
    public String sellItem(@PathVariable("id") int id, HttpServletResponse response){
        boolean status = warehouseRepository.removeItemById(id);
        if(status){
            response.setStatus(201);
            return "Sold item!";
        }
        else{
            response.setStatus(400);
            return "No item found!";
        }
    }

}
