package com.example.yashpatel.admitpredictor;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
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

public class topUniversities extends AppCompatActivity {
    // tv is just for testing the output
    TextView tv,uname1,uname2,uname3, uname4,uname5,uname6,uname7,uname8, uname9,uname10;
    TextView udetail1,udetail2,udetail3,udetail4,udetail5,udetail6,udetail7,udetail8,udetail9,udetail10;
    TextView u1,u2,u3,u4,u5,u6,u7,u8,u9,u10;
String utitles[] = new String[10];
String ucontent[] = new String[10];
String urank[]= new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_top_universities);
        tv = findViewById(R.id.tv);
        uname1 = findViewById(R.id.university_name1);
        uname2 = findViewById(R.id.university_name2);
        uname3 = findViewById(R.id.university_name3);
        uname4 = findViewById(R.id.university_name4);
        uname5 = findViewById(R.id.university_name5);
        uname6 = findViewById(R.id.university_name6);
        uname7 = findViewById(R.id.university_name7);
        uname8 = findViewById(R.id.university_name8);
        uname9 = findViewById(R.id.university_name9);
        uname10 = findViewById(R.id.university_name10);

        udetail1 = findViewById(R.id.udetail1);
        udetail2 = findViewById(R.id.udetail2);
        udetail3 = findViewById(R.id.udetail3);
        udetail4 = findViewById(R.id.udetail4);
        udetail5 = findViewById(R.id.udetail5);
        udetail6 = findViewById(R.id.udetail6);
        udetail7 = findViewById(R.id.udetail7);
        udetail8 = findViewById(R.id.udetail8);
        udetail9 = findViewById(R.id.udetail9);
        udetail10 = findViewById(R.id.udetail10);

        u1  = findViewById(R.id.acceptance_text1);
        u2 = findViewById(R.id.acceptance_text2);
        u3 = findViewById(R.id.acceptance_text3);
        u4 = findViewById(R.id.acceptance_text4);
        u5 = findViewById(R.id.acceptance_text5);
        u6 = findViewById(R.id.acceptance_text6);
        u7 = findViewById(R.id.acceptance_text7);
        u8 = findViewById(R.id.acceptance_text8);
        u9 = findViewById(R.id.acceptance_text9);
        u10 = findViewById(R.id.acceptance_text10);


        RequestQueue queue = Volley.newRequestQueue(this);

        // making the first request
        StringRequest  usnews_top = new StringRequest("https://www.usnews.com/best-colleges/rankings/national-universities", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            // if the request is successfull
                // now we get the contents form the web page
                Toast.makeText(topUniversities.this, "Request Successful", Toast.LENGTH_SHORT).show();
                //tv.append(response);
                Document doc = Jsoup.parse(response);

                // first get the title of universities by class
                Elements utitle = doc.getElementsByClass("DetailCardColleges__StyledAnchor-s1e80ggw-2 cJYIgQ Anchor-u1fur6-0 eNYUZI");
                Elements rank = doc.getElementsByClass("ranklist-ranked-item RankList__Rank-s1dx9co1-2 jNcEpG");
                Elements details = doc.getElementsByClass("Hide-s18sxv0v-0 bxLBHg Paragraph-fqygwe-0 itSAGF");
                Elements links = doc.select("a");
                // get the information
                for (int i = 0; i < utitle.size(); i++) {
                    Element item = utitle.get(i);
                    Element detail = details.get(i);
                    Element ranking = rank.get(i);
                    //tv.append(item.attr("href"));
                    utitles[i]=item.text();
                    ucontent[i]=detail.text();
                    urank[i]=ranking.text();

                    //tv.append(ranking.text()+detail.text()+"\n");


                }
                //tv.append(utitles[5]);
                // set the contents
                uname1.setText(utitles[0]);
                uname2.setText(utitles[1]);
                uname3.setText(utitles[2]);
                uname4.setText(utitles[3]);
                uname5.setText(utitles[4]);
                uname6.setText(utitles[5]);
                uname7.setText(utitles[6]);
                uname8.setText(utitles[7]);
                uname9.setText(utitles[8]);
                uname10.setText(utitles[9]);

                udetail1.setText(ucontent[0]);
                udetail2.setText(ucontent[1]);
                udetail3.setText(ucontent[2]);
                udetail4.setText(ucontent[3]);
                udetail5.setText(ucontent[4]);
                udetail6.setText(ucontent[5]);
                udetail7.setText(ucontent[6]);
                udetail8.setText(ucontent[7]);
                udetail9.setText(ucontent[8]);
                udetail10.setText(ucontent[9]);

                u1.setText(urank[0]);
                u2.setText(urank[1]);
                u3.setText(urank[2]);
                u4.setText(urank[3]);
                u5.setText(urank[4]);
                u6.setText(urank[5]);
                u7.setText(urank[6]);
                u8.setText(urank[7]);
                u9.setText(urank[8]);
                u10.setText(urank[9]);

                Toast.makeText(topUniversities.this, "items found : "+utitle.size(), Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // if the request fails
                Toast.makeText(topUniversities.this, "Request failed" , Toast.LENGTH_LONG).show();
            }
        });

        // make the second request





       // add the request to the queue
        Toast.makeText(topUniversities.this, "Request Sent. please wait", Toast.LENGTH_SHORT).show();
        queue.add(usnews_top);


        // fill the contents



    }
}
