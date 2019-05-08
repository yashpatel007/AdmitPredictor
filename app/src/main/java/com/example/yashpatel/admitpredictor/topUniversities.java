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
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_top_universities);
        tv = findViewById(R.id.tv);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest  request = new StringRequest("https://www.usnews.com/best-colleges/rankings/national-universities", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            // if the request is successfull
                // now we get the contents form the web page
                Toast.makeText(topUniversities.this, "Request Successful", Toast.LENGTH_SHORT).show();
                //tv.append(response);
                Document doc = Jsoup.parse(response);

                Elements utitle = doc.getElementsByClass("DetailCardColleges__StyledAnchor-s1e80ggw-2 cJYIgQ Anchor-u1fur6-0 eNYUZI");

                tv.append("start :");
                for (int i = 0; i < utitle.size(); i++) {
                    Element item = utitle.get(i);


                    tv.append(item.text());


                }
                Toast.makeText(topUniversities.this, "items found : "+utitle.size(), Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // if the request fails
                Toast.makeText(topUniversities.this, "Request failed" , Toast.LENGTH_LONG).show();
            }
        });

       // add the request to the queue
        Toast.makeText(topUniversities.this, "Request Sent. please wait", Toast.LENGTH_SHORT).show();
        queue.add(request);
    }
}
