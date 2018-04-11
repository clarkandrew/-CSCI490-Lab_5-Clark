package com.example.andrewclark.lab5;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LabDatabase labDB = new LabDatabase() {
        @Override
        public PersonDao personDao() {
            return null;
        }

        @Override
        protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
            return null;
        }

        @Override
        protected InvalidationTracker createInvalidationTracker() {
            return null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView personName = (TextView) findViewById(R.id.personName);

        Button submit = (Button) findViewById(R.id.submit);
        Button viewList = (Button) findViewById(R.id.viewList);



        labDB = Room.databaseBuilder(this, LabDatabase.class, "PersonDatabase").build();

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Person newPerson = new Person();
                newPerson.setName(personName.getText().toString());
                db_insert insertTask = new db_insert();
                insertTask.execute(newPerson);
                Toast.makeText(MainActivity.this, personName.getText().toString() + " added.", Toast.LENGTH_SHORT).show();
            }
        });

        viewList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                db_view viewTask = new db_view(labDB, getApplicationContext());
                viewTask.execute();
            }
        });

    }


    public class db_insert extends AsyncTask<Person, String, String> {
        protected void onPreExecute(){}

        protected String doInBackground(Person... pName) {
            labDB.personDao().insertPerson(pName[0]);


            return "Done";

        }
    }




    public class db_view extends AsyncTask<Void, Void, ArrayList<Person>> {
        Context context;
        LabDatabase labDB;


        public db_view(LabDatabase histDB, Context context) {
            this.labDB = histDB;
            this.context = context;
        }

        @Override
        protected ArrayList<Person> doInBackground(Void... voids) {
            ArrayList<Person> persons = (ArrayList) labDB.personDao().getAllPersons();
            return persons;
        }

        @Override
        protected void onPostExecute(ArrayList<Person> persons) {
            super.onPostExecute(persons);

            ArrayList<String> personNames = new ArrayList<>();
            for(Person p: persons) {
                personNames.add(p.getName());
            }

            Intent i = new Intent(context, PersonsActivity.class);
            i.putExtra("Persons", personNames);
            context.startActivity(i);
        }


    }





}