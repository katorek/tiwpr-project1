package com.wjaronski.tiwprproject1.service;

import com.wjaronski.tiwprproject1.model.Order;
import com.wjaronski.tiwprproject1.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by Wojciech Jaronski
 */

@AllArgsConstructor
public abstract class AbstractService<T, ID extends Number> {

    private CrudRepository<T, ID> repository;

    String getEntityNotFoundMessage(Class c, String id) {
        return String.format("Entity %s with given id=%s not found",
                c.getSimpleName(), id);
    }

    public T save(T t){
        return repository.save(t);
    }

    public List<T> findAll() {
        return (List<T>) repository.findAll();
    }

    public void delete(ID id) {
        repository.deleteById(id);
    }

    public T add(T t) {
        return repository.save(t);
    }

}
