package com.example.organ_donation;

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

import com.example.organ_donation.Lists.All_Donors;
import com.example.organ_donation.signups.findDonor;
import com.example.organ_donation.tables.DonorTable_admin;

import java.util.ArrayList;

public class patDonor extends AppCompatActivity {

    ListView custlist;
    SQLiteDatabase db;

    ArrayList<String> patientlist, item;
    ArrayAdapter adapter;
    Button back;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pat_donor);

        db = new db(this).getWritableDatabase();

        Intent src1 = getIntent();
        final String src2 = src1.getStringExtra("src");

        Intent doc = getIntent();
        final String src = doc.getStringExtra("src");

        custlist = findViewById(R.id.patDonorList);
        back = (Button) findViewById(R.id.backPat);

        item = new ArrayList<>();

        custlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                Intent disp = new Intent(patDonor.this, DonorTable_admin.class);
                disp.putExtra("name", selectedItem);
                startActivity(disp);
            }
        });

        patientlist = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from donation where status = 'Yes';", null);
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data in Database", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                patientlist.add(cursor.getString(1));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, patientlist);
            custlist.setAdapter(adapter);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (src.equals("findDonor")) {
                    Intent back = new Intent(patDonor.this, findDonor.class);
                    startActivity(back);
                }else if(src2.equals("patientLogin")){
                    Intent back = new Intent(patDonor.this, Admin_Pannel.class);
                    startActivity(back);
                }
                else {
                    Intent back = new Intent(patDonor.this, Admin_Pannel.class);
                    startActivity(back);
                }
            }
        });
    }
}