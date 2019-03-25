package com.wjaronski.tiwprproject1.controller;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.Resources;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wojciech Jaronski
 */

public class AbstractRestController {

    public static final String HAL_JSON = "application/hal+json;charset=UTF-8";
    public static final String JSON = "application/json;charset=UTF-8";

    public static final String SLASH = "/";

    protected <T> Resources<Resource<T>> wrapToResources(Iterable<T> data,
                                                         ResourceProcessor<Resource<T>> processor,
                                                         Link... links) {
        final List<Resource<T>> list = new LinkedList<>();

        data.forEach(t -> list.add(processResource(t, processor)));

        return new Resources<>(list, links);
    }

    protected <T> Resource<T> processResource(T data,
                                              ResourceProcessor<Resource<T>> processor){
        return processor.process(new Resource<>(data));
    }
}
