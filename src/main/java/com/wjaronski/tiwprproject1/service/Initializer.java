package com.wjaronski.tiwprproject1.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.wjaronski.tiwprproject1.model.Meal;
import com.wjaronski.tiwprproject1.model.Order;
import com.wjaronski.tiwprproject1.repository.MealRepository;
import com.wjaronski.tiwprproject1.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Created by Wojciech Jaronski
 */

@Component
@Slf4j
public class Initializer {

    public Initializer(MealRepository meals,
                       OrderRepository orders) {
        List<Meal> mealList = (List<Meal>) meals.findAll();

        if(mealList.isEmpty()){
            meals.saveAll(ImmutableList.of(
                    new Meal(1, "Burger", "Opis burgera", 122.3, 10.5),
                    new Meal(2, "Tortilla", "Tortilla pszenna", 100.2, 15.99),
                    new Meal(3, "Frytki", "Małe frytki", 50d, 4d)
            ));
            mealList = (List<Meal>) meals.findAll();
        }
        
        log.info("Initializing ORDERS");
        orders.save(Order.builder()
                .meals(ImmutableList.of(mealList.get(0), mealList.get(1)))
                .orderNum(UUID.randomUUID())
//                .orderNum(UUID.fromString("1"))
                .build());
    }

}
