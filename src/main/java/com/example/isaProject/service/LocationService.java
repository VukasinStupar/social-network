package com.example.isaProject.service;

import com.example.isaProject.dto.LocationDto;
import com.example.isaProject.model.Location;

public interface LocationService {
    public Location create(LocationDto locationDto);
}
