package com.example.Doctor_Availability_Finder.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Service
public class DataLoaderService {
        private final ObjectMapper objectMapper;

        public DataLoaderService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

      @PostConstruct
    public void loadDataFromJson() {
        try (InputStream inputStream = getClass().getResourceAsStream("/AvailabilityTimings.json")) {
            if (inputStream == null) {
                throw new IOException("Could not find JSON file");
            }
            
            Map<String, Map<String, List<Map<String, String>>>> availableTimings = objectMapper.readValue(inputStream, new TypeReference<Map<String, Map<String, List<Map<String, String>>>>>() {});
            
            System.out.println("Data loaded");
            System.out.println(availableTimings);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
