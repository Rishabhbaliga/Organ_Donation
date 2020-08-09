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

import androidx.appcompat.app.AppCompatActivity;

import com.example.organ_donation.Admin_Pannel;
import com.example.organ_donation.R;
import com.example.organ_donation.Register_Handle.Doctors_handle;
import com.example.organ_donation.db;
import com.example.organ_donation.tables.DonorTable;
import com.example.organ_donation.tables.DonorTable_admin;

import java.util.ArrayList;

public class All_Donors extends AppCompatActivity {

    ListView donList;
    SQLiteDatabase db;

    ArrayList<String> donorlist, item;
    ArrayAdapter adapter;
    Button back;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__donors);

        db = new db(this).getWritableDatabase();

        donList = findViewById(R.id.donorList);
        back = (Button) findViewById(R.id.backDonor);

        item = new ArrayList<>();

        donList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                Intent disp = new Intent(All_Donors.this, DonorTable_admin.class);
                disp.putExtra("name", selectedItem);
                startActivity(disp);
            }
        });

        donorlist = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from donation where status = 'Yes';", null);
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data in Database", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                donorlist.add(cursor.getString(1));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, donorlist);
            donList.setAdapter(adapter);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(All_Donors.this, Admin_Pannel.class);
                startActivity(back);
                finish();
            }
        });
    }
}

