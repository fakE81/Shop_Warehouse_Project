package com.visma.internship.shop.services;

import com.visma.internship.ItemDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WarehouseService {

    private final RestTemplateBuilder restTemplateBuilder;

    private RestTemplate restTemplate;

    @Value("${login.username}")
    private String username;
    @Value("${login.password}")
    private String password;

    public WarehouseService(RestTemplateBuilder restTemplateBuilder, @Value("${warehouse.url}") String warehouseUrl) {
        this.restTemplateBuilder = restTemplateBuilder;
        restTemplate = restTemplateBuilder.rootUri(warehouseUrl).build();
    }

    public List<ItemDTO> getItems() {
        ResponseEntity<ArrayList<ItemDTO>> response = restTemplate.exchange(
                "/api/items",
                HttpMethod.GET,
                setupHttpEntity(),
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }

    public ItemDTO getItem(int id) {
        ResponseEntity<ItemDTO> response = restTemplate.exchange(
                "/api/item/{id}",
                HttpMethod.GET,
                setupHttpEntity(),
                ItemDTO.class,
                id);

        return response.getBody();
    }

    public ResponseEntity<String> sellItem(int id) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    "/api/items/{id}",
                    HttpMethod.PUT,
                    setupHttpEntity(),
                    String.class,
                    id);

            return response;

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Resource> downloadActivity() {
        String url = "/api/report/download/{hour}";
        HttpEntity<Resource> entity = setupHttpEntity(MediaType.APPLICATION_OCTET_STREAM);

        ResponseEntity<Resource> response = restTemplate.exchange(
                "/api/report/download/{hour}",
                HttpMethod.GET,
                entity,
                Resource.class,
                LocalTime.now().getHour());

        return response;
    }

    public ResponseEntity<Resource> downloadUserActivity() {
        String url = "/api/report/activity/user/download";
        HttpEntity<Resource> entity = setupHttpEntity(MediaType.APPLICATION_OCTET_STREAM);

        ResponseEntity<Resource> response = restTemplate.exchange(
                "/api/report/activity/user/download",
                HttpMethod.GET,
                entity,
                Resource.class);

        return response;
    }

    private HttpEntity setupHttpEntity() {
        HttpHeaders header = new HttpHeaders();
        header.setBasicAuth(username, password);
        return new HttpEntity(header);
    }

    private HttpEntity setupHttpEntity(MediaType... accept) {
        HttpHeaders header = new HttpHeaders();
        header.setBasicAuth(username, password);
        if (accept != null) {
            header.setAccept(Arrays.asList(accept));
        }
        return new HttpEntity(header);
    }

}
