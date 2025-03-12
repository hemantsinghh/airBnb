package com.example.projects.airBnb.dto;


import com.example.projects.airBnb.entity.Hotel;
import lombok.Data;

@Data
public class HotelPriceDto {
    private Hotel hotel;
    private Double price;

}
