package com.example.organ_donation.Register_Handle;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.organ_donation.Logins.Doctor_Login;
import com.example.organ_donation.R;
import com.example.organ_donation.db;
import com.example.organ_donation.tables.DonorTable;

import java.util.ArrayList;

public class Doctors_handle extends AppCompatActivity {

    ListView patlist;
    SQLiteDatabase db;
    ArrayList<String> patientlist, item;
    ArrayAdapter adapter;
    Button back;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_handle);

        db=new db(this).getWritableDatabase();

        patlist = findViewById(R.id.docHandle);
        back = (Button) findViewById(R.id.button7);

        item = new ArrayList<>();

        patlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                Intent disp = new Intent(Doctors_handle.this, DonorTable.class);
                disp.putExtra("name", selectedItem);
                startActivity(disp);
            }
        });

        patientlist = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from donation where status = 'No';", null);
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
                Intent back = new Intent(Doctors_handle.this, Doctor_Login.class);
                startActivity(back);
                finish();
            }
        });
    }
}