package com.example.organ_donation.Register_Handle;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.organ_donation.R;
import com.example.organ_donation.db;
import com.example.organ_donation.thank_yous.Thank_You_pat;

public class Patient_Page_2 extends AppCompatActivity {

    RadioGroup rg, rg2;
    String radiobtn, radiobtn2;
    int selectedId, selectedId2;
    Button go;
    EditText hospital_contact, hospital_name;
    String bgStr, hospital_ContStr, hosp_nameStr;
    SQLiteDatabase db;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__page_2);

        db=new db(this).getWritableDatabase();

        go = (Button) findViewById(R.id.button6);

        builder = new AlertDialog.Builder(this);

        Intent conti = getIntent();
        final String name = conti.getStringExtra("name");

        hospital_name = (EditText) findViewById(R.id.editTextpat22);
        hospital_contact = (EditText) findViewById(R.id.editTextpat23);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hosp_nameStr = hospital_name.getText().toString();
                hospital_ContStr = hospital_contact.getText().toString();

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

                if(hospital_contact.equals("")){

                }
                else {
                    ContentValues values = new ContentValues();
                    values.put("blood_group", radiobtn);
                    values.put("organ", radiobtn2);
                    values.put("hosp_name", hosp_nameStr);
                    values.put("hosp_contact", hospital_ContStr);
                    values.put("status", "No");
                    String where = "name=?";
                    String[] whereArgs = new String[] {String.valueOf(name)};

                    db.update("recieve", values, where, whereArgs);
                    Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
                }

                Intent conti = new Intent(Patient_Page_2.this, Thank_You_pat.class);
                startActivity(conti);
                finish();
            }
        });
    }
}