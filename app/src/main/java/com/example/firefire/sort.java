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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.firefire.FBref.refGrades;
import static com.example.firefire.FBref.refStudents;

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


    /**
     * sorting students by name
     * @param view
     */
    public void byname(View view) {
        Query query = refStudents.orderByChild("name");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                stuList.clear();
                stuValues.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    String st1 = (String) data.getKey();
                    Student stemp = data.getValue(Student.class);
                    stuValues.add(stemp);
                    String st2 = stemp.getName();
                    stuList.add(st1+" "+st2);
                }

                adp = new ArrayAdapter<String>(sort.this,R.layout.support_simple_spinner_dropdown_item, stuList);
                lvs.setAdapter(adp);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }


    /**
     * sorting students by address
     * @param view
     */
    public void byadd(View view) {
        Query query = refStudents.orderByChild("address");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                stuList.clear();
                stuValues.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    String st1 = (String) data.getKey();
                    Student stemp = data.getValue(Student.class);
                    stuValues.add(stemp);
                    String st2 = stemp.getAdress();
                    stuList.add(st1+" "+st2);
                }

                adp = new ArrayAdapter<String>(sort.this,R.layout.support_simple_spinner_dropdown_item, stuList);
                lvs.setAdapter(adp);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }


    /**
     * sorting by grades
     * @param view
     */
    public void bygrade(View view) {
        Query query = refGrades.orderByChild("grade");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            //
            @Override
            public void onDataChange(DataSnapshot dS) {
                GList.clear();
                GValues.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    String st1 = (String) data.getKey();
                    Grade gtemp = data.getValue(Grade.class);
                    GValues.add(gtemp);
                    String st2 = gtemp.getGname();
                    GList.add(st1+" "+st2);
                }

                adp2 = new ArrayAdapter<String>(sort.this,R.layout.support_simple_spinner_dropdown_item,GList);
                lvg.setAdapter(adp2);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }


    /**
     * sorting the grades by subjects
     * @param view
     */
    public void bysub(View view) {
        Query query = refGrades.orderByChild("subject");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            //
            @Override
            public void onDataChange(DataSnapshot dS) {
                GList.clear();
                GValues.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    String st1 = (String) data.getKey();
                    Grade gtemp = data.getValue(Grade.class);
                    GValues.add(gtemp);
                    String st2 = gtemp.getSubject();
                    GList.add(st1+" "+st2);
                }
                adp2 = new ArrayAdapter<String>(sort.this,R.layout.support_simple_spinner_dropdown_item,GList);
                lvg.setAdapter(adp2);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * showing the wanted information
     * @param adapterView
     * @param view
     * @param pos
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        if (adapterView == lvg) {
            adb = new AlertDialog.Builder(this);
            adb.setTitle(GValues.get(pos).getGname());
            final String stgN = GValues.get(pos).getGname();
            sttemp = "Name: " + GValues.get(pos).getGname() + "\n";
            sttemp += "Grade: " + String.valueOf(GValues.get(pos).getGrade()) + " ";
            sttemp += "Quarter: " + String.valueOf(GValues.get(pos).getQuarter()) + " ";
            sttemp += "Subject: " + String.valueOf(GValues.get(pos).getSubject()) + " ";
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
        } else {
            adb = new AlertDialog.Builder(this);
            adb.setTitle(stuValues.get(pos).getName());
            final String ststuN = stuValues.get(pos).getName();
            sttemp = "Name: " + stuValues.get(pos).getName() + "\n";
            sttemp += "Adress: " + String.valueOf(stuValues.get(pos).getAdress()) + " ";
            sttemp += "Num: " + String.valueOf(stuValues.get(pos).getNum()) + " ";
            sttemp += "DName: " + String.valueOf(stuValues.get(pos).getDname()) + " ";
            sttemp += "Dnum: " + String.valueOf(stuValues.get(pos).getDnum()) + " ";
            sttemp += "Mname: " + String.valueOf(stuValues.get(pos).getMname()) + " ";
            sttemp += "Mnum: " + String.valueOf(stuValues.get(pos).getMnum()) + " ";
            sttemp += "Hnum: " + String.valueOf(stuValues.get(pos).getHnum()) + " ";
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
        if (st.equals("main")) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        if (st.equals("info")){
            Intent si = new Intent(this, Info.class);
            startActivity(si);
        }

        if (st.equals("creds")){
            Intent si = new Intent(this, credits.class);
            startActivity(si);
        }
        return true;
    }

}
