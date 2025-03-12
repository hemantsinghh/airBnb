package com.example.projects.airBnb.strategy;

import com.example.projects.airBnb.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Inventory inventory);

}
