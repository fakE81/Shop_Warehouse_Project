package com.visma.internship.shop.services;

import com.visma.internship.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;

@Service
public class WarehouseService {
    // Turbut nelabai iseis testukus sitai klasei padaryt, kai yra siunciama uzklausa i serveri.
    @Autowired
    RestTemplate restTemplate;

    public ArrayList<Item> getItems(){
        String url = "http://localhost:8081/warehouse/api/items";
        ResponseEntity<ArrayList<Item>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                setupHttpEntity(),
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }

    public Item getItem(int id){
        String url = "http://localhost:8081/warehouse/api/item/"+id;

        ResponseEntity<Item> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                setupHttpEntity(),
                Item.class);

        return response.getBody();
    }
    public ResponseEntity<String> sellItem(int id){
        String url = "http://localhost:8081/warehouse/api/items/"+id;

        try{
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    setupHttpEntity(),
                    String.class);

            return response;

        }catch (Exception e){
            return ResponseEntity.badRequest().body("Such item not found!");
        }
    }

    public HttpEntity setupHttpEntity(){
        HttpHeaders header = new HttpHeaders();
        header.setBasicAuth("admin","admin");
        return new HttpEntity(header);
    }
}