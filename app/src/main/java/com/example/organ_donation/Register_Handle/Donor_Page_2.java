package com.example.organ_donation.Register_Handle;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.organ_donation.R;
import com.example.organ_donation.thank_yous.ThankYou;
import com.example.organ_donation.db;

public class Donor_Page_2 extends AppCompatActivity {

    RadioGroup rg, rg2;
    String radiobtn, radiobtn2;
    int selectedId, selectedId2;
    String donor_contact;
    SQLiteDatabase db;
    EditText donorContact;
    Button submit;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor__page_2);

        db=new db(this).getWritableDatabase();

        builder = new AlertDialog.Builder(this);

        Intent conti = getIntent();
        final String name = conti.getStringExtra("name");

        submit = (Button) findViewById(R.id.button2);

        donorContact = (EditText) findViewById(R.id.editText24);

        builder = new AlertDialog.Builder(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donor_contact = donorContact.getText().toString();

                //rb1
                try {
                    rg = (RadioGroup) findViewById(R.id.rg1);
                    selectedId = rg.getCheckedRadioButtonId();

                    RadioButton radioButton = (RadioButton) findViewById(selectedId);
                    radiobtn = radioButton.getText().toString();
                }catch (Exception e){
                    builder.setMessage("Select User")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Error in input");
                    alert.show();
                }

                //rb2
                try {
                    rg2 = (RadioGroup) findViewById(R.id.rg2);
                    selectedId2 = rg2.getCheckedRadioButtonId();

                    RadioButton radioButton2 = (RadioButton) findViewById(selectedId2);
                    radiobtn2 = radioButton2.getText().toString();
                }
                catch (Exception e){
                    builder.setMessage("Select User")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Error in input");
                    alert.show();
                }


                if(donor_contact.equals("")) {
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
                    values.put("donating_organ", radiobtn2);
                    values.put("blood_group", radiobtn);
                    values.put("emergency_contact", donor_contact);
                    values.put("status", "No");

                    String where = "name=?";
                    String[] whereArgs = new String[] {String.valueOf(name)};

                    db.update("donation", values, where, whereArgs);

                    Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
                }

                Intent conti = new Intent(Donor_Page_2.this, ThankYou.class);
                startActivity(conti);
                finish();
            }
        });
    }
}
