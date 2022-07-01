package com.visma.internship.warehouse.repositories;

import com.visma.internship.warehouse.entities.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<UserActivity,Long> {

    @Query(value = "SELECT * FROM user_activity WHERE activity_time > localtimestamp - interval '1:00'",nativeQuery = true)
    List<UserActivity> findLastHourActivities();

    @Query("SELECT u FROM UserActivity u WHERE u.shopUser.id =:id")
    List<UserActivity> findByShopUser(@Param("id") long id);
}
