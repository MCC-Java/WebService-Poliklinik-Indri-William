/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.webservice.services;

import com.mcc.webservice.entities.Pendaftaran;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author TOSHIBA
 */

@Service
public class PendaftaranRestService {
    
    private final String uri = "http://localhost:8075/api/pendaftaran";
    
    private static final RestTemplate restTemplate = new RestTemplate();
    
    public List<Pendaftaran> getAll() {
        ResponseEntity<List<Pendaftaran>> response = restTemplate.exchange(
                uri, 
                HttpMethod.GET, 
                null, new ParameterizedTypeReference<List<Pendaftaran>>() {
                });
        List<Pendaftaran> result = response.getBody();
        return result;
    }
    
    public void save(Pendaftaran pendaftaran){
        Pendaftaran result = restTemplate.postForObject(uri + "/save", pendaftaran, Pendaftaran.class);
    }
    
    public  void delete(Integer no){
        Map<String, Integer> params = new HashMap<>();
        params.put("no", no);
        
        restTemplate.delete(uri + "/{no}", params);
        
    }
    
    public Pendaftaran findById(Integer no){
        Map<String, Integer> params = new HashMap<>();
        params.put("no", no);
        
        Pendaftaran result = restTemplate.getForObject(uri + "/{no}", Pendaftaran.class, params);
        return result;
    }
    
}
