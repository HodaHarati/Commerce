package com.example.commerce.model.product;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CartProduct extends RealmObject {

    @PrimaryKey
    private int id;
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
