package com.visma.internship.warehouse.report;

import com.visma.internship.warehouse.entities.ShopUser;
import com.visma.internship.warehouse.entities.UserActivity;
import com.visma.internship.warehouse.services.ActivityRepositoryService;
import com.visma.internship.warehouse.services.UserRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityReportService {

    @Value("${activities.filepath}")
    private String filepath;
    @Value("${useractivities.filepath}")
    private String userActivitiesFilepath;

    private ActivityRepositoryService activityRepositoryService;

    private UserRepositoryService userRepositoryService;

    Logger logger = LoggerFactory.getLogger(ActivityReportScheduler.class);

    public ActivityReportService(ActivityRepositoryService activityRepositoryService, UserRepositoryService userRepositoryService) {
        this.activityRepositoryService = activityRepositoryService;
        this.userRepositoryService = userRepositoryService;
    }

    public void generateReport() {
        List<UserActivity> userActivityList = activityRepositoryService.findLastHourActivities();
        try {
            StringBuilder data = new StringBuilder("Id,User,Item,Price,Time\n");
            for (UserActivity userActivity : userActivityList) {
                appendDataString(data, userActivity);
            }
            String filename = filepath + LocalTime.now().getHour() + ".csv";
            writeDataToFile(filename, data);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void generateUserActivity(long id) {
        logger.info("Generating user-" + id + " activity!");
        List<UserActivity> userActivityList = activityRepositoryService.findAllActivitiesByUserId(id);
        try {
            StringBuilder data = new StringBuilder("Id,User,Item,Price,Time\n");
            for (UserActivity userActivity : userActivityList) {
                appendDataString(data, userActivity);
            }
            String filename = userActivitiesFilepath + "user-" + id + ".csv";
            writeDataToFile(filename, data);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void appendDataString(StringBuilder data, UserActivity userActivity) {
        data.append(userActivity.getId()).append(",");
        data.append(userActivity.getShopUser().getName()).append(",");
        data.append(userActivity.getItem().getName()).append(",");
        data.append(userActivity.getItem().getPrice()).append(",");
        data.append(userActivity.getActivityTime());
        data.append("\n");
    }

    private void writeDataToFile(String filepath, StringBuilder data) throws IOException {
        FileWriter fileWriter = new FileWriter(filepath);
        fileWriter.write(data.toString());
        fileWriter.flush();
    }

    public InputStreamResource createInputStreamResourceForReport(int hour) throws FileNotFoundException {
        String filename = generateFilename(hour);
        File file = new File(filepath + filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return resource;
    }

    public InputStreamResource createDownloadUserActivityAction(String username) throws FileNotFoundException {
        Optional<ShopUser> user = userRepositoryService.findByName(username);
        generateUserActivity(user.get().getId());

        String filename = userActivitiesFilepath + "user-" + user.get().getId() + ".csv";
        File file = new File(filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return resource;
    }

    public String generateFilename(int hour) {
        return hour + ".csv";
    }
}
