package com.visma.internship.warehouse.services;


import com.visma.internship.ItemDTO;
import com.visma.internship.warehouse.entities.Item;
import com.visma.internship.warehouse.repositories.WarehouseRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseRepositoryService {
    // Atsakingas uz konvertavima Item -> ItemDto
    private ModelMapper modelMapper;

    @Autowired
    @Qualifier("repository")
    private final WarehouseRepository warehouseRepository;

    public WarehouseRepositoryService(ModelMapper modelMapper, WarehouseRepository warehouseRepository) {
        this.modelMapper = modelMapper;
        this.warehouseRepository = warehouseRepository;
    }

    public Optional<ItemDTO> findItemById(long id){
        return convertItemToDto(warehouseRepository.findItem(id));
    }

    public List<ItemDTO> findAllItems(){
        return convertItemListToDtoList(warehouseRepository.findAll());
    }

    public void addItem(Item item){
        warehouseRepository.createItem(item);
    }

    public ResponseEntity<String> sellItemById(long id){
        boolean status = warehouseRepository.removeOneQntFromItemById(id);
        if(status){
            return ResponseEntity.ok("Item sold!");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    private Optional<ItemDTO> convertItemToDto(Optional<Item> item){
        if(item.isPresent()){
            ItemDTO itemDTO = new ItemDTO();
            BeanUtils.copyProperties(item.get(),itemDTO);
            return Optional.of(itemDTO);
        }else{
            return Optional.empty();
        }
    }

    private List<ItemDTO> convertItemListToDtoList(List<Item> items){
        List<ItemDTO> itemsDto = modelMapper.map(items,new TypeToken<List<ItemDTO>>(){}.getType());
        return itemsDto;
    }

}
