package com.wjaronski.tiwprproject1.service;

import com.google.common.collect.ImmutableList;
import com.wjaronski.tiwprproject1.config.YAMLConfig;
import com.wjaronski.tiwprproject1.model.Meal;
import com.wjaronski.tiwprproject1.model.Order;
import com.wjaronski.tiwprproject1.model.parser.MealParser;
import com.wjaronski.tiwprproject1.repository.MealRepository;
import com.wjaronski.tiwprproject1.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Wojciech Jaronski
 */

@Component
@Slf4j
public class Initializer {


    public Initializer(MealRepository meals,
                       OrderRepository orders,
                       YAMLConfig config) {

        if (config.getFileDataPath() != null) {
            meals.saveAll(loadFromFile(config.getFileDataPath()));
        } else {
            List<Meal> mealList = (List<Meal>) meals.findAll();

            if (mealList.isEmpty()) {
                meals.saveAll(ImmutableList.of(
                        Meal.builder().id(1).name("Burger").category("Opis").build(),
                        Meal.builder().id(2).name("Tortilla").category("kanapka").build(),
                        Meal.builder().id(3).name("Frytki").category("dodatek").build()
                ));
                mealList = (List<Meal>) meals.findAll();
            }
            List<Order> orderList = (List<Order>) orders.findAll();
            if (orderList.isEmpty()) {
                log.info("Initializing ORDERS");
                orders.save(Order.builder()
                        .meals(ImmutableList.of(mealList.get(0), mealList.get(1)))
                        .orderNum(UUID.randomUUID())
                        .build());
            }
        }

    }

    private List<Meal> loadFromFile(String fileDataPath) {
        log.info("Loading data from file: {}", fileDataPath);

        try {
            List<Meal> meals =
                    Files.lines(Paths.get(fileDataPath))
                            .map(MealParser::parseMeal)
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList());
            String category = null;
            for (Meal meal : meals) {
                if (meal.getCategory() == null) meal.setCategory(category);
                else {
                    category = meal.getCategory();
                }
            }

            return meals.stream().filter(meal -> meal.getName() != null).collect(Collectors.toList());
        } catch (IOException e) {
            log.error("{}", e);
        }
        return new ArrayList<>();
    }

}
