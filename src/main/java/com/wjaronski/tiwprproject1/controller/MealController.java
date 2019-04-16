package com.wjaronski.tiwprproject1.controller;

import com.wjaronski.tiwprproject1.model.Meal;
import com.wjaronski.tiwprproject1.resourceProcessors.MealResourceProcessor;
import com.wjaronski.tiwprproject1.service.MealService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.wjaronski.tiwprproject1.controller.AbstractRestController.HAL_JSON;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Wojciech Jaronski
 */
@RestController
@RequestMapping(value = "/meals", produces = HAL_JSON)
@AllArgsConstructor
public class MealController extends AbstractRestController {

    private MealService service;

    private MealResourceProcessor mealProcessor;

    @GetMapping
    public ResponseEntity<Resources<Resource<Meal>>> all() {
        return ResponseEntity.ok(
                wrapToResources(
                        service.findAll(),
                        mealProcessor,
                        linkTo(MealController.class).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource<Meal>> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(
                processResource(
                        service.findOne(id),
                        mealProcessor)
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PostMapping
    public ResponseEntity<Resource<Meal>> add(@RequestBody @Valid Meal meal) {
        return ResponseEntity.ok(
                processResource(
                        service.add(meal),
                        mealProcessor)
        );
    }
}

