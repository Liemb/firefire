package com.example.firefire;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.firefire.FBref.refGrades;
import static com.example.firefire.FBref.refStudents;

public class Info extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lvg, lvs;
    ArrayList<String> stuList = new ArrayList<String>();
    ArrayList<Student> stuValues = new ArrayList<Student>();
    ArrayList<String> GList = new ArrayList<String>();
    ArrayList<Grade> GValues = new ArrayList<Grade>();
    String st1, st2, sttemp;
    ArrayAdapter adp,adp2;
    AlertDialog.Builder adb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        lvg = (ListView) findViewById(R.id.lvg);
        lvs = (ListView) findViewById(R.id.lvs);

        lvg.setOnItemClickListener(this);
        lvg.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lvs.setOnItemClickListener(this);
        lvs.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ValueEventListener GListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dS) {
                GList.clear();
                GValues.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    st1 = (String) data.getKey();
                    Grade GTmp = data.getValue(Grade.class);
                    GValues.add(GTmp);
                    st2 = GTmp.getGname();
                    GList.add(st1+" "+st2);
                }
                adp2 = new ArrayAdapter<String>(Info.this,R.layout.support_simple_spinner_dropdown_item, GList);
                lvg.setAdapter(adp2);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        };
        refGrades.addValueEventListener(GListener);

        ValueEventListener stuListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                stuList.clear();
                stuValues.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    st1 = (String) data.getKey();
                    Student stuTmp = data.getValue(Student.class);
                    stuValues.add(stuTmp);
                    st2 = stuTmp.getName();
                    stuList.add(st1+" "+st2);
                }
                adp = new ArrayAdapter<String>(Info.this,R.layout.support_simple_spinner_dropdown_item, stuList);
                lvs.setAdapter(adp);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        };
        refStudents.addValueEventListener(stuListener);




    }



    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Showing the information
     * tap to delete
     * @param adapterView
     * @param view
     * @param pos
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view,final int pos, long id) {
        if(adapterView==lvs){
            adb = new AlertDialog.Builder(this);
            adb.setTitle(stuValues.get(pos).getName());
            final String ststuN=stuValues.get(pos).getName();
            sttemp="Name: "+stuValues.get(pos).getName()+"\n";
            sttemp+="Adress: "+String.valueOf(stuValues.get(pos).getAdress())+" ";
            sttemp+="Phone: "+String.valueOf(stuValues.get(pos).getNum())+" ";
            sttemp+="Fnum: "+String.valueOf(stuValues.get(pos).getDname())+" ";
            sttemp+="Dnum: "+String.valueOf(stuValues.get(pos).getDnum())+" ";
            sttemp+="Mname: "+String.valueOf(stuValues.get(pos).getMname())+" ";
            sttemp+="Mnum: "+String.valueOf(stuValues.get(pos).getMnum())+" ";
            sttemp+="Hnum: "+String.valueOf(stuValues.get(pos).getHnum())+" ";
            adb.setMessage(sttemp);
            adb.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    refStudents.child(ststuN).removeValue();
                }
            });

            adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog ad = adb.create();
            ad.show();
        }

        else{
            adb = new AlertDialog.Builder(this);
            adb.setTitle(GValues.get(pos).getGname());
            final String stgN=GValues.get(pos).getGname();
            sttemp="Name: "+GValues.get(pos).getGname()+"\n";
            sttemp+="Grade: "+String.valueOf(GValues.get(pos).getGrade())+" ";
            sttemp+="Quarter: "+String.valueOf(GValues.get(pos).getQuarter())+" ";
            sttemp+="Subject: "+String.valueOf(GValues.get(pos).getSubject())+" ";
            adb.setMessage(sttemp);
            adb.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    refGrades.child(stgN).removeValue();
                }
            });

            adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog ad = adb.create();
            ad.show();


        }


        }


    /**
     * inflating the menu
      * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        String st = item.getTitle().toString();
        if (st.equals("main")){
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        if(st.equals("sort")){
            Intent si = new Intent(this, sort.class);
            startActivity(si);
        }
        if (st.equals("credits")){
            Intent si = new Intent(this, credits.class);
            startActivity(si);
        }
        return true;
    }



}