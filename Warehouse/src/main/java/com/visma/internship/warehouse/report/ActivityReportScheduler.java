package com.visma.internship.warehouse.report;

import com.visma.internship.warehouse.entities.UserActivity;
import com.visma.internship.warehouse.services.ActivityRepositoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
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

    // Kas valanda.
    @Scheduled(fixedDelay = 60*60*1000)
    public void generateReport() {
        System.out.println("Generating");
        List<UserActivity> userActivityList = activityRepositoryService.findAllActivities();
        try{
            StringBuffer data = new StringBuffer("Id,User,Item,Price\n");
            for(UserActivity userActivity : userActivityList){
                data.append(userActivity.getId()).append(",");
                data.append(userActivity.getShopUser().getName()).append(",");
                data.append(userActivity.getItem().getName()).append(",");
                data.append(userActivity.getItem().getPrice());
                data.append("\n");
            }

            FileWriter fileWriter = new FileWriter(filepath);
            fileWriter.write(data.toString());
            fileWriter.flush();
        } catch (IOException e){

        }


    }
}
