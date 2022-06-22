package com.visma.internship.warehouse.controllers;


import com.visma.internship.Item;
import com.visma.internship.warehouse.services.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
    public List<Item> printWarehouseItems(){
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
    public ResponseEntity<String> sellItem(@PathVariable("id") int id){
        boolean status = warehouseRepository.removeOneQntFromItemById(id);
        if(status){
            return ResponseEntity.ok("Item sold!");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
