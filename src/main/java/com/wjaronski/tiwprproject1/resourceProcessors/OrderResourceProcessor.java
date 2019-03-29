package com.wjaronski.tiwprproject1.resourceProcessors;

import com.wjaronski.tiwprproject1.controller.MealController;
import com.wjaronski.tiwprproject1.controller.OrderController;
import com.wjaronski.tiwprproject1.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class OrderResourceProcessor implements ResourceProcessor<Resource<Order>> {
    @Override
    public Resource<Order> process(Resource<Order> orderResource) {

        orderResource.add(linkTo(OrderController.class).withRel("orders"));
        orderResource.add(linkTo(methodOn(OrderController.class).findOne(orderResource.getContent().getOrderNum())).withSelfRel());

        orderResource.add(linkTo(methodOn(OrderController.class).getOrderMeals(orderResource.getContent().getOrderNum())).withRel("meals"));

//        Resource<Order> order = new Resources<>()

//        Order o = orderResource.getContent();
//        o.getMeals().forEach(
//                meal -> {
//                    orderResource.add(linkTo(methodOn(MealController.class).getOne(meal.getId())).withRel(meal.getName()));
//                }
//        );

//        orderResource.getContent().getMealSet().forEach(
//                meal -> {
//                    orderResource.add(linkTo(MealController.class).withRel());
//                }
//        );
        return orderResource;
    }
}


/*
@Component
public class MealResourceProcessor implements ResourceProcessor<Resource<Meal>> {
    @Override
    public Resource<Meal> process(Resource<Meal> mealResource) {

        mealResource.add(linkTo(MealController.class).withRel("meals"));
        mealResource.add(linkTo(methodOn( MealController.class).getOne(mealResource.getContent().getId())).withSelfRel());
        return mealResource;
    }
}




* */
