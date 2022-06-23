package com.visma.internship.warehouse.services;

import com.visma.internship.Item;

import java.util.List;
import java.util.Optional;

public class WarehouseRepositoryDatabase implements WarehouseRepository{




    @Override
    public List<Item> findItems() {
        return null;
    }

    @Override
    public Optional<Item> findItem(int id) {
        return Optional.empty();
    }

    @Override
    public void createItem(Item item) {

    }

    @Override
    public boolean removeOneQntFromItemById(int id) {
        return false;
    }
}
