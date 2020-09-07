package com.demo.customers.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {

    private Long id;
    private String name;
    private int age;
    private boolean active;
}
