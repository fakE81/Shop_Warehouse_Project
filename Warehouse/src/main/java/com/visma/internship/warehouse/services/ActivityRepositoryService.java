package com.visma.internship.warehouse.services;

import com.visma.internship.warehouse.entities.ShopUser;
import com.visma.internship.warehouse.entities.UserActivity;
import com.visma.internship.warehouse.repositories.ActivityRepository;
import com.visma.internship.warehouse.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityRepositoryService {
    private ActivityRepository activityRepository;

    private UserRepository userRepository;

    public ActivityRepositoryService(ActivityRepository activityRepository, UserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    public void saveActivity(long item_id,String name){
        List<ShopUser> shopUsers = userRepository.findAll();
        long user_id = findIdByName(shopUsers,name);
        activityRepository.save(new UserActivity(item_id,user_id));
    }

    // Nelabai gerai jeigu vienodi username.
    private long findIdByName(List<ShopUser> users,String name){
        for(ShopUser shopUser : users){
            if(shopUser.getName().equals(name)){
                return shopUser.getId();
            }
        }
        return -1L;
    }
}
