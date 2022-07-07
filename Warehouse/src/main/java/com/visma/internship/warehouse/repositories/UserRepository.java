package com.visma.internship.warehouse.repositories;

import com.visma.internship.warehouse.entities.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<ShopUser,Long> {
    List<ShopUser> findByName(String name);
}
