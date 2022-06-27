package com.visma.internship.warehouse.services;

import com.visma.internship.warehouse.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseRepository extends JpaRepository<Item,Long> {
}
