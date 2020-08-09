package com.example.organ_donation.tables;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.organ_donation.Lists.All_Doctors;
import com.example.organ_donation.R;
import com.example.organ_donation.Register_Handle.Doctors_handle;
import com.example.organ_donation.db;

import java.util.ArrayList;

public class DoctorTable extends AppCompatActivity {

    EditText dName, dPhone, dEmail, dPlace;
    Button ok;
    SQLiteDatabase db;
    ArrayList<String> item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_table);

        Intent disp = getIntent();
        final String name = disp.getStringExtra("name");

        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();

        db = new db(this).getWritableDatabase();

        dName = (EditText) findViewById(R.id.editText22);
        dPhone = (EditText) findViewById(R.id.editText23);
        dEmail = (EditText) findViewById(R.id.editText25);
        dPlace = (EditText) findViewById(R.id.editText26);

        ok = (Button) findViewById(R.id.button8);

        item = new ArrayList<>();

        String query = "select * from doctor where name = '" + name + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data in Database", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                item.add(cursor.getString(0));
                item.add(cursor.getString(1));
                item.add(cursor.getString(2));
                item.add(cursor.getString(6));
            }
            dName.setText(item.get(0));
            dPhone.setText(item.get(1));
            dEmail.setText(item.get(2));
            dPlace.setText(item.get(3));

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        Intent back = new Intent(DoctorTable.this, All_Doctors.class);
                        startActivity(back);
                        finish();
                }
            });
        }
    }
}
