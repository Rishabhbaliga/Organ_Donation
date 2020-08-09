package com.example.organ_donation;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.organ_donation.Lists.All_Doctors;
import com.example.organ_donation.Lists.All_Donors;
import com.example.organ_donation.Lists.All_Patients;
import com.example.organ_donation.Logins.Doctor_Login;
import com.example.organ_donation.Logins.Donor_Login;
import com.example.organ_donation.Register_Handle.Doctors_handle;
import com.example.organ_donation.Register_Handle.Donor_Page;
import com.example.organ_donation.Register_Handle.Donor_Page_2;
import com.example.organ_donation.Register_Handle.Patient_Page;
import com.example.organ_donation.Register_Handle.Patient_Page_2;
import com.example.organ_donation.signups.Doctor_signup;
import com.example.organ_donation.signups.Donor_signup;
import com.example.organ_donation.signups.patient_sign_up;
import com.example.organ_donation.tables.DoctorTable;
import com.example.organ_donation.tables.DonorTable;
import com.example.organ_donation.tables.DonorTable_admin;
import com.example.organ_donation.tables.PatientTable;
import com.example.organ_donation.thank_yous.ThankYou;
import com.example.organ_donation.thank_yous.Thank_You_pat;

public class db extends SQLiteOpenHelper {
    public static String DB_NAME = "organ_donation";
    public db(patient_sign_up context) {
        super(context, DB_NAME, null, 1);
    }
    public db(Patient_Login context) {
        super(context, DB_NAME, null, 1);
    }
    public db(Doctor_Login context) {
        super(context, DB_NAME, null, 1);
    }
    public db(Donor_Login context) {
        super(context, DB_NAME, null, 1);
    }
    public db(Donor_Page context) {
        super(context, DB_NAME, null, 1);
    }
    public db(Donor_Page_2 context) {
        super(context, DB_NAME, null, 1);
    }
    public db(ThankYou context) {
        super(context, DB_NAME, null, 1);
    }
    public db(All_Donors context) {
        super(context, DB_NAME, null, 1);
    }
    public db(All_Patients context) {
        super(context, DB_NAME, null, 1);
    }
    public db(All_Doctors context) {
        super(context, DB_NAME, null, 1);
    }
    public db(Pending_Requests context) {
        super(context, DB_NAME, null, 1);
    }
    public db(Donor_signup context) {
        super(context, DB_NAME, null, 1);
    }
    public db(Patient_Page context) {
        super(context, DB_NAME, null, 1);
    }
    public db(Patient_Page_2 context) {
        super(context, DB_NAME, null, 1);
    }
    public db(Thank_You_pat context) {
        super(context, DB_NAME, null, 1);
    }
    public db(patDonor context) {
        super(context, DB_NAME, null, 1);
    }
    public db(Doctor_signup context) {
        super(context, DB_NAME, null, 1);
    }
    public db(Doctors_handle context) {
        super(context, DB_NAME, null, 1);
    }
    public db(DonorTable context) {
        super(context, DB_NAME, null, 1);
    }
    public db(DonorTable_admin context) {
        super(context, DB_NAME, null, 1);
    }
    public db(DoctorTable context) {
        super(context, DB_NAME, null, 1);
    }
    public db(PatientTable context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table donor(name varchar(30), phone varchar(30), email varchar(50), password varchar(30), age int, address varchar(60), card_no varchar(10), dob varchar(10))");
        db.execSQL("create table doctor(name varchar(30), phone varchar(30), email varchar(50), password varchar(30), age int, address varchar(60), card_no varchar(10), dob varchar(10))");
        db.execSQL("create table patient(name varchar(30), phone varchar(30), email varchar(50), password varchar(30), age int, address varchar(60), card_no varchar(10), dob varchar(10))");
        db.execSQL("create table donation(donor_id INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(30), age int, blood_group varchar(10), doc_name varchar(30), doc_contact varchar(20), place varchar(50), donating_organ varchar(20), date varchar(10), emergency_contact varchar(20), status varchar(10))");
        db.execSQL("create table recieve(patient_id INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(30), age int, organ varchar(10), guard_name varchar(30), pat_contact varchar(15), place varchar(50), blood_group varchar(10), guard_contact varchar(15), hosp_name varchar(40), hosp_contact varchar(10), status varchar(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}