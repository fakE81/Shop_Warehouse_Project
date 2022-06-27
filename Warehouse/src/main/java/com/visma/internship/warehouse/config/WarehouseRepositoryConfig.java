package com.visma.internship.warehouse.config;

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

    @Autowired
    private WarehouseDatabaseRepository warehouseDatabaseRepository;

    @Autowired
    private WarehouseInMemoryRepository warehouseInMemoryRepository;

    @Primary
    @Bean(name = "repository")
    public WarehouseRepository getWarehouseRepository(){
        if(isDatabase){
            return warehouseDatabaseRepository;
        }else{
            return warehouseInMemoryRepository;
        }
    }
}
