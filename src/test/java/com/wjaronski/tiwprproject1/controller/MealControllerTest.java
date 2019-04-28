package com.wjaronski.tiwprproject1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.collect.ImmutableList;
import com.wjaronski.tiwprproject1.model.Meal;
import com.wjaronski.tiwprproject1.resourceProcessors.MealResourceProcessor;
import com.wjaronski.tiwprproject1.service.MealService;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MealController.class)
public class MealControllerTest {
    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private static final String BASE_PATH = "http://localhost/meals";

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MealService service;

    @MockBean
    private MealResourceProcessor mealProcessor;

    private Meal meal;

    @Before
    public void setUp() {
        when(mealProcessor.process(any())).thenReturn(DataHelper.processedMeal());

        meal = DataHelper.getValidMeal();
    }

    @Test
    public void all() throws Exception {
        when(service.findAll()).thenReturn(ImmutableList.of(meal));

        mockMvc.perform(get(BASE_PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.meals.length()", is(1)))
                .andExpect(jsonPath("_embedded.meals[0].id", is(meal.getId())))
                .andExpect(jsonPath("_embedded.meals[0].name", is(meal.getName())))
        ;
//                .andExpect(jsonPath("_embedded.meals[0].description", is(meal.getDescription())))
//                .andExpect(jsonPath("_embedded.meals[0].weight", is(meal.getWeight())))
//                .andExpect(jsonPath("_embedded.meals[0].price", is(meal.getPrice())));
    }

    @Test
    public void getOne() throws Exception {
        given(service.findOne(meal.getId())).willReturn(meal);

        ResultActions actions = mockMvc.perform(get(BASE_PATH + "/" + meal.getId()));

        verifyJson(actions);
    }

    @Test
    public void deleteReturn() throws Exception {
        mockMvc.perform(delete(BASE_PATH + "/" + meal.getId()))
                .andExpect(status().isOk());

    }

    @Test
    public void add_whenValidMeal_shouldReturn200() throws Exception {
        when(service.add(any())).thenReturn(meal);

        final ResultActions result = this.mockMvc.perform(post(BASE_PATH)
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectToJson(meal)))
                .andExpect(status().isOk());

        verifyJson(result);

    }

    private void verifyJson(final ResultActions action) throws Exception {
        action
                .andExpect(jsonPath("id", is(meal.getId())))
                .andExpect(jsonPath("name", is(meal.getName())))
//                .andExpect(jsonPath("description", is(meal.getDescription())))
//                .andExpect(jsonPath("weight", is(meal.getWeight())))
//                .andExpect(jsonPath("price", is(meal.getPrice())))
                .andExpect(jsonPath("_links.meals.href", is("/meals")))
                .andExpect(jsonPath("_links.self.href", is("/meals/1")))
                .andDo(MockMvcResultHandlers.print()) // to print response in console
        ;
    }

    @SneakyThrows(JsonProcessingException.class)
    private String objectToJson(Object object) {
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }

}