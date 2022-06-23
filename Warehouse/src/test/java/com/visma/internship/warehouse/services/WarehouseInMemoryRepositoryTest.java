package com.visma.internship.warehouse.services;

import com.visma.internship.ItemDTO;
import com.visma.internship.warehouse.entities.Item;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class WarehouseInMemoryRepositoryTest {
    private final WarehouseRepository warehouseInMemoryRepository = new WarehouseInMemoryRepository();

    @Test
    void addItemTest() {
        int itemsCount = warehouseInMemoryRepository.findAll().size();
        Item item = new Item(3,".",".",15,20);
        warehouseInMemoryRepository.createItem(item);
        Assert.assertEquals(itemsCount+1, warehouseInMemoryRepository.findAll().size());
    }

    @Test
    void removeItemById() {
        Item item = new Item(4L,".",".",15,20);
        warehouseInMemoryRepository.createItem(item);
        boolean removed = warehouseInMemoryRepository.removeOneQntFromItemById(4L);
        Assert.assertEquals(19, warehouseInMemoryRepository.findItem(4L).get().getQuantity());
        Assert.assertTrue(removed);

        removed = warehouseInMemoryRepository.removeOneQntFromItemById(5);
        Assert.assertFalse(removed);

        removed = warehouseInMemoryRepository.removeOneQntFromItemById(-5);
        Assert.assertFalse(removed);

        removed = warehouseInMemoryRepository.removeOneQntFromItemById(Integer.MAX_VALUE+1);
        Assert.assertFalse(removed);
    }
}