package com.example.firefire;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class sort extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lvs, lvg;
    ArrayList<String> stuList = new ArrayList<String>();
    ArrayList<Student> stuValues = new ArrayList<Student>();
    ArrayList<String> GList = new ArrayList<String>();
    ArrayList<Grade> GValues = new ArrayList<Grade>();
    ArrayAdapter adp, adp2;
    AlertDialog.Builder adb;
    String sttemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        lvs = (ListView) findViewById(R.id.lvs);
        lvg = (ListView) findViewById(R.id.lvg);

        lvs.setOnItemClickListener(this);
        lvs.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvg.setOnItemClickListener(this);
        lvg.setChoiceMode(ListView.CHOICE_MODE_SINGLE);



    }
}
