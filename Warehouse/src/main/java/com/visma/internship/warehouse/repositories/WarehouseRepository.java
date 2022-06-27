package com.visma.internship.warehouse.repositories;

import com.visma.internship.warehouse.entities.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository {

    public List<Item> findAll();
    public Optional<Item> findItem(long id);
    public void createItem(Item item);
    public boolean removeOneQntFromItemById(long id);
    // 1 lentele su Items
    // I duombaze User lentele su prisijungimais.
    // Lentele pardavimams kurie ivyko
    // UI spring boot, clientui
    // Generuoti mygtukas, karta i valanda atsiusti api. Api callas.
}