package com.example.organ_donation.Logins;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.organ_donation.R;
import com.example.organ_donation.Register_Handle.Donor_Page;
import com.example.organ_donation.db;
import com.example.organ_donation.signups.Donor_signup;

import java.util.ArrayList;

public class Donor_Login extends AppCompatActivity {

    Button login;
    EditText uName, pWord;
    TextView fPword, check;
    String struName, strpword;
    AlertDialog.Builder builder;
    SQLiteDatabase db;
    ArrayList<String> logindetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor__login);
        db=new db(this).getWritableDatabase();
        builder = new AlertDialog.Builder(this);

        login =(Button)findViewById(R.id.button);

        uName =(EditText)findViewById(R.id.editText3);
        pWord =(EditText)findViewById(R.id.password);

        fPword = (TextView) findViewById(R.id.textView8);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    struName = uName.getText().toString();
                    strpword = pWord.getText().toString();
                    if(!struName.equals("")&&!strpword.equals("")) {
                        logindetails = new ArrayList<>();

                        String query = "select * from donor where name='" + struName + "'";
                        Cursor cursor = db.rawQuery(query, null);

                        if (cursor.getCount() == 0) {
                            Toast.makeText(getApplicationContext(), "No Data in Database", Toast.LENGTH_SHORT).show();
                        } else {
                            while (cursor.moveToNext()) {
                                logindetails.add(cursor.getString(0));
                                logindetails.add(cursor.getString(3));
                            }
                        }

                        String uname1 = logindetails.get(0);
                        String pword1 = logindetails.get(1);

                        if (struName.equals(uname1) && strpword.equals(pword1)) {
                            Intent i = new Intent(Donor_Login.this, Donor_Page.class);
                            startActivity(i);
                            finish();
                        } else {
                            builder.setMessage("User Name and password dont match")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                            //Creating dialog box
                            AlertDialog alert = builder.create();
                            //Setting the title manually
                            alert.setTitle("Wrong username or password");
                            alert.show();

                            uName.setText("");
                            pWord.setText("");
                        }
                    }
                    else{
                        builder.setMessage("Enter username and password")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.setTitle("No input");
                        alert.show();
                    }
                } catch (Exception e) {
                    builder.setMessage("Enter valid username and password")
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
            }
        });


        fPword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(Donor_Login.this, Donor_signup.class);
                signup.putExtra("src", "donor");
                startActivity(signup);
                finish();
            }
        });
    }
}
