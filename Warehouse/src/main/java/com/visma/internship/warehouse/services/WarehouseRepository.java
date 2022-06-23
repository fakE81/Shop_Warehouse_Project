package com.visma.internship.warehouse.services;

import com.visma.internship.Item;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepository {


    public List<Item> findItems();
    public Optional<Item> findItem(int id);
    public void createItem(Item item);
    public boolean removeOneQntFromItemById(int id);

}
