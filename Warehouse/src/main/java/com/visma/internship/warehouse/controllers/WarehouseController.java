package com.visma.internship.warehouse.controllers;


import com.visma.internship.ItemDTO;
import com.visma.internship.warehouse.entities.Item;
import com.visma.internship.warehouse.entities.UserActivity;
import com.visma.internship.warehouse.exceptions.ItemNotFoundException;
import com.visma.internship.warehouse.report.ActivityReportScheduler;
import com.visma.internship.warehouse.services.WarehouseRepositoryService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class WarehouseController {

    private final WarehouseRepositoryService warehouseRepositoryService;

    private final ActivityReportScheduler activityReportScheduler;

    public WarehouseController(WarehouseRepositoryService warehouseRepositoryService, ActivityReportScheduler activityReportScheduler) {
        this.warehouseRepositoryService = warehouseRepositoryService;
        this.activityReportScheduler = activityReportScheduler;
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

    @PutMapping("/items/{id}")
    public ResponseEntity<String> sellItem(@PathVariable("id") int id) {
        try{
            warehouseRepositoryService.sellItemById(id);
            return ResponseEntity.ok("Item sold!");
        }catch (ItemNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("report/download/{hour}")
    public ResponseEntity<Resource> downloadReport(@PathVariable("hour") int hour) {
        try{
            InputStreamResource resource = activityReportScheduler.createInputStreamResourceForReport(hour);
            String filename = activityReportScheduler.generateFilename(hour);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"Activity_Report_"+filename+"\"")
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(resource);
        }catch (FileNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("report/activity/user/download")
    public ResponseEntity<Resource> downloadUserReport(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            InputStreamResource resource = activityReportScheduler.createDownloadUserActivityAction(username);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"User_Activity_Report_"+username+".csv\"")
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(resource);
        }catch (FileNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("user/activity/{id}")
    public List<UserActivity> getUserActivity(@PathVariable("id") long id) {
        return warehouseRepositoryService.getUserActivityById(id);
    }

}
