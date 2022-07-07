package com.visma.internship.warehouse.controllers;


import com.visma.internship.ItemDTO;
import com.visma.internship.warehouse.entities.Item;
import com.visma.internship.warehouse.entities.UserActivity;
import com.visma.internship.warehouse.report.ActivityReportService;
import com.visma.internship.warehouse.services.WarehouseRepositoryService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class WarehouseController {

    private final WarehouseRepositoryService warehouseRepositoryService;

    private final ActivityReportService activityReportService;

    public WarehouseController(WarehouseRepositoryService warehouseRepositoryService, ActivityReportService activityReportService) {
        this.warehouseRepositoryService = warehouseRepositoryService;
        this.activityReportService = activityReportService;
    }

    @GetMapping("/items")
    public List<ItemDTO> printWarehouseItems() {
        return warehouseRepositoryService.findAllItems();
    }

    @GetMapping("/item/{id}")
    public Optional<ItemDTO> printWarehouseItem(@PathVariable("id") Long id) {
        return warehouseRepositoryService.findItemById(id);
    }

    @PostMapping("/items")
    public void addItem(@RequestBody Item item) {
        warehouseRepositoryService.addItem(item);
    }

    //TODO: Cia gaudai exception/suformuoji responseEntity
    @PutMapping("/items/{id}")
    public ResponseEntity<String> sellItem(@PathVariable("id") int id) {
        return warehouseRepositoryService.sellItemById(id);
    }

    @GetMapping("report/download/{hour}")
    public ResponseEntity<Resource> downloadReport(@PathVariable("hour") int hour) {
        return activityReportService.downloadReport(hour);
    }

    @GetMapping("user/activity/{id}")
    public List<UserActivity> getUserActivity(@PathVariable("id") long id) {
        return warehouseRepositoryService.getUserActivityById(id);
    }

}
