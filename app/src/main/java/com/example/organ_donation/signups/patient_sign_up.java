package com.example.organ_donation.signups;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.organ_donation.Logins.Doctor_Login;
import com.example.organ_donation.Patient_Login;
import com.example.organ_donation.R;
import com.example.organ_donation.db;

public class patient_sign_up extends AppCompatActivity {

    Button signUP;
    EditText name, phone, email, age, addr, cardno, dob, pword;
    String namestr, phstr, emailstr, agestr, addrstr, cardnostr, dobstr, pwordstr, osrc;
    TextView Name, Phone, Email, Age, Addr, Cardno, Dob, Pword;
    SQLiteDatabase db;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup);

        db = new db(this).getWritableDatabase();

        builder = new AlertDialog.Builder(this);

        final Intent src = getIntent();
        osrc = src.getStringExtra("src");

        name = (EditText) findViewById(R.id.editText);
        phone = (EditText) findViewById(R.id.editText3);
        email = (EditText) findViewById(R.id.editText2);
        age = (EditText) findViewById(R.id.editText4);
        addr = (EditText) findViewById(R.id.editText5);
        cardno = (EditText) findViewById(R.id.editText6);
        dob = (EditText) findViewById(R.id.editText7);
        pword = (EditText) findViewById(R.id.editText9);

        Name = (TextView) findViewById(R.id.textView);
        Phone = (TextView) findViewById(R.id.textView2);
        Email = (TextView) findViewById(R.id.textView3);
        Pword = (TextView) findViewById(R.id.textView9);
        Age = (TextView) findViewById(R.id.textView4);
        Addr = (TextView) findViewById(R.id.textView5);
        Cardno = (TextView) findViewById(R.id.textView6);
        Dob = (TextView) findViewById(R.id.textView7);


        if (osrc.equals("admin")) {
            Name.setEnabled(false);
            Phone.setEnabled(false);
            Email.setEnabled(false);
            Pword.setEnabled(false);
            Age.setEnabled(false);
            Addr.setEnabled(false);
            Cardno.setEnabled(false);
            Dob.setEnabled(false);

            name.setEnabled(false);
            phone.setEnabled(false);
            email.setEnabled(false);
            pword.setEnabled(false);
            age.setEnabled(false);
            addr.setEnabled(false);
            cardno.setEnabled(false);
            dob.setEnabled(false);

            builder.setMessage("Contact IT Department for login credentials")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.setTitle("Admin Sign Up");
            alert.show();
    }
            signUP = (Button) findViewById(R.id.buttonPatient);

            signUP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (osrc.equals("patient")) {
                        namestr = name.getText().toString();
                        phstr = phone.getText().toString();
                        emailstr = email.getText().toString();
                        agestr = age.getText().toString();
                        addrstr = addr.getText().toString();
                        cardnostr = cardno.getText().toString();
                        dobstr = dob.getText().toString();
                        pwordstr = pword.getText().toString();

                        if (namestr.equals("") || phstr.equals("") || emailstr.equals("") || pwordstr.equals("") || agestr.equals("") || addrstr.equals("") || cardnostr.equals("") || dobstr.equals("")) {
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
                            values.put("card_no", cardnostr);
                            values.put("dob", dobstr);
                            db.insert("patient", null, values);
                            Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
                            Intent red = new Intent(patient_sign_up.this, Patient_Login.class);
                            startActivity(red);
                            finish();
                        }
                    } else if (osrc.equals("admin")) {
                        Name.setEnabled(false);
                        Phone.setEnabled(false);
                        Email.setEnabled(false);
                        Pword.setEnabled(false);
                        Age.setEnabled(false);
                        Addr.setEnabled(false);
                        Cardno.setEnabled(false);
                        Dob.setEnabled(false);

                        name.setEnabled(false);
                        phone.setEnabled(false);
                        email.setEnabled(false);
                        pword.setEnabled(false);
                        age.setEnabled(false);
                        addr.setEnabled(false);
                        cardno.setEnabled(false);
                        dob.setEnabled(false);

                        builder.setMessage("Contact IT Department for login credentials")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.setTitle("Admin Sign Up");
                        alert.show();
                    }
                }
            });
    }
}
