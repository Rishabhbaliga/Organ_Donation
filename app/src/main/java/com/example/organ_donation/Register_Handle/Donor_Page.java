package com.example.organ_donation.Register_Handle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
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

import com.example.organ_donation.R;
import com.example.organ_donation.db;

public class Donor_Page extends AppCompatActivity {

    Button cont;
    String nameStr, ageStr, docnameStr, doccontactStr, placeStr, emergencyStr, dateStr;
    EditText name, age, docname, doccontact, place, date, emergencyContact;
    SQLiteDatabase db;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);

        db=new db(this).getWritableDatabase();

        cont = (Button) findViewById(R.id.submit);

        name = (EditText) findViewById(R.id.editText10);
        age = (EditText) findViewById(R.id.editText8);
        docname = (EditText) findViewById(R.id.editText16);
        doccontact = (EditText) findViewById(R.id.editText15);
        place = (EditText) findViewById(R.id.editText14);
        date = (EditText) findViewById(R.id.editText12);
        emergencyContact = (EditText) findViewById(R.id.Contact_Person_Name);


        builder = new AlertDialog.Builder(this);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameStr = name.getText().toString();
                ageStr = age.getText().toString();
                docnameStr = docname.getText().toString();
                doccontactStr = doccontact.getText().toString();
                placeStr = place.getText().toString();
                emergencyStr = emergencyContact.getText().toString();
                dateStr = date.getText().toString();

                if(nameStr.equals("")||ageStr.equals("")||docnameStr.equals("")||doccontactStr.equals("")||placeStr.equals("") || emergencyStr.equals("") || dateStr.equals("")){
                    builder.setMessage("Enter all the details")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                }
                else {
                    ContentValues values = new ContentValues();
                    values.put("name", nameStr);
                    values.put("age", ageStr);
                    values.put("doc_name", docnameStr);
                    values.put("doc_contact", doccontactStr);
                    values.put("place", placeStr);
                    values.put("date", dateStr);
                    values.put("emergency_contact", emergencyStr);
                    db.insert("donation", null, values);
                    Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
                }

                Intent conti = new Intent(Donor_Page.this, Donor_Page_2.class);
                conti.putExtra("name", nameStr);
                startActivity(conti);
                finish();
            }
        });
    }
}
