package com.yang.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private int id;

    private String name;

    private Integer grade;

    private String gender;

    private String phone;

    private String apartment;
}
