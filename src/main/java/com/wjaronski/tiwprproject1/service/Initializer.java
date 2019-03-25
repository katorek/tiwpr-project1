package com.wjaronski.tiwprproject1.service;

import com.wjaronski.tiwprproject1.model.Meal;
import com.wjaronski.tiwprproject1.repository.MealRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Wojciech Jaronski
 */

@Component
@Slf4j
public class Initializer {

    public Initializer(MealRepository repository){
        log.info("Initailizing MEALS");
        repository.save(
                Meal.builder()
                        .name("Burger")
                        .description("Opis burgera")
                        .price(12.5)
                        .weight(150.0)
                .build()
        );
    }
}
