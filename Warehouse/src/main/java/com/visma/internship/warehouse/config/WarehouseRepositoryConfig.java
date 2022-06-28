package com.visma.internship.warehouse.config;

import com.visma.internship.warehouse.repositories.ItemRepository;
import com.visma.internship.warehouse.repositories.WarehouseDatabaseRepository;
import com.visma.internship.warehouse.repositories.WarehouseInMemoryRepository;
import com.visma.internship.warehouse.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class WarehouseRepositoryConfig {

    @Value("${warehouse.db}")
    private boolean isDatabase;

    @Primary
    @Bean(name = "repository")
    public WarehouseRepository getWarehouseRepository(ItemRepository itemRepository){
        if(isDatabase){
            return new WarehouseDatabaseRepository(itemRepository);
        }else{
            return new WarehouseInMemoryRepository();
        }
    }
}
