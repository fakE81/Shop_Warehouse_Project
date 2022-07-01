package com.visma.internship.shop.services;

import com.visma.internship.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${warehouse.url}")
    private String warehouseUrl;

    @Value("${login.username}")
    private String username;
    @Value("${login.password}")
    private String password;

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

    public ResponseEntity<Resource> downloadActivity(){

            String url = warehouseUrl + "/api/report/download/" + LocalTime.now().getHour();
            System.out.println(url);
            HttpHeaders header = new HttpHeaders();
            header.setBasicAuth(username, password);
            header.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
            HttpEntity<Resource> entity = new HttpEntity<>(header);

            ResponseEntity<Resource> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    Resource.class);

            return response;
            //Files.write(Paths.get("C:\\Users\\laurynas.seikis\\Downloads\\11.csv"), response.getBody());

    }

    public HttpEntity setupHttpEntity(){
        HttpHeaders header = new HttpHeaders();
        header.setBasicAuth(username,password);
        return new HttpEntity(header);
    }

}
