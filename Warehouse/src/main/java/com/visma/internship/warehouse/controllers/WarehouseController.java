package com.visma.internship.warehouse.controllers;


import com.visma.internship.Item;
import com.visma.internship.warehouse.services.WarehouseRepository;
import com.visma.internship.warehouse.services.WarehouseRepositoryInMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class WarehouseController {

    private final WarehouseRepository warehouseRepositoryInMemory;
    @Autowired
    public WarehouseController(WarehouseRepositoryInMemory warehouseRepositoryInMemory) {
        this.warehouseRepositoryInMemory = warehouseRepositoryInMemory;
    }

    @GetMapping("/items")
    public List<Item> printWarehouseItems(){
        return warehouseRepositoryInMemory.findItems();
    }

    @GetMapping("/item/{id}")
    public Optional<Item> printWarehouseItem(@PathVariable("id") int id){
        return warehouseRepositoryInMemory.findItem(id);
    }

    @PostMapping("/items")
    public void addItem(@RequestBody Item item){
        warehouseRepositoryInMemory.createItem(item);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<String> sellItem(@PathVariable("id") int id){
        boolean status = warehouseRepositoryInMemory.removeOneQntFromItemById(id);
        if(status){
            return ResponseEntity.ok("Item sold!");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
