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
import com.example.organ_donation.R;
import com.example.organ_donation.db;

public class Doctor_signup extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6,t7;
    Button signup;
    EditText Name, phno, email, age, addr, dob, docid, pwrd;
    String namestr, agestr, phstr, dobstr, emailstr, addrstr, docidStr, pwordstr;
    SQLiteDatabase db;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup);

        builder = new AlertDialog.Builder(this);

        db=new db(this).getWritableDatabase();

        t1=(TextView)findViewById(R.id.textView);
        t2=(TextView)findViewById(R.id.textView2);
        t3=(TextView)findViewById(R.id.textView4);
        t4=(TextView)findViewById(R.id.textView5);
        t5=(TextView)findViewById(R.id.textView6);
        t6=(TextView)findViewById(R.id.textView7);
        t7=(TextView)findViewById(R.id.textView8);

        Name =(EditText)findViewById(R.id.editText);
        phno =(EditText)findViewById(R.id.editText2);
        email =(EditText)findViewById(R.id.editText3);
        age =(EditText)findViewById(R.id.editText4);
        addr =(EditText)findViewById(R.id.editText5);
        dob =(EditText)findViewById(R.id.editText6);
        docid =(EditText)findViewById(R.id.editText7);
        pwrd = (EditText) findViewById(R.id.editText19);

        signup =(Button)findViewById(R.id.button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namestr = Name.getText().toString();
                phstr = phno.getText().toString();
                emailstr = email.getText().toString();
                agestr = age.getText().toString();
                addrstr = addr.getText().toString();
                docidStr = docid.getText().toString();
                dobstr = dob.getText().toString();
                pwordstr = pwrd.getText().toString();

                if (namestr.equals("") || phstr.equals("") || emailstr.equals("") || pwordstr.equals("") || agestr.equals("") || addrstr.equals("") || docidStr.equals("") || dobstr.equals("")) {
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
                    values.put("card_no", docidStr);
                    values.put("dob", dobstr);
                    db.insert("doctor", null, values);
                    Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
                    Intent red = new Intent(Doctor_signup.this, Doctor_Login.class);
                    startActivity(red);
                    finish();
                }
            }
        });
    }
}
