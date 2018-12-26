package com.alascoray.ayo.dsapp;

public class list_item {
    private String customerId;
    private String name;
    private String desc;


    public list_item (){

    }

//constructor for listItem for us to be ablr to use an object of it
    public list_item(String customerId, String name, String desc) {
        this.customerId= customerId;
        this.name = name;
        this.desc = desc;
    }

    public String getCustomerId() {
        return customerId;
    }

    //getter for head and desc
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
