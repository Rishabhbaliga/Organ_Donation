package com.example.organ_donation.Lists;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.organ_donation.Admin_Pannel;
import com.example.organ_donation.R;
import com.example.organ_donation.db;
import com.example.organ_donation.tables.DoctorTable;
import com.example.organ_donation.tables.PatientTable;

import java.util.ArrayList;


public class All_Patients extends AppCompatActivity {

    ListView patlist;
    SQLiteDatabase db;

    ArrayList<String> patientlist, item;
    ArrayAdapter adapter;
    Button back;
    String text;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__patients);

        builder = new AlertDialog.Builder(this);

        db = new db(this).getWritableDatabase();

        patlist = findViewById(R.id.patientList);
        back = (Button) findViewById(R.id.backPatient);

        item = new ArrayList<>();

        patlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                Intent disp = new Intent(All_Patients.this, PatientTable.class);
                disp.putExtra("name", selectedItem);
                startActivity(disp);
            }
        });

        patientlist = new ArrayList<>();

            String query = "select * from recieve";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() == 0) {
                Toast.makeText(getApplicationContext(), "No Data in Database", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    patientlist.add(cursor.getString(1));
                }
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, patientlist);
                patlist.setAdapter(adapter);
            }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(All_Patients.this, Admin_Pannel.class);
                startActivity(back);
                finish();
            }
        });
    }
}
