package com.visma.internship.shop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/shop")
@RestController
public class ShopController {

    // Ka daryti su tais response. Ar kazkokia struktura yra ka grazinti, o jei nieko negrazina ka tada.


    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/items")
    public ArrayList<Item> getItems(){
        String url ="http://localhost:8081/warehouse/api/items";
        HttpHeaders header = new HttpHeaders();
        header.setBasicAuth("admin","admin");
        HttpEntity entity = new HttpEntity(header);

        ResponseEntity<ArrayList<Item>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<ArrayList<Item>>() {});

        return response.getBody();
    }

    @RequestMapping("/item/{id}")
    public Item getItem(@PathVariable("id") int id){
        String url ="http://localhost:8081/warehouse/api/item/"+id;
        HttpHeaders header = new HttpHeaders();
        header.setBasicAuth("admin","admin");
        HttpEntity entity = new HttpEntity(header);

        ResponseEntity<Item> response = restTemplate.exchange
                (url, HttpMethod.GET, entity, Item.class);

        return response.getBody();

    }

    @RequestMapping("/item/sell/{id}")
    public String sellItem(@PathVariable("id") int id){
        String url ="http://localhost:8081/warehouse/api/items/"+id;
        HttpHeaders header = new HttpHeaders();
        header.setBasicAuth("admin","admin");
        HttpEntity entity = new HttpEntity(header);

        // Pagaunam exception, nes nustatem 400 error koda ten. Ar kaip cia daryt kai neranda item.
        try{
            ResponseEntity<String> response = restTemplate.exchange
                    (url, HttpMethod.PUT, entity,String.class);
            return response.getBody();
        }catch (Exception e){
            return "No item found!";
        }
    }
}
