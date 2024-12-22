package com.example.isaProject.controller;

import com.example.isaProject.dto.LocationDto;
import com.example.isaProject.dto.MessageDto;
import com.example.isaProject.model.Location;
import com.example.isaProject.model.Message;
import com.example.isaProject.model.User;
import com.example.isaProject.securityAuth.TokenBasedAuthentication;
import com.example.isaProject.service.LocationService;
import com.example.isaProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/locations", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationDto> create(@RequestBody LocationDto locationDto){
        Location location = locationService.create(locationDto);
        if(location == null){
            return new ResponseEntity<LocationDto>(HttpStatus.BAD_REQUEST);
        }
        LocationDto dto = new LocationDto(location);
        return new ResponseEntity<LocationDto>(dto, HttpStatus.OK);
    }
}
