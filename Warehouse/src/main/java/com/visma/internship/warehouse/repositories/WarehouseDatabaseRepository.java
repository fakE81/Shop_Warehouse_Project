package com.visma.internship.warehouse.repositories;

import com.visma.internship.warehouse.entities.Item;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public class WarehouseDatabaseRepository implements WarehouseRepository {

    private final ItemRepository itemRepository;

    public WarehouseDatabaseRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> findItem(long id) {
        return itemRepository.findById(id);
    }

    @Override
    public void createItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public boolean removeOneQntFromItemById(long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            int qnt = item.get().getQuantity();
            if (qnt <= 0) {
                return false;
            }
            item.get().setQuantity(qnt - 1);
            itemRepository.save(item.get());
            return true;
        }
        return false;
    }
}
