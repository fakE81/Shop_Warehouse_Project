package com.visma.internship.warehouse.services;

import com.visma.internship.Item;
import com.visma.internship.warehouse.services.WarehouseRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class WarehouseRepositoryTest {
    private final WarehouseRepository warehouseRepository = new WarehouseRepository();

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
        boolean removed = warehouseRepository.removeOneQntFromItemById(4);
        Assert.assertEquals(19,warehouseRepository.getItemById(4).get().getQuantity());
        Assert.assertTrue(removed);

        removed = warehouseRepository.removeOneQntFromItemById(5);
        Assert.assertFalse(removed);

        removed = warehouseRepository.removeOneQntFromItemById(-5);
        Assert.assertFalse(removed);

        removed = warehouseRepository.removeOneQntFromItemById(Integer.MAX_VALUE+1);
        Assert.assertFalse(removed);
    }
}