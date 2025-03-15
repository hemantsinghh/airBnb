package com.example.projects.airBnb.dto;


import com.example.projects.airBnb.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HotelPriceDto {
    private Hotel hotel;
    private Double price;

}
