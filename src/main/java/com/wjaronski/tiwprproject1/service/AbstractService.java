package com.wjaronski.tiwprproject1.service;

import org.springframework.stereotype.Component;

/**
 * Created by Wojciech Jaronski
 */

public class AbstractService {

    String getEntityNotFoundMessage(Class c, Integer id) {
        return String.format("Entity %s with given id=%d not found",
                c.getSimpleName(), id);
    }

}
