package com.example.yashpatel.admitpredictor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class predList extends AppCompatActivity {

    TextView tv;


    String bucket1[]= new String[39];
    String bucket2[]= new String[40];
    String bucket3[]= new String[39];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pred_list);
        tv=findViewById(R.id.tv);

        RequestQueue queue = Volley.newRequestQueue(this);
        RequestQueue que1=Volley.newRequestQueue(this);
        RequestQueue que2 =Volley.newRequestQueue(this);
        //first request
        StringRequest top_university = new StringRequest("https://crunchprep.com/gre/2014/universities-gre-scores-320-to-340", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //on successful request
                Toast.makeText(predList.this, "Request Successful", Toast.LENGTH_SHORT).show();
                Document doc = Jsoup.parse(response);
                Elements tuniv = doc.getElementsByTag("a");
                int k=0;
                for (int i = 0; i < tuniv.size(); i++) {
                    Element item = tuniv.get(i);
                    String uname = item.getElementsByTag("strong").text();
                    if(uname.length()!=0){
                        bucket1[k]=uname;
                        k++;
                    }
                   
                }
                Toast.makeText(predList.this, "items found : "+tuniv.size(), Toast.LENGTH_LONG).show();
                for(int j=0;j<39;j++){
                    tv.append(bucket1[j]);
                    tv.append("\n");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // if the request fails
                Toast.makeText(predList.this, "Request failed" , Toast.LENGTH_LONG).show();
            }
        });
        // second request--- to fill bucket2
        StringRequest middle_university = new StringRequest("https://crunchprep.com/gre/2014/universities-gre-scores-310-to-320", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // request 2 is successful
                Document doc1 = Jsoup.parse(response);
                Elements tuniv = doc1.getElementsByTag("strong");
                int l=0;
                for (int i = 0; i < tuniv.size(); i++) {
                    Element item = tuniv.get(i);
                    String uname = item.getElementsByTag("a").text();
                    if(uname.length()!=0){
                        bucket2[l]=uname;
                        l++;
                    }

                }
                Toast.makeText(predList.this, "items found : "+tuniv.size(), Toast.LENGTH_LONG).show();
                for(int j=0;j<38;j++){
                    tv.append(bucket2[j]);
                    tv.append("\n");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(predList.this, "Request failed" , Toast.LENGTH_LONG).show();
            }
        });

        // make  a third request for bucket 3
        StringRequest low_university = new StringRequest("https://crunchprep.com/gre/2014/universities-gre-scores-300-to-310", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
             // if the  request is successful
                Toast.makeText(predList.this, "Request Successful", Toast.LENGTH_SHORT).show();
                Document doc2= Jsoup.parse(response);
                Elements tuniv = doc2.getElementsByTag("a");
                int m=0;
                for (int i = 0; i < tuniv.size(); i++) {
                    Element item = tuniv.get(i);
                    String uname = item.getElementsByTag("strong").text();
                    if(uname.length()!=0){
                        bucket3[m]=uname;
                        m++;
                    }

                }
                Toast.makeText(predList.this, "items found : "+tuniv.size(), Toast.LENGTH_LONG).show();
                for(int j=0;j<39;j++){
                    tv.append(bucket3[j]);
                    tv.append("\n");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // if the request fails

            }
        });


        // add the request to the queue
        Toast.makeText(predList.this, "Request Sent. please wait", Toast.LENGTH_SHORT).show();
        queue.add(top_university);
        que1.add(middle_university);
        que2.add(low_university);
    }
}
