package com.example.projects.innStay.strategy;

import com.example.projects.innStay.entity.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class HolidayPricingStrategy implements PricingStrategy{

    private final PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);
        boolean isTodayHoliday = true; // TODO: integrate a API to check if it is holiday or not
        if(isTodayHoliday){
            price = price.multiply(BigDecimal.valueOf(1.25));
        }
        return price;
    }
}
