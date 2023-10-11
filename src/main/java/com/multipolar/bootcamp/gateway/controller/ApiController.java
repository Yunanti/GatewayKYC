package com.multipolar.bootcamp.gateway.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multipolar.bootcamp.gateway.dto.CustomerDTO;
import com.multipolar.bootcamp.gateway.dto.ErrorMessageDTO;
import com.multipolar.bootcamp.gateway.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private static final String CUSTOMER_URL = "http://localhost:8081/kyc";
    private final RestTemplateUtil restTemplateUtil;
    private final ObjectMapper objectMapper;

    @Autowired
    public ApiController(RestTemplateUtil restTemplateUtil, ObjectMapper objectMapper) {
        this.restTemplateUtil = restTemplateUtil;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/getCustomers")
    public ResponseEntity<?> getCustomers() throws JsonProcessingException {
//        akses API customer dan dapatkan datanya
        try{
            ResponseEntity<?> response = restTemplateUtil.getList(CUSTOMER_URL, new ParameterizedTypeReference<>() {});
            ResponseEntity.status(response.getStatusCode()).body(response.getBody());
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        }catch (HttpClientErrorException ex){
            List<ErrorMessageDTO> errorResponse = objectMapper.readValue(ex.getResponseBodyAsString(),List.class);
            return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
        }
    }

//    Buat
    @PostMapping("/createCustomer")
    public ResponseEntity<?> postCustomer(@RequestBody CustomerDTO customerDTO) throws JsonProcessingException {
        try {
            ResponseEntity<?> response = restTemplateUtil.post(CUSTOMER_URL, customerDTO, CustomerDTO.class);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (HttpClientErrorException ex) {
            List<ErrorMessageDTO> errorResponse = objectMapper.readValue(ex.getResponseBodyAsString(), List.class);
            return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
        }
    }

}
