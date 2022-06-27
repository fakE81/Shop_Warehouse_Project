package com.visma.internship.warehouse.services;

import com.visma.internship.warehouse.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public class WarehouseDatabaseRepository implements WarehouseRepository {

    private  DatabaseRepository databaseRepository;

    @Autowired
    public WarehouseDatabaseRepository(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    @Override
    public List<Item> findAll() {
        return databaseRepository.findAll();
    }

    @Override
    public Optional<Item> findItem(long id) {
        return databaseRepository.findById(id);
    }

    @Override
    public void createItem(Item item) {
        databaseRepository.save(item);
    }

    @Override
    public boolean removeOneQntFromItemById(long id) {
        return false;
    }
}
