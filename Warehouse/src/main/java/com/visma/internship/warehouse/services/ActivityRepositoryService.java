package com.visma.internship.warehouse.services;

import com.visma.internship.warehouse.entities.Item;
import com.visma.internship.warehouse.entities.ShopUser;
import com.visma.internship.warehouse.entities.UserActivity;
import com.visma.internship.warehouse.repositories.ActivityRepository;
import com.visma.internship.warehouse.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityRepositoryService {

    private ActivityRepository activityRepository;

    private UserRepository userRepository;

    public ActivityRepositoryService(ActivityRepository activityRepository, UserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    public void saveActivity(Item item, String name) {
        Optional<ShopUser> user = findFirstShopUserByName(name);

        user.ifPresent(shopUser -> {
            activityRepository.save(new UserActivity(shopUser, item));
        });
    }

    public List<UserActivity> findAllActivitiesByUserId(long id) {
        return activityRepository.findByShopUser(id);
    }

    public List<UserActivity> findAllActivities() {
        return activityRepository.findAll();
    }

    public List<UserActivity> findLastHourActivities() {
        return activityRepository.findLastHourActivities();
    }

    private Optional<ShopUser> findFirstShopUserByName(String name) {
        List<ShopUser> shopUsers = userRepository.findByName(name);
        return shopUsers.stream().findFirst();
    }
}
