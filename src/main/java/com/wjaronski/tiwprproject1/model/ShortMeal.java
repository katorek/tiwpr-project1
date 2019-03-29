package com.wjaronski.tiwprproject1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortMeal {
    @JsonIgnore
    private Integer id;
    private String name;

    public ShortMeal(Meal meal){
        id = meal.getId();
        name = meal.getName();
    }
}
