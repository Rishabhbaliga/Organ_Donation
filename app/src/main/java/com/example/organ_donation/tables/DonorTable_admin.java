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
import com.example.organ_donation.patDonor;
import com.example.organ_donation.signups.findDonor;

import java.util.ArrayList;

public class DonorTable_admin extends AppCompatActivity {

    EditText dName, dBGroup, dPlace, dOrgan, dDate, dContact, dStatus;
    CheckBox approve;
    Button ok;
    SQLiteDatabase db;
    ArrayList<String> item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_table_admin);

        Intent disp = getIntent();
        final String name = disp.getStringExtra("name");

        Intent doc = getIntent();
        final String src = doc.getStringExtra("src");

        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();

        db = new db(this).getWritableDatabase();

        dName = (EditText) findViewById(R.id.editText22);
        dBGroup = (EditText) findViewById(R.id.editText23);
        dPlace = (EditText) findViewById(R.id.editText25);
        dOrgan = (EditText) findViewById(R.id.editText26);
        dDate = (EditText) findViewById(R.id.editText27);
        dContact = (EditText) findViewById(R.id.editText28);
        dStatus = (EditText) findViewById(R.id.editText29);

        approve = (CheckBox) findViewById(R.id.checkBox);

        ok = (Button) findViewById(R.id.button8);

        item = new ArrayList<>();

        String query = "select * from donation where name = '" + name + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data in Database", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                item.add(cursor.getString(1));
                item.add(cursor.getString(3));
                item.add(cursor.getString(6));
                item.add(cursor.getString(7));
                item.add(cursor.getString(8));
                item.add(cursor.getString(9));
                item.add(cursor.getString(10));
            }
            dName.setText(item.get(0));
            dBGroup.setText(item.get(1));
            dPlace.setText(item.get(2));
            dOrgan.setText(item.get(3));
            dDate.setText(item.get(4));
            dContact.setText(item.get(5));
            dStatus.setText(item.get(6));

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        Intent back = new Intent(DonorTable_admin.this, Admin_Pannel.class);
                        startActivity(back);
                        finish();
                }
            });
        }
    }
}
