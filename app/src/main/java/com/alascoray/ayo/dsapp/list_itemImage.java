package com.alascoray.ayo.dsapp;

public class list_itemImage {
    public String customerId;
    public String imageUri;



    public list_itemImage() {

    }

//constructor for listItem for us to be ablr to use an object of it

    public list_itemImage(String customerId, String imageUri) {
        this.customerId = customerId;
        this.imageUri = imageUri;


    }

    public String getCustomerId(){
        return customerId;
    }

    public String getImageUri() { return imageUri; }
}
