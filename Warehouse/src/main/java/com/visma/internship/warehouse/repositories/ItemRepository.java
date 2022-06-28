package com.visma.internship.warehouse.repositories;

import com.visma.internship.warehouse.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
}
