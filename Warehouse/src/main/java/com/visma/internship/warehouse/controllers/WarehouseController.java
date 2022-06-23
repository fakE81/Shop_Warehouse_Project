package com.visma.internship.warehouse.controllers;


import com.visma.internship.ItemDTO;
import com.visma.internship.warehouse.entities.Item;
import com.visma.internship.warehouse.services.WarehouseDatabaseRepository;
import com.visma.internship.warehouse.services.WarehouseRepository;
import com.visma.internship.warehouse.services.WarehouseRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class WarehouseController {

    private final WarehouseRepositoryService warehouseRepositoryService;

    public WarehouseController(WarehouseRepositoryService warehouseRepositoryService) {
        this.warehouseRepositoryService = warehouseRepositoryService;
    }

    @GetMapping("/items")
    public List<ItemDTO> printWarehouseItems(){
        return warehouseRepositoryService.findAllItems();
    }

    @GetMapping("/item/{id}")
    public Optional<ItemDTO> printWarehouseItem(@PathVariable("id") Long id){
        return warehouseRepositoryService.findItemById(id);
    }

    @PostMapping("/items")
    public void addItem(@RequestBody Item item){
        warehouseRepositoryService.addItem(item);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<String> sellItem(@PathVariable("id") int id){
        return warehouseRepositoryService.sellItemById(id);
    }

}
