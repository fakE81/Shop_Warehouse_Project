package com.visma.internship.warehouse.repositories;

import com.visma.internship.warehouse.entities.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<UserActivity,Long> {

}
