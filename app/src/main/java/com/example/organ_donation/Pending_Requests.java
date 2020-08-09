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

import java.util.ArrayList;

public class Pending_Requests extends AppCompatActivity {

    ListView penList;
    SQLiteDatabase db;

    ArrayList<String> pendingList, item;
    ArrayAdapter adapter;
    Button back;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending__requests);

        db = new db(this).getWritableDatabase();

        penList = findViewById(R.id.pendingList);
        back = (Button) findViewById(R.id.backPending);

        item = new ArrayList<>();

        pendingList = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from recieve where status = 'No';", null);
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data in Database", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                pendingList.add(cursor.getString(1));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pendingList);
            penList.setAdapter(adapter);
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(Pending_Requests.this, Admin_Pannel.class);
                startActivity(back);
            }
        });
    }
}