package com.visma.internship.warehouse.repositories;

import com.visma.internship.warehouse.entities.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<UserActivity,Long> {

}
