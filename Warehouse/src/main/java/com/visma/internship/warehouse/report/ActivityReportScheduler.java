package com.visma.internship.warehouse.report;

import com.visma.internship.warehouse.entities.ShopUser;
import com.visma.internship.warehouse.entities.UserActivity;
import com.visma.internship.warehouse.services.ActivityRepositoryService;
import com.visma.internship.warehouse.services.UserRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@EnableScheduling
public class ActivityReportScheduler {

    ActivityReportService activityReportService;

    public ActivityReportScheduler(ActivityReportService activityReportService) {
        this.activityReportService = activityReportService;
    }

    // Kas valanda reportas.
    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void generateReport() {
        activityReportService.generateReport();
    }

}
