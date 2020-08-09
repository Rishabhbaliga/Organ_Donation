package com.example.organ_donation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.organ_donation.Logins.Admin_Login;
import com.example.organ_donation.Logins.Doctor_Login;
import com.example.organ_donation.Logins.Donor_Login;

public class landing extends AppCompatActivity {

    RadioGroup rg;
    Button go;
    String radiobtn;
    int selectedId;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        go = (Button) findViewById(R.id.landbtn);

        builder = new AlertDialog.Builder(this);

        rg = (RadioGroup) findViewById(R.id.rg);


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    selectedId = rg.getCheckedRadioButtonId();

                    RadioButton radioButton = (RadioButton) findViewById(selectedId);
                    radiobtn = radioButton.getText().toString();

                    if (radiobtn.equals("Patient")) {
                        Intent pat = new Intent(landing.this, Patient_Login.class);
                        startActivity(pat);
                    } else if (radiobtn.equals("Doctor")) {
                        Intent doc = new Intent(landing.this, Doctor_Login.class);
                        startActivity(doc);
                    } else if (radiobtn.equals("Donor")) {
                        Intent don = new Intent(landing.this, Donor_Login.class);
                        startActivity(don);
                    } else if (radiobtn.equals("Admin")){
                        Intent adm = new Intent(landing.this, Admin_Login.class);
                        startActivity(adm);
                    }
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
            }
        });
    }
}


