package com.visma.internship.warehouse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;


@Component
public class WarehouseRepository {
    // HashMap pasidaryti.
    // Autentifikacija.
    // Unit testu - Metodus kviesti.
    // Integraciniai testai - Su spring boot context, mvc,mockito mock.
    private ArrayList<Item> items = new ArrayList<>();

    public WarehouseRepository() {
        items.add(new Item(0,"Spade","Used for digging",15,5));
        items.add(new Item(1,"Rake","Simple rake",5,15));
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public Optional<Item> getItemById(int id){
        return items.stream().filter(item -> item.getId() == id).findFirst();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public boolean removeItemById(int id){
        Optional<Item> item = getItemById(id);
        if(item.isPresent()){
            int index = items.indexOf(item.get());
            int lastQuantity = items.get(index).getQuantity();
            items.get(index).setQuantity(lastQuantity-1);
            return true;
        }else{
            return false;
        }
    }
}
