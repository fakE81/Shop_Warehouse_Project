package com.visma.internship.warehouse.report;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
public class ActivityReportService {

    @Value("${activities.filepath}")
    String filepath;

    public ResponseEntity<Resource> downloadReport()  {
        try{
            File file = new File(filepath);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"activities.csv\"")
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(resource);
        }catch (FileNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}