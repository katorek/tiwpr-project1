package com.wjaronski.tiwprproject1.resourceProcessors;

import com.wjaronski.tiwprproject1.controller.MealController;
import com.wjaronski.tiwprproject1.model.Meal;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Wojciech Jaronski
 */

@Component
public class MealResourceProcessor implements ResourceProcessor<Resource<Meal>> {
    @Override
    public Resource<Meal> process(Resource<Meal> mealResource) {

        mealResource.add(linkTo(MealController.class).withRel("meals"));
        mealResource.add(linkTo(methodOn( MealController.class).getOne(mealResource.getContent().getId())).withSelfRel());
//                add(linkTo(PersonController.class).withRel("people"));
//        add(linkTo(methodOn(GymMembershipController.class).all(id)).withRel("memberships"));
//        add(linkTo(methodOn(PersonController.class).get(id)).withSelfRel());

        return mealResource;
    }
}
