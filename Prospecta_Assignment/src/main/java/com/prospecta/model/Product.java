package com.prospecta.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {
	private Integer id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
}
