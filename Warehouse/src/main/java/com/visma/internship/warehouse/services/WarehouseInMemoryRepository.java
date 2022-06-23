package com.visma.internship.warehouse.services;

import com.visma.internship.ItemDTO;
import com.visma.internship.warehouse.entities.Item;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;


@Repository
public class WarehouseInMemoryRepository implements WarehouseRepository{
    // Padaryti reiktu, kad veiktu per conditional autowire kazkaip:)
    private Map<Long, Item> items = new HashMap<>();

    public WarehouseInMemoryRepository() {
        items.put(0L,new Item(0,"Spade","Used for digging",15,5));
        items.put(1L,new Item(1,"Rake","Simple rake",5,15));
    }

    @Override
    public List<Item> findAll() {
        return items.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public Optional<Item> findItem(long id) {
        return Optional.ofNullable(items.get(id));
    }

    @Override
    public void createItem(Item item) {
        this.items.put(item.getId(), item);
    }

    public boolean removeOneQntFromItemById(long id){
        if(items.containsKey(id)){
            int lastQuantity = items.get(id).getQuantity();
            items.get(id).setQuantity(lastQuantity-1);
            return true;
        } else{
            return false;
        }
    }
}
