package com.visma.internship.warehouse.services;


import com.visma.internship.ItemDTO;
import com.visma.internship.warehouse.entities.Item;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseRepositoryService {
    // Atsakingas uz konvertavima Item -> ItemDto
    private ModelMapper modelMapper;

    private final WarehouseRepository warehouseDatabaseRepository;

    //Testavimo sumetimais visada paleista ant DatabaseRepository.
    public WarehouseRepositoryService(ModelMapper modelMapper, WarehouseRepository warehouseDatabaseRepository) {
        this.modelMapper = modelMapper;
        this.warehouseDatabaseRepository = warehouseDatabaseRepository;
    }

    public Optional<ItemDTO> findItemById(long id){
        return convertItemToDto(warehouseDatabaseRepository.findItem(id));
    }

    public List<ItemDTO> findAllItems(){
        return convertItemListToDtoList(warehouseDatabaseRepository.findAll());
    }

    // Nelabai gerai nes daug kartu pernaudojama.
    public void addItem(Item item){
        warehouseDatabaseRepository.createItem(item);
    }

    public ResponseEntity<String> sellItemById(long id){
        boolean status = warehouseDatabaseRepository.removeOneQntFromItemById(id);
        if(status){
            return ResponseEntity.ok("Item sold!");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    private Optional<ItemDTO> convertItemToDto(Optional<Item> item){
        if(item.isPresent()){
            return Optional.of(modelMapper.map(item.get(),ItemDTO.class));
        }else{
            return Optional.empty();
        }
    }

    private List<ItemDTO> convertItemListToDtoList(List<Item> items){
        List<ItemDTO> itemsDto = modelMapper.map(items,new TypeToken<List<ItemDTO>>(){}.getType());
        return itemsDto;
    }

}
