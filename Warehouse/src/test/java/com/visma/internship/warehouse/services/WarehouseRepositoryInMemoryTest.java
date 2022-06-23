package com.visma.internship.warehouse.services;

import com.visma.internship.Item;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class WarehouseRepositoryInMemoryTest {
    private final WarehouseRepositoryInMemory warehouseRepositoryInMemory = new WarehouseRepositoryInMemory();

    @Test
    void addItemTest() {
        int itemsCount = warehouseRepositoryInMemory.getItems().size();
        Item item = new Item(3,".",".",15,20);
        warehouseRepositoryInMemory.addItem(item);
        Assert.assertEquals(itemsCount+1, warehouseRepositoryInMemory.getItems().size());
    }

    @Test
    void removeItemById() {
        Item item = new Item(4,".",".",15,20);
        warehouseRepositoryInMemory.addItem(item);
        boolean removed = warehouseRepositoryInMemory.removeOneQntFromItemById(4);
        Assert.assertEquals(19, warehouseRepositoryInMemory.getItemById(4).get().getQuantity());
        Assert.assertTrue(removed);

        removed = warehouseRepositoryInMemory.removeOneQntFromItemById(5);
        Assert.assertFalse(removed);

        removed = warehouseRepositoryInMemory.removeOneQntFromItemById(-5);
        Assert.assertFalse(removed);

        removed = warehouseRepositoryInMemory.removeOneQntFromItemById(Integer.MAX_VALUE+1);
        Assert.assertFalse(removed);
    }
}