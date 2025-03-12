package com.example.projects.airBnb.strategy;


import com.example.projects.airBnb.entity.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
}
