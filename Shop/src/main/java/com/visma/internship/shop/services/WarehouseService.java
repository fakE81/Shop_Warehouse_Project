package com.visma.internship.shop.services;

import com.visma.internship.ItemDTO;
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

    @Autowired
    RestTemplate restTemplate;

    @Value("${warehouse.url}")
    private String warehouseUrl;

    public List<ItemDTO> getItems(){
        String url = warehouseUrl+"/api/items";
        ResponseEntity<ArrayList<ItemDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                setupHttpEntity(),
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }

    public ItemDTO getItem(int id){
        String url = warehouseUrl+"/api/item/"+id;

        ResponseEntity<ItemDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                setupHttpEntity(),
                ItemDTO.class);

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
