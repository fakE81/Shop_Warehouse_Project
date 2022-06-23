package com.visma.internship.warehouse.services;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperBean {
    // Gal kazkaip Config reiktu pavadint.
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
