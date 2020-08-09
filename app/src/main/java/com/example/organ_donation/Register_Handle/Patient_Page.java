package com.example.organ_donation.Register_Handle;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.organ_donation.R;
import com.example.organ_donation.db;

public class Patient_Page extends AppCompatActivity {

    Button cont;
    String organStr, nameStr, ageStr, guardStr, patContStr, placrStr, gurard_contStr;
    EditText name, age, guard_name, pat_contact, place, guard_Contact;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__page);

        db = new db(this).getWritableDatabase();

        cont = (Button) findViewById(R.id.button5);

        name = (EditText) findViewById(R.id.editText11);
        age = (EditText) findViewById(R.id.editText13);
        guard_name = (EditText) findViewById(R.id.editText17);
        pat_contact = (EditText) findViewById(R.id.editText20);
        place = (EditText) findViewById(R.id.editText18);
        guard_Contact = (EditText) findViewById(R.id.editTextpat);


        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameStr = name.getText().toString();
                ageStr = age.getText().toString();
                guardStr = guard_name.getText().toString();
                patContStr = pat_contact.getText().toString();
                placrStr = place.getText().toString();
                gurard_contStr = guard_Contact.getText().toString();

                if (nameStr.equals("") || ageStr.equals("") || guardStr.equals("") || patContStr.equals("") || placrStr.equals("")) {

                } else {
                    ContentValues values = new ContentValues();
                    values.put("name", nameStr);
                    values.put("age", ageStr);
                    values.put("guard_name", guardStr);
                    values.put("pat_contact", patContStr);
                    values.put("place", placrStr);
                    values.put("guard_contact", gurard_contStr);
                    db.insert("recieve", null, values);
                    Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
                }

                Intent conti = new Intent(Patient_Page.this, Patient_Page_2.class);
                conti.putExtra("name", nameStr);
                startActivity(conti);
                finish();
            }
        });
    }
}