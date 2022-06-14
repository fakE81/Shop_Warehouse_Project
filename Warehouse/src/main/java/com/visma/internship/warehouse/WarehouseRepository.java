package com.visma.internship.warehouse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;


@Component
public class WarehouseRepository {
    private ArrayList<Item> items = new ArrayList<Item>();

    public WarehouseRepository() {

        items.add(new Item("Spade","Used for digging",15,5));
        items.add(new Item("Rake","Simple rake",5,15));
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
}
