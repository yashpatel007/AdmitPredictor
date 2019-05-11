package com.example.yashpatel.admitpredictor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class admitPredict extends AppCompatActivity {

    ProgressBar grebar,toeflbar,gpabar;
    EditText grescore,toeflscore,gpascore;
    public double gre,toefl,gpa;
    Button greok,toeflok,gpaok;


    String bucket2[]={};

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admit_predict);
        // declare progress bar
        grebar = findViewById(R.id.grebar);
        toeflbar = findViewById(R.id.toeflbar);
        gpascore =findViewById(R.id.gpascore);
       // declare  buttons
        greok = findViewById(R.id.greok);
        toeflok = findViewById(R.id.toeflok);
        gpaok = findViewById(R.id.gpaok);
        //declare edit text
        grescore =findViewById(R.id.grescore);
        toeflscore=findViewById(R.id.toeflscore);
        gpascore= findViewById(R.id.gpascore);

        greok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grescore.getText().toString().length()!=0 && Integer.valueOf(grescore.getText().toString())<=340){
                gre = ((Integer.valueOf(grescore.getText().toString())*100)/340);
                int gre_precent =(int)gre;
                grebar.setProgress(gre_precent);
                }// if condition ends
                else{
                    Toast.makeText(admitPredict.this,"Please input a valid entry",Toast.LENGTH_SHORT).show();
                }
            }
        });

        toeflok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(toeflscore.getText().toString().length()!=0 && Integer.valueOf(toeflscore.getText().toString())<=120){
                toefl = (Integer.valueOf(toeflscore.getText().toString())*100)/120;
                int toefl_percent= (int)toefl;
                toeflbar.setProgress(toefl_percent);
                }// if ends
                else{
                    Toast.makeText(admitPredict.this,"please enter valid entry",Toast.LENGTH_LONG).show();
                }
            }
        });

        gpaok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(gpascore.getText().toString().length()!=0 && Integer.valueOf(gpascore.getText().toString())<=4){
                gpa = (Integer.valueOf(gpascore.getText().toString())*100)/4;
                int gpa_percent = (int)gpa;
                gpabar.setProgress(gpa_percent);}
                else{
                    Toast.makeText(admitPredict.this,"please enter valid entry",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void gotopred(View v){
        Intent i = new Intent(this,predList.class);
        startActivity(i);
    }

}
