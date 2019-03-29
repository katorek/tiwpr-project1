package com.wjaronski.tiwprproject1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wjaronski.tiwprproject1.resourceProcessors.MealResourceProcessor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResource extends ResourceSupport {



    private UUID orderNum;
    private Collection<Resource<ShortMeal>> meals;
    @JsonIgnore
    private List<Meal> mealsList = new ArrayList<>();

    public OrderResource(Order order) {
        orderNum = order.getOrderNum();
        mealsList = order.getMeals();
    }
}
