package com.visma.internship.warehouse.services;

import com.visma.internship.warehouse.entities.ShopUser;
import com.visma.internship.warehouse.entities.UserActivity;
import com.visma.internship.warehouse.repositories.ActivityRepository;
import com.visma.internship.warehouse.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void saveActivity(long item_id,String name){
        List<ShopUser> shopUsers = userRepository.findAll();
        Optional<ShopUser> user = findUserByName(shopUsers,name);
        if(user.isPresent()){
            activityRepository.save(new UserActivity(user.get().getId(),item_id));
        }else{
            //Nieko, bet cia visada bus user.
        }
    }

    private Optional<ShopUser> findUserByName(List<ShopUser> users, String name){
        return users.stream().filter(shopUser -> shopUser.getName().equals(name)).findFirst();
    }
}
