package com.visma.internship.warehouse.repositories;

import com.visma.internship.warehouse.entities.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository {

    List<Item> findAll();

    Optional<Item> findItem(long id);

    void createItem(Item item);

    boolean removeOneQntFromItemById(long id);
}