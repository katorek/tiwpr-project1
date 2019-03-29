package com.wjaronski.tiwprproject1.resourceProcessors;

import com.wjaronski.tiwprproject1.controller.MealController;
import com.wjaronski.tiwprproject1.model.ShortMeal;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ShortMealResourceProcessor implements ResourceProcessor<Resource<ShortMeal>> {
    @Override
    public Resource<ShortMeal> process(Resource<ShortMeal> resource) {

        resource.add(linkTo(methodOn( MealController.class).getOne(resource.getContent().getId())).withSelfRel());
        return resource;
    }
}
