package com.example.projects.innStay.strategy;


import com.example.projects.innStay.entity.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PricingService {
    public BigDecimal calculateDynamicPricing(Inventory inventory){
        PricingStrategy basePricingStrategy = new BasePricingStrategy();

        basePricingStrategy = new SurgePricingStrategy(basePricingStrategy);
        basePricingStrategy = new OccupancyPricingStrategy(basePricingStrategy);
        basePricingStrategy = new UrgencyPricingStrategy(basePricingStrategy);
        basePricingStrategy = new HolidayPricingStrategy(basePricingStrategy);

        return basePricingStrategy.calculatePrice(inventory);
    }

    //Return the sum of price of the inventory list
    public BigDecimal calculateTotalPrice(List<Inventory> inventoryList){
        return inventoryList.stream()
                .map(this::calculateDynamicPricing)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
