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
  
    // Tas pats ir cia Map naudojam, o kuriam HashMap, nes Map yra interface, o hashmap implementation jau.
    // Kazka gal galima idomaus per interfaces sukurti ir panaudoti? Pora implementaciju interfaco.

    // 1 lentele su Items
    // I duombaze User lentele su prisijungimais.
    // Lentele pardavimams kurie ivyko
    // UI spring boot, clientui
    // Generuoti mygtukas, karta i valanda atsiusti api. Api callas.

    //Warehouse repository interface
    //Pervadinti warehouse repository WarehouseInMemory Vienas su DB gales dirbti.
    //Kitas su Duombazem.
    //Conditional autowire. Galima pasidaryti kad sukurti arba DB, arba paprasta priklausomai nuo properties.
}