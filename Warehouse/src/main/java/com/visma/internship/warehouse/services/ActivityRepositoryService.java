package com.visma.internship.warehouse.services;

import com.visma.internship.warehouse.entities.Item;
import com.visma.internship.warehouse.entities.ShopUser;
import com.visma.internship.warehouse.entities.UserActivity;
import com.visma.internship.warehouse.repositories.ActivityRepository;
import com.visma.internship.warehouse.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityRepositoryService {
    private ActivityRepository activityRepository;

    private UserRepository userRepository;

    public ActivityRepositoryService(ActivityRepository activityRepository, UserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    public void saveActivity(Item item, String name){
        List<ShopUser> shopUsers = userRepository.findAll();
        Optional<ShopUser> user = findUserByName(shopUsers,name);

        user.ifPresent(shopUser -> {
            activityRepository.save(new UserActivity(shopUser,item));
        });
    }

    public List<UserActivity> findAllActivitiesByUserId(long id){
        return activityRepository.findAll().stream().filter(userActivity -> userActivity.getShopUser().getId() == id).collect(Collectors.toList());
    }

    private Optional<ShopUser> findUserByName(List<ShopUser> users, String name){
        return users.stream().filter(shopUser -> shopUser.getName().equals(name)).findFirst();
    }
}
