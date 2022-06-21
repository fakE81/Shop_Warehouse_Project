package com.visma.internship.shop.controllers;

import com.visma.internship.Item;
import com.visma.internship.shop.controllers.ShopController;
import com.visma.internship.shop.services.WarehouseService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

@WebMvcTest(ShopController.class)
@AutoConfigureMockMvc(addFilters = false)
class ShopControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WarehouseService warehouseService;

    @Test
    void showListOfItems() throws Exception {
        Item item = new Item(500,"Test","Test",20,60);
        Item item2 = new Item(600,"Test","Test",20,60);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        items.add(item2);

        Mockito.when(warehouseService.getItems()).thenReturn(items);

        mockMvc.perform(MockMvcRequestBuilders.get("/shop/items/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)));
    }

    @Test
    void showItem() throws Exception {
        Item item = new Item(500,"Test","Test",20,60);

        Mockito.when(warehouseService.getItem(500)).thenReturn(item);

        mockMvc.perform(MockMvcRequestBuilders.get("/shop/item/500"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Test")));
    }

    @Test
    void sellItem() throws Exception {

        Mockito.when(warehouseService.sellItem(0)).thenReturn(ResponseEntity.ok("Sold"));
        Mockito.when(warehouseService.sellItem(9999)).thenReturn(ResponseEntity.badRequest().body("No item!"));

        // Kai pavyksta parduoti:
        mockMvc.perform(MockMvcRequestBuilders.get("/shop/item/sell/0"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Kai nepavyksta:
        mockMvc.perform(MockMvcRequestBuilders.get("/shop/item/sell/9999"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}