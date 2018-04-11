package com.example.andrewclark.lab5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by andrewclark on 4/10/18.
 */

public class PersonsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);


        ListView listView = (ListView)findViewById(R.id.personListView);

        ArrayList<String> i = (ArrayList) this.getIntent().getExtras().get("Persons");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, i);

        listView.setAdapter(adapter);
    }
}
