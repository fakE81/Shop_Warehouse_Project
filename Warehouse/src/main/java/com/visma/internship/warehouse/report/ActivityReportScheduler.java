package com.visma.internship.warehouse.report;

import com.visma.internship.warehouse.entities.UserActivity;
import com.visma.internship.warehouse.services.ActivityRepositoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalTime;
import java.util.List;

@Service
@EnableScheduling
public class ActivityReportScheduler {

    @Value("${activities.filepath}")
    String filepath;

    ActivityRepositoryService activityRepositoryService;

    public ActivityReportScheduler(ActivityRepositoryService activityRepositoryService) {
        this.activityRepositoryService = activityRepositoryService;
    }

    // Kas valanda reportas.
    @Scheduled(fixedDelay = 60*60*1000)
    public void generateReport() {
        List<UserActivity> userActivityList = activityRepositoryService.findLastHourActivities();
        try{
            StringBuilder data = new StringBuilder("Id,User,Item,Price,Time\n");
            for(UserActivity userActivity : userActivityList){
                data.append(userActivity.getId()).append(",");
                data.append(userActivity.getShopUser().getName()).append(",");
                data.append(userActivity.getItem().getName()).append(",");
                data.append(userActivity.getItem().getPrice()).append(",");
                data.append(userActivity.getActivityTime());
                data.append("\n");
            }
            String filename = filepath + LocalTime.now().getHour()+".csv";
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(data.toString());
            fileWriter.flush();
        } catch (IOException e){

        }
    }
}
