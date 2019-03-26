package com.wjaronski.tiwprproject1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.wjaronski.tiwprproject1.model.Meal;
import com.wjaronski.tiwprproject1.repository.MealRepository;
import com.wjaronski.tiwprproject1.resourceProcessors.MealResourceProcessor;
import com.wjaronski.tiwprproject1.service.MealService;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @MockBean
    private MealResourceProcessor mealProcessor;

    @MockBean
    private MealRepository repository;

    @MockBean
    private MealService service;

    private MockMvc mockMvc;
    private Meal meal;

    @Before
    public void setUp() {
        when(mealProcessor.process(any())).thenReturn(DataHelper.processedMeal());

        mockMvc =
                MockMvcBuilders.standaloneSetup(new MealController(service, mealProcessor))
                        .setControllerAdvice(new ExceptionConfiguration())
                        .build();
        meal = DataHelper.getValidMeal();
    }

    @Test
    public void all() {
        throw new NotImplementedException();
    }

    @Test
    public void getOne() {
        throw new NotImplementedException();
    }

    @Test
    public void delete() {
        throw new NotImplementedException();
    }

    @Test
    public void add_whenValidMeal_shouldReturn200() throws Exception {
        Meal meal = DataHelper.getValidMeal();

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
                .andExpect(jsonPath("description", is(meal.getDescription())))
                .andExpect(jsonPath("weight", is(meal.getWeight())))
                .andExpect(jsonPath("price", is(meal.getPrice())))
                .andExpect(jsonPath("links.length()", is(2)))
                .andExpect(jsonPath("links[0].rel", is("meals")))
                .andExpect(jsonPath("links[0].href", is("/meals")))
                .andExpect(jsonPath("links[1].rel", is("self")))
                .andExpect(jsonPath("links[1].href", is("/meals/1")))
                .andDo(MockMvcResultHandlers.print()) // to print response in console
        ;
    }

    @SneakyThrows(JsonProcessingException.class)
    private String objectToJson(Object object) {
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }

}