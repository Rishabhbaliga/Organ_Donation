package com.example.organ_donation.signups;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.organ_donation.R;
import com.example.organ_donation.Register_Handle.Patient_Page;
import com.example.organ_donation.patDonor;

public class findDonor extends AppCompatActivity {

    RadioGroup radioGroup;
    Button find;
    String radiobtn;
    int selectedId;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_donor);

        find =(Button) findViewById(R.id.find);

        Intent src = getIntent();
        final String src1 = src.getStringExtra("src");

        builder = new AlertDialog.Builder(this);

        radioGroup = (RadioGroup) findViewById(R.id.patrg);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    selectedId = radioGroup.getCheckedRadioButtonId();

                    RadioButton radioButton = (RadioButton) findViewById(selectedId);
                    radiobtn = radioButton.getText().toString();

                    if (radiobtn.equals("Request Organ")) {
                        Intent pat = new Intent(findDonor.this, Patient_Page.class);
                        Toast.makeText(getApplicationContext(), radiobtn, Toast.LENGTH_SHORT).show();
                        startActivity(pat);
                        finish();
                    } else if (radiobtn.equals("Find Donor")) {
                        if(src1.equals("patientLogin")){
                            Intent doc = new Intent(findDonor.this, patDonor.class);
                            doc.putExtra("src", src1);
                            startActivity(doc);
                            finish();
                        } else{
                            Intent doc = new Intent(findDonor.this, patDonor.class);
                            doc.putExtra("src", "findDonor");
                            startActivity(doc);
                            finish();
                        }
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