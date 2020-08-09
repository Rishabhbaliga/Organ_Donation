package com.example.organ_donation.Logins;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.organ_donation.Admin_Pannel;
import com.example.organ_donation.R;
import com.example.organ_donation.signups.patient_sign_up;

public class Admin_Login extends AppCompatActivity {

    Button login;
    EditText uName, pWord;
    TextView fPword, creds;
    String struName, strpword;
    AlertDialog.Builder builder;
    String [] usernames = {"admin1", "admin2", "admin3"};
    String [] passwords = {"orgadmin1", "adminorg2", "AdminOrg3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__login);

        builder = new AlertDialog.Builder(this);

        login =(Button)findViewById(R.id.button);

        uName =(EditText)findViewById(R.id.editText3);
        pWord =(EditText)findViewById(R.id.password4);

        fPword = (TextView) findViewById(R.id.textView8);
        creds = (TextView) findViewById(R.id.creds);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    struName = uName.getText().toString();
                    strpword = pWord.getText().toString();

                    int i, j;
                    for(i=0; i<usernames.length; i++){
                    if (struName.equals(usernames[i]) && strpword.equals(passwords[i])) {
                        Intent intent = new Intent(Admin_Login.this, Admin_Pannel.class);
                        startActivity(intent);
                        finish();
                    } else {
                            creds.setVisibility(View.VISIBLE);
                            uName.setText("");
                            pWord.setText("");
                        }
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
                Intent signup = new Intent(Admin_Login.this, patient_sign_up.class);
                signup.putExtra("src", "admin");
                startActivity(signup);
                finish();
            }
        });
    }
}
