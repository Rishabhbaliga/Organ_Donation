package com.example.organ_donation.thank_yous;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.organ_donation.R;
import com.example.organ_donation.db;
import com.example.organ_donation.landing;

public class ThankYou extends AppCompatActivity {

    TextView id;
    SQLiteDatabase db;
    String donor_id;
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        db=new db(this).getWritableDatabase();

        id = (TextView) findViewById(R.id.textView20);

        home = (Button) findViewById(R.id.button3);

        String querry = "SELECT * FROM donation ORDER BY donor_id DESC LIMIT 1";
        Cursor cursor = db.rawQuery(querry, null);

        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data in Database", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                donor_id =cursor.getString(0);
            }
        }

        id.setText(donor_id);

        Toast.makeText(getApplicationContext(), donor_id, Toast.LENGTH_SHORT).show();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hme = new Intent(ThankYou.this, landing.class);
                startActivity(hme);
            }
        });


    }
}
