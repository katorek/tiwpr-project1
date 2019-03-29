package com.wjaronski.tiwprproject1.repository;

import com.wjaronski.tiwprproject1.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    Optional<Order> findByOrderNum(UUID uuid);
}
