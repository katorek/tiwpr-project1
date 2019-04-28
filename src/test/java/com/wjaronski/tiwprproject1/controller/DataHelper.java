package com.wjaronski.tiwprproject1.controller;

import com.wjaronski.tiwprproject1.model.Meal;
import lombok.experimental.UtilityClass;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@UtilityClass
public class DataHelper {

    static Meal getValidMeal() {
        return Meal.builder()
                .id(1)
                .name("name")
//                .description("description")
//                .price(12.3)
//                .weight(7.89)
                .build();
    }

    static Resource<Meal> processedMeal() {
        Resource<Meal> resource = new Resource<>(getValidMeal());
        resource.add(linkTo(MealController.class).withRel("meals"));
        resource.add(linkTo(methodOn(MealController.class).getOne(resource.getContent().getId())).withSelfRel());
        return resource;
    }
}
