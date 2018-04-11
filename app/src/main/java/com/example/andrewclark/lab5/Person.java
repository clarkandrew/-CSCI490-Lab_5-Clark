package com.example.andrewclark.lab5;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by andrewclark on 4/10/18.
 */

@Entity
public class Person {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public int getId() {

        return id;
    }
}
