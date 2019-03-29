package com.wjaronski.tiwprproject1.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Table(name = "Orders")
@Entity(name = "Orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
public class Order {

    @Id
    @GeneratedValue
    private Integer id;

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "order_num", updatable = false, nullable = false)
    private UUID orderNum;

//    @ManyToMany
//    @JoinTable(name = "meals_order",
//            joinColumns = @JoinColumn(name = "orders_id"),
//            inverseJoinColumns = @JoinColumn(name = "meals_id"))
    @OneToMany
//    @JsonBackReference
    @JsonInclude
    private List<Meal> content;

}
