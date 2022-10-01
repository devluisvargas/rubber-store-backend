package com.devluis.rubberstore.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "price is required")
    @Min(value = 0, message = "price is not less to 0")
    private Double price;

    private String imgUrl;

    private String imgId;

}
