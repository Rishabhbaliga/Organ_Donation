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

import com.example.organ_donation.Admin_Pannel;
import com.example.organ_donation.R;
import com.example.organ_donation.Register_Handle.Doctors_handle;
import com.example.organ_donation.db;

import java.util.ArrayList;

public class PatientTable extends AppCompatActivity {

    EditText dName, dOrgan, dGName, dPContact, dPlace, dBGroup, dGContact, dHospName, dHospContact;
    CheckBox approve;
    Button ok;
    SQLiteDatabase db;
    ArrayList<String> item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_table);

        Intent disp = getIntent();
        final String name = disp.getStringExtra("name");

        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();

        db = new db(this).getWritableDatabase();

        dName = (EditText) findViewById(R.id.editText21);
        dOrgan = (EditText) findViewById(R.id.editText31);
        dGName = (EditText) findViewById(R.id.editText32);
        dPContact = (EditText) findViewById(R.id.editText33);
        dPlace = (EditText) findViewById(R.id.editText34);
        dBGroup = (EditText) findViewById(R.id.editText36);
        dGContact = (EditText) findViewById(R.id.editText35);
        dHospName = (EditText) findViewById(R.id.editText37);
        dHospContact = (EditText) findViewById(R.id.editText39);

        approve = (CheckBox) findViewById(R.id.checkBox);

        ok = (Button) findViewById(R.id.buttonpatTable);

        item = new ArrayList<>();

        String query = "select * from recieve where name = '" + name + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data in Database", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                item.add(cursor.getString(1));
                item.add(cursor.getString(3));
                item.add(cursor.getString(4));
                item.add(cursor.getString(5));
                item.add(cursor.getString(6));
                item.add(cursor.getString(7));
                item.add(cursor.getString(8));
                item.add(cursor.getString(9));
                item.add(cursor.getString(10));
            }
            dName.setText(item.get(0));
            dOrgan.setText(item.get(1));
            dGName.setText(item.get(2));
            dPContact.setText(item.get(3));
            dPlace.setText(item.get(4));
            dBGroup.setText(item.get(5));
            dGContact.setText(item.get(6));
            dHospName.setText(item.get(7));
            dHospContact.setText(item.get(8));


            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        Intent back = new Intent(PatientTable.this, Admin_Pannel.class);
                        startActivity(back);
                        finish();
                }
            });
        }
    }
}
