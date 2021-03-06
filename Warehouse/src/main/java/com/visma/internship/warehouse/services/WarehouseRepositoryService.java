package com.visma.internship.warehouse.services;


import com.sun.istack.NotNull;
import com.visma.internship.ItemDTO;
import com.visma.internship.warehouse.entities.Item;
import com.visma.internship.warehouse.entities.UserActivity;
import com.visma.internship.warehouse.exceptions.ItemNotFoundException;
import com.visma.internship.warehouse.repositories.WarehouseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarehouseRepositoryService {
    // Atsakingas uz konvertavima Item -> ItemDto
    private ModelMapper modelMapper;

    @PersistenceContext
    private EntityManager em;

    @Qualifier("repository")
    private final WarehouseRepository warehouseRepository;

    private final ActivityRepositoryService activityRepositoryService;

    public WarehouseRepositoryService(ModelMapper modelMapper, WarehouseRepository warehouseRepository, ActivityRepositoryService activityRepositoryService) {
        this.modelMapper = modelMapper;
        this.warehouseRepository = warehouseRepository;
        this.activityRepositoryService = activityRepositoryService;
    }

    public Optional<ItemDTO> findItemById(long id) {
        return warehouseRepository.findItem(id).map(this::convertItemToDto);
    }

    public List<ItemDTO> findAllItems() {
        return convertItemListToDtoList(warehouseRepository.findAll());
    }

    public void addItem(Item item) {
        warehouseRepository.createItem(item);
    }

    public void sellItemById(long id) throws ItemNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        boolean status = warehouseRepository.removeOneQntFromItemById(id);
        if (status) {
            Item item = em.getReference(Item.class, id);
            activityRepositoryService.saveActivity(item, name);
        } else {
            throw new ItemNotFoundException("Item not found");
        }
    }

    private ItemDTO convertItemToDto(@NotNull Item item) {
        ItemDTO itemDTO = new ItemDTO();
        BeanUtils.copyProperties(item, itemDTO);
        return itemDTO;
    }

    private List<ItemDTO> convertItemListToDtoList(List<Item> items) {
        return items.stream()
                .map(this::convertItemToDto)
                .collect(Collectors.toList());
    }

    public List<UserActivity> getUserActivityById(long id) {
        return activityRepositoryService.findAllActivitiesByUserId(id);
    }
}
