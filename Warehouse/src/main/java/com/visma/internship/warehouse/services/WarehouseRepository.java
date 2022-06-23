package com.visma.internship.warehouse.services;

import com.visma.internship.warehouse.entities.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository {

    public List<Item> findAll();
    public Optional<Item> findItem(long id);
    public void createItem(Item item);
    public boolean removeOneQntFromItemById(long id);

}
