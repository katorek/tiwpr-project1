package com.wjaronski.tiwprproject1.controller;

import com.wjaronski.tiwprproject1.model.Meal;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DataHelper {

    static Meal getValidMeal() {
        return Meal.builder()
                .name("Test")
                .description("Test")
                .price(10.0)
                .weight(11.0)
                .build();
    }
}
