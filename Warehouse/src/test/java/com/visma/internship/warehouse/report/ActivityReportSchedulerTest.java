package com.visma.internship.warehouse.report;

import com.visma.internship.warehouse.entities.Item;
import com.visma.internship.warehouse.entities.ShopUser;
import com.visma.internship.warehouse.entities.UserActivity;
import com.visma.internship.warehouse.services.ActivityRepositoryService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActivityReportSchedulerTest {

    @Value("${activities.filepath}")
    String filepath;

    @Mock
    ActivityRepositoryService activityRepositoryService;

    @InjectMocks
    ActivityReportScheduler activityReportScheduler;

    @Test
    void generateReport() {
        List<UserActivity> userActivityList = new ArrayList<>();
        Item item = new Item(3,"Spade","good.",15,20);
        ShopUser shopUser = new ShopUser(999L,"Username","Password","Role");
        userActivityList.add(new UserActivity(1000L,shopUser,item));
        Mockito.when(activityRepositoryService.findLastHourActivities()).thenReturn(userActivityList);
        String filename = filepath + LocalTime.now().getHour()+".csv";
        activityReportScheduler.generateReport();

        // Tikrinam
        File file = new File(filename);
        Assert.assertTrue(file.isFile());
    }
}