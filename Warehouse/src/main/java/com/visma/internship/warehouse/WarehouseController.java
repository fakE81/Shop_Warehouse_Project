package com.visma.internship.warehouse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

    @PostMapping("/items")
    public void addItem(@RequestBody Item item){
        warehouseRepository.addItem(item);
    }

}
