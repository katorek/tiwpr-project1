package com.wjaronski.tiwprproject1.service;

import com.wjaronski.tiwprproject1.model.Order;
import com.wjaronski.tiwprproject1.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class OrderService extends AbstractService<Order, Integer> {

    private OrderRepository repository;

    public OrderService(OrderRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Order findOne(UUID uuid) {
        return repository.findByOrderNum(uuid).orElseThrow(() -> new EntityNotFoundException(getEntityNotFoundMessage(Order.class, uuid.toString())));
    }

}
