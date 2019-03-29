package com.wjaronski.tiwprproject1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Wojciech Jaronski
 */

@Table
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Relation(value="meal", collectionRelation="meals")
public class Meal {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Size(min = 1)
    private String name;

    @NotNull
    private String description;

    @NotNull
    @Min(0)
    private Double weight;

    @NotNull
    @Min(0)
    private Double price;
}
