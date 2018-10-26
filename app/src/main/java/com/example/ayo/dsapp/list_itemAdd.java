package com.example.ayo.dsapp;

public class list_itemAdd {
    public String customerId;
    public String desc;
    public String name;


    public list_itemAdd() {

    }

//constructor for listItem for us to be ablr to use an object of it

    public list_itemAdd( String customerId, String desc, String name) {
        this.customerId = customerId;
        this.desc = desc;
        this.name = name;

    }

    public String getCustomerId(){
        return customerId;
    }



    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }


}
