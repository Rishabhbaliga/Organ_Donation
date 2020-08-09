package com.example.organ_donation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.organ_donation.Lists.All_Doctors;
import com.example.organ_donation.Lists.All_Donors;
import com.example.organ_donation.Lists.All_Patients;
import com.example.organ_donation.Logins.Admin_Login;
import com.example.organ_donation.Logins.Doctor_Login;
import com.example.organ_donation.Logins.Donor_Login;
import com.example.organ_donation.Register_Handle.Doctors_handle;

public class Admin_Pannel extends AppCompatActivity {

    RadioGroup rg;
    Button check;
    String radiobtn;
    int selectedId;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__pannel);

        builder = new AlertDialog.Builder(this);

        check = (Button) findViewById(R.id.button4);
        rg = (RadioGroup) findViewById(R.id.panelrg);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    selectedId = rg.getCheckedRadioButtonId();

                    RadioButton radioButton = (RadioButton) findViewById(selectedId);
                    radiobtn = radioButton.getText().toString();

                    if (radiobtn.equals("All Donors")) {
                        Intent pat = new Intent(Admin_Pannel.this, All_Donors.class);
                        startActivity(pat);
                    } else if (radiobtn.equals("All Doctors")) {
                        Intent doc = new Intent(Admin_Pannel.this, All_Doctors.class);
                        startActivity(doc);
                    } else if (radiobtn.equals("All Patients")) {
                        Intent don = new Intent(Admin_Pannel.this, All_Patients.class);
                        startActivity(don);
                    } else if (radiobtn.equals("All Requests")){
                        Intent adm = new Intent(Admin_Pannel.this, Doctors_handle.class);
                        startActivity(adm);
                    }
                }catch (Exception e){
                    builder.setMessage("Select Action")
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
            }
        });
    }
}
