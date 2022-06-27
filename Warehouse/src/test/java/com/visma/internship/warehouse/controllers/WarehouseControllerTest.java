package com.visma.internship.warehouse.controllers;

import com.google.gson.Gson;
import com.visma.internship.ItemDTO;
import com.visma.internship.warehouse.entities.Item;
import com.visma.internship.warehouse.services.WarehouseRepositoryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;


@WebMvcTest(WarehouseController.class)
@AutoConfigureMockMvc(addFilters = false)
class WarehouseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    WarehouseRepositoryService warehouseRepositoryService;

    @Test
    public void showListOfItems() throws Exception {
        ItemDTO item1 = new ItemDTO(500,"Test","Test",20,60);
        ItemDTO item2 = new ItemDTO(600,"Test","Test",20,60);
        ArrayList<ItemDTO> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        Mockito.when(warehouseRepositoryService.findAllItems()).thenReturn(items);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/items/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)));
    }

    @Test
    public void addItem() throws Exception {

        Item item = new Item(500,"Test","Test",20,60);
        Gson gson = new Gson();
        String json = gson.toJson(item);

        // Siunciam Json(objekta) ir laukiam atsakymo isOk
        mockMvc.perform(MockMvcRequestBuilders.post("/api/items/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void removeItem() throws Exception {

        Mockito.when(warehouseRepositoryService.sellItemById(500)).thenReturn(ResponseEntity.ok("ok"));
        Mockito.when(warehouseRepositoryService.sellItemById(-1)).thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/items/500/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/items/-1/"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}