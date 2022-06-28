package com.visma.internship.warehouse.repositories;

import com.visma.internship.warehouse.entities.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ShopUser,Long> {

}