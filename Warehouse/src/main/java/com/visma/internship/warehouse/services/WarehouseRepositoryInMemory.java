package com.visma.internship.warehouse.services;

import com.visma.internship.Item;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class WarehouseRepositoryInMemory implements WarehouseRepository{
    
    private Map<Integer,Item> items = new HashMap<>();

    public WarehouseRepositoryInMemory() {
        items.put(0,new Item(0,"Spade","Used for digging",15,5));
        items.put(1,new Item(1,"Rake","Simple rake",5,15));
    }

    @Override
    public List<Item> findItems() {
        return items.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public Optional<Item> findItem(int id) {
        return Optional.ofNullable(items.get(id));
    }

    @Override
    public void createItem(Item item) {
        this.items.put(item.getId(),item);
    }

    public boolean removeOneQntFromItemById(int id){
        if(items.containsKey(id)){
            int lastQuantity = items.get(id).getQuantity();
            items.get(id).setQuantity(lastQuantity-1);
            return true;
        } else{
            return false;
        }
    }
}
