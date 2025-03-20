package com.example.projects.innStay.dto;


import com.example.projects.innStay.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HotelPriceDto {
    private Hotel hotel;
    private Double price;

}
