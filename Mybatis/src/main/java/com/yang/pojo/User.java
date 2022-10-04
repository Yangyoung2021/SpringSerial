package com.yang.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue
    private String id;

    @Column(name = "age")
    private Integer age;

    @Column(name = "name")
    private String name;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "address")
    private String address;
}
