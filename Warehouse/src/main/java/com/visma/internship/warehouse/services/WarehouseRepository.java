package com.visma.internship.warehouse.services;

import com.visma.internship.Item;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class WarehouseRepository {

    // Tas pats ir cia Map naudojam, o kuriam HashMap, nes Map yra interface, o hashmap implementation jau.
    private Map<Integer,Item> items = new HashMap<>();

    public WarehouseRepository() {
        items.put(0,new Item(0,"Spade","Used for digging",15,5));
        items.put(1,new Item(1,"Rake","Simple rake",5,15));
    }

    public List<Item> getItems() {
        return items.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public Optional<Item> getItemById(int id){
        return Optional.ofNullable(items.get(id));
    }

    public void addItem(Item item) {
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
