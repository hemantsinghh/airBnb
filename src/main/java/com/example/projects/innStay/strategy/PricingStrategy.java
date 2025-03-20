package com.example.projects.innStay.strategy;

import com.example.projects.innStay.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Inventory inventory);

}
