package com.wjaronski.tiwprproject1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Wojciech Jaronski
 */

@Table
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Relation(value = "meal", collectionRelation = "meals")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Meal {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Double avgWeight;
    private Integer energy100kJ;
    private Integer energykJ;
    private Integer energy100kcal;
    private Integer energykcal;
    private Float energyRWS;
    private Double fat100;
    private Double fat;
    private Float fatRWS;
    private Double fatAcid100;
    private Double fatAcid;
    private Float fatAcidRWS;
    private Double fatTrans100;
    private Double fatTrans;
    private Double carbs100;
    private Double carbs;
    private Float carbsRWS;
    private Double carbsSugar100;
    private Double carbsSugar;
    private Float carbsSugarRWS;
    private Double fiber100;
    private Double fiber;
    private Double protein100;
    private Double protein;
    private Float proteinRWS;
    private Double salt100;
    private Double salt;
    private Float saltRWS;

    private String category = null;
}