package com.wjaronski.tiwprproject1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wjaronski.tiwprproject1.model.Meal;
import com.wjaronski.tiwprproject1.repository.MealRepository;
import com.wjaronski.tiwprproject1.resourceProcessors.MealResourceProcessor;
import com.wjaronski.tiwprproject1.service.MealService;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.nio.charset.Charset;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MealController.class)
@AutoConfigureMockMvc
public class MealControllerTest {
    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private ObjectMapper mapper;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MealService service;

    @MockBean
    private MealResourceProcessor mealProcessor;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    }

    @Test
    public void all() {
        //when

    }

    @Test
    public void getOne() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void add_whenValidMeal_shouldReturn200() throws Exception {
        Meal meal = DataHelper.getValidMeal();

        when(service.add(any())).thenReturn(meal);

        MvcResult result = this.mockMvc.perform(post("/meals")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectToJson(meal)))
                .andExpect(status().isOk())
                .andReturn();


    }

    @SneakyThrows(JsonProcessingException.class)
    private String objectToJson(Object object) {
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }

}