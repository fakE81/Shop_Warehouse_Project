package com.visma.internship.warehouse;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseRepositoryTest {
    // Su netaisyklingais duomenims Unit testai
    private WarehouseRepository warehouseRepository = new WarehouseRepository();

    @Test
    void addItemTest() {
        int itemsCount = warehouseRepository.getItems().size();
        Item item = new Item(3,".",".",15,20);
        warehouseRepository.addItem(item);
        Assert.assertEquals(itemsCount+1,warehouseRepository.getItems().size());
    }

    @Test
    void removeItemById() {
        Item item = new Item(4,".",".",15,20);
        warehouseRepository.addItem(item);
        boolean removed = warehouseRepository.removeItemById(4);
        Assert.assertEquals(19,warehouseRepository.getItemById(4).get().getQuantity());
        Assert.assertEquals(true,removed);

        removed = warehouseRepository.removeItemById(5);
        Assert.assertEquals(false,removed);
    }
}