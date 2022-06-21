package com.visma.internship.warehouse.services;

import com.visma.internship.Item;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class WarehouseRepository {

    private HashMap<Integer,Item> items = new HashMap<>();

    public WarehouseRepository() {
        items.put(0,new Item(0,"Spade","Used for digging",15,5));
        items.put(1,new Item(1,"Rake","Simple rake",5,15));
    }

    public ArrayList<Item> getItems() {
        return (ArrayList<Item>) items.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }
    public Optional<Item> getItemById(int id){

        return Optional.ofNullable(items.get(id));
    }

    public void addItem(Item item) {
        this.items.put(item.getId(),item);
    }

    public boolean removeItemById(int id){
        if(items.containsKey(id)){
            int lastQuantity = items.get(id).getQuantity();
            items.get(id).setQuantity(lastQuantity-1);
            return true;
        }
        else{
            return false;
        }
    }
}
