package com.visma.internship.warehouse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;


@Component
public class WarehouseRepository {
    // HashMap pasidaryti.
    // Autentifikacija.
    // Unit testu - Metodus kviesti.
    // Integraciniai testai - Su spring boot context, mvc,mockito mock.
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

    public boolean removeItemByName(String name){
        // Per stream pasirasyti pagal ID.
        int index = 0;
        boolean found = false;
        for(Item item : items){
            if(item.getName().equals(name)){
                found = true;
                break;
            }
            index++;
        }
        // Kur det check ar yra ta preke ar ne?
        if(found){
            int lastQuantity = items.get(index).getQuantity();
            items.get(index).setQuantity(lastQuantity-1);
            return true;
        }else{
            return false;
        }

    }
}
