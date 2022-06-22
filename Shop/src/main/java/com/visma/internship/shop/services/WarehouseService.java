package com.visma.internship.shop.services;

import com.visma.internship.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseService {
    // Turbut nelabai iseis testukus sitai klasei padaryt, kai yra siunciama uzklausa i serveri.
    @Autowired
    RestTemplate restTemplate;

    // Neparasius sito private, default: protected yra?
    @Value("${warehouse.url}")
    private String warehouseUrl;

    public List<Item> getItems(){
        String url = warehouseUrl+"/api/items";
        ResponseEntity<ArrayList<Item>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                setupHttpEntity(),
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }

    public Item getItem(int id){
        String url = warehouseUrl+"/api/item/"+id;

        ResponseEntity<Item> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                setupHttpEntity(),
                Item.class);

        return response.getBody();
    }
    public ResponseEntity<String> sellItem(int id){
        String url = warehouseUrl+"/api/items/"+id;

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
