package com.yang.test;


import org.springframework.stereotype.Component;

@Component
public class Demo01 {


    public void add() {
        System.out.println("添加联系人。。。。。");
    }

    public void delete() {
        System.out.println("删除联系人。。。。。");
    }


    public void update() {
        System.out.println("修改联系人。。。。。");
    }

    public void query() {
        System.out.println("查询联系人。。。。。");
    }
}
