package com.wjaronski.tiwprproject1.controller;

import com.wjaronski.tiwprproject1.model.Order;
import com.wjaronski.tiwprproject1.model.OrderResource;
import com.wjaronski.tiwprproject1.model.ShortMeal;
import com.wjaronski.tiwprproject1.resourceProcessors.OrderResourceProcessor;
import com.wjaronski.tiwprproject1.resourceProcessors.ShortMealResourceProcessor;
import com.wjaronski.tiwprproject1.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.stream.Collectors;

import static com.wjaronski.tiwprproject1.controller.AbstractRestController.HAL_JSON;

@RestController
@RequestMapping(value = "/orders", produces = HAL_JSON)
@AllArgsConstructor
public class OrderController extends AbstractRestController {

    private OrderService service;

    private OrderResourceProcessor orderProcessor;
    private ShortMealResourceProcessor mealProcessor;

    @GetMapping
    public ResponseEntity<Resources<Resource<Order>>> all() {
        return ResponseEntity.ok(
                wrapToResources(
                        service.findAll(),
                        orderProcessor)
        );
    }

//    @GetMapping("/{uuid}")
//    public ResponseEntity<Resource<Order>> findOne(@PathVariable UUID uuid) {
//        return ResponseEntity.ok(
//                processResource(
//                        service.findOne(uuid),
//                        orderProcessor
//                )
//        );
//    }

    @GetMapping("/t/{uuid}")
    public Order findOneT(@PathVariable UUID uuid) {
        return service.findOne(uuid);
//        return ResponseEntity.ok(
//                processResource(
//                        service.findOne(uuid),
//                        orderProcessor
//                )
//        );
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<OrderResource> findOne(@PathVariable UUID uuid) {
        Order o = service.findOne(uuid);
        return ResponseEntity.ok(
                OrderResource.builder()
                        .orderNum(o.getOrderNum())
                        .meals(
                                wrapToResources(
                                        o.getMeals()
                                                .stream()
                                                .map(ShortMeal::new)
                                                .collect(Collectors.toList()),
                                        mealProcessor)
                                        .getContent())
                        .build());
    }

//    @GetMapping("/{uuid}/meals")
//    public ResponseEntity<Resources<Resource<Meal>>> getOrderMeals(@PathVariable UUID uuid) throws URISyntaxException {
//
//
//        return ResponseEntity.ok(
//                wrapToResources(
//                        service.findOne(uuid).getMeals(),
//                        mealProcessor
//                )
//        );
//    }

}
