package com.example.isaProject.serviceImpl;

import com.example.isaProject.dto.LocationDto;
import com.example.isaProject.dto.MessageDto;
import com.example.isaProject.model.Location;
import com.example.isaProject.model.Message;
import com.example.isaProject.model.User;
import com.example.isaProject.repository.LocationRepository;
import com.example.isaProject.repository.MessageRepository;
import com.example.isaProject.repository.UserRepository;
import com.example.isaProject.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;


    public Location create(LocationDto locationDto){

        Location location = new Location();
        location.setAddress(locationDto.getAddress());
        location.setLongitude(locationDto.getLongitude());
        location.setLatitude(locationDto.getLatitude());

        locationRepository.save(location);
        return location;

    }
}
