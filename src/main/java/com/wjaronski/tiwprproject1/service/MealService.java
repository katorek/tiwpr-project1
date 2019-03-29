package com.wjaronski.tiwprproject1.service;

import com.wjaronski.tiwprproject1.model.Meal;
import com.wjaronski.tiwprproject1.repository.MealRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * Created by Wojciech Jaronski
 */

@Service
public class MealService extends AbstractService<Meal, Integer> {

    private MealRepository repository;

    public MealService(MealRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Meal findOne(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(getEntityNotFoundMessage(Meal.class, id.toString())));
    }
}
