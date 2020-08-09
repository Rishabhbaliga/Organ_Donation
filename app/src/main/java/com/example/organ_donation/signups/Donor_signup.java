package com.example.organ_donation.signups;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.organ_donation.Logins.Doctor_Login;
import com.example.organ_donation.Logins.Donor_Login;
import com.example.organ_donation.R;
import com.example.organ_donation.db;

public class Donor_signup extends AppCompatActivity {

    Button signup;
    EditText name, age, phno, dob, donid, email, addr, pwrd;
    String namestr, agestr, phstr, dobstr, emailstr, addrstr, donidStr, pwordstr;
    SQLiteDatabase db;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_signup);

        builder = new AlertDialog.Builder(this);

        db=new db(this).getWritableDatabase();

        name = (EditText) findViewById(R.id.editTextd8);
        age = (EditText) findViewById(R.id.editTextd10);
        phno = (EditText) findViewById(R.id.editTextd11);
        dob = (EditText) findViewById(R.id.editTextd12);
        donid = (EditText) findViewById(R.id.editTextd13);
        email = (EditText) findViewById(R.id.editTextd17);
        addr = (EditText) findViewById(R.id.editTextd14);
        pwrd = (EditText) findViewById(R.id.editTextd18);

        signup = (Button) findViewById(R.id.buttonDonor2);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namestr = name.getText().toString();
                phstr = phno.getText().toString();
                emailstr = email.getText().toString();
                agestr = age.getText().toString();
                addrstr = addr.getText().toString();
                donidStr = donid.getText().toString();
                dobstr = dob.getText().toString();
                pwordstr = pwrd.getText().toString();

                if (namestr.equals("") || phstr.equals("") || emailstr.equals("") || pwordstr.equals("") || agestr.equals("") || addrstr.equals("") || donidStr.equals("") || dobstr.equals("")) {
                    builder.setMessage("Enter all the fields")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Incomplete");
                    alert.show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put("name", namestr);
                    values.put("phone", phstr);
                    values.put("email", emailstr);
                    values.put("password", pwordstr);
                    values.put("age", agestr);
                    values.put("address", addrstr);
                    values.put("card_no", donidStr);
                    values.put("dob", dobstr);
                    db.insert("donor", null, values);
                    Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
                    Intent red = new Intent(Donor_signup.this, Donor_Login.class);
                    startActivity(red);
                    finish();
                }
            }
        });
    }
}
