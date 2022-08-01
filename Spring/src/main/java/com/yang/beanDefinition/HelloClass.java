package com.yang.beanDefinition;


import java.util.List;
import java.util.Map;

public class HelloClass {

    private String name;
    private List<String> list;
    private Map<String, String> map;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return this.list;
    }

    public void iterList(List<String> list) {
        for (String s : list) {
            System.out.println(s);
        }
    }


}
