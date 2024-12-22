package com.example.isaProject.dto;


import com.example.isaProject.model.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    private Long id;

    private String address;

    private double longitude;

    private double latitude;

    public LocationDto(Location location) {
        this.id = location.getId();
        this.address = location.getAddress();
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
    }
}