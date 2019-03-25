package com.wjaronski.tiwprproject1.service;

import com.wjaronski.tiwprproject1.model.Meal;
import com.wjaronski.tiwprproject1.repository.MealRepository;
import lombok.AllArgsConstructor;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by Wojciech Jaronski
 */

@Service
@AllArgsConstructor
public class MealService extends AbstractService {

    private MealRepository repository;

    public Meal save(Meal meal){
        return repository.save(meal);
    }

    public List<Meal> findAll() {
        return (List<Meal>) repository.findAll();
    }

    public Meal findOne(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException( getEntityNotFoundMessage(Meal.class, id)));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Meal add(Meal meal) {
        return repository.save(meal);
    }
}
