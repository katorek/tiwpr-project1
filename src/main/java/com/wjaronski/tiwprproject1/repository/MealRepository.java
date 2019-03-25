package com.wjaronski.tiwprproject1.repository;

import com.wjaronski.tiwprproject1.model.Meal;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Wojciech Jaronski
 */
public interface MealRepository extends CrudRepository<Meal, Integer> {
}
