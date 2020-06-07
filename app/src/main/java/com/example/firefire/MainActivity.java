package com.example.firefire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.net.http.SslCertificate;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import static com.example.firefire.FBref.refGrades;
import static com.example.firefire.FBref.refStudents;

/**
 * @author liem
 * @since 21/05/2020
 */

public class MainActivity extends AppCompatActivity  {

    EditText etNAME, etADD, etNUM, etHNUM, etMNAME, etMNUM, etDNAME, etDNUM, etNAMEG, etQUAR, etGRADE, etSUB;
    String name, address, num, Mname, Dname, Mnum, Dnum, Hnum, Gname, subject, grade, quarter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNAME = (EditText) findViewById(R.id.etNAME);
        etADD = (EditText) findViewById(R.id.etADD);
        etNUM = (EditText) findViewById(R.id.etNUM);
        etHNUM = (EditText) findViewById(R.id.etHnum);
        etMNAME = (EditText) findViewById(R.id.etMNAME);
        etMNUM = (EditText) findViewById(R.id.etMNUM);
        etDNAME = (EditText) findViewById(R.id.etDNAME);
        etDNUM = (EditText) findViewById(R.id.etDNUM);
        etNAMEG = (EditText) findViewById(R.id.etNAMEG);
        etQUAR = (EditText) findViewById(R.id.etQUAR);
        etGRADE = (EditText) findViewById(R.id.etGRADE);
        etSUB = (EditText) findViewById(R.id.etSUB);
    }

    /**
     * Saving the information from te students editTexts on the database
     * @param view
     */

    public void sumS(View view) {
        name = etNAME.getText().toString();
        Mname = etMNAME.getText().toString();
        Dname = etDNAME.getText().toString();
        num = etNUM.getText().toString();
        Mnum = etMNUM.getText().toString();
        Dnum = etDNUM.getText().toString();
        Hnum = etHNUM.getText().toString();
        address = etADD.getText().toString();
        Student S = new Student(name, address, num, Hnum, Dnum, Dname, Mnum, Mname);
        refStudents.child(name).setValue(S);
    }

    /**
     * Saving the information from te grades editTexts on te database
     * @param view
     */

    public void sumG(View view) {
        Gname = etNAMEG.getText().toString();
        subject = etSUB.getText().toString();
        grade = etGRADE.getText().toString();
        quarter = etQUAR.getText().toString();
        Grade G = new Grade(Gname, subject, grade, quarter);
        refGrades.child(Gname).setValue(G);
    }

    /**
     * inflating te menu
     * @param menu
     * @return
     */

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        String st = item.getTitle().toString();

        if (st.equals("info")){
            Intent si = new Intent(this, Info.class);
            startActivity(si);
        }

        if (st.equals("sort")) {
            Intent si = new Intent(this, sort.class);
            startActivity(si);
        }

        if (st.equals("creds")){
            Intent si = new Intent(this, credits.class);
            startActivity(si);
        }
        return true;
    }
}