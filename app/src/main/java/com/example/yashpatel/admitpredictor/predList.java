package com.example.yashpatel.admitpredictor;

import android.content.Intent;
import android.os.Handler;
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

    TextView tv, gre_passed, toefl_passed, safebucket, mediumbucket, lowbucket;
    public int gre, toefl;
    private static int WAIT_RESPONCE = 5000;

    String bucket1[] = new String[39];
    String bucket2[] = new String[40];
    String bucket3[] = new String[39];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pred_list);
        // get the contents from last intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            gre = extras.getInt("gre_score");
            toefl = extras.getInt("toefl_score");
        }

        tv = findViewById(R.id.tv);
        gre_passed = findViewById(R.id.grepassed);
        toefl_passed = findViewById(R.id.toeflpassed);
        gre_passed.setText(String.valueOf(gre));
        toefl_passed.setText(String.valueOf(toefl));
        safebucket = findViewById(R.id.safebucket);
        mediumbucket = findViewById(R.id.mediumbucket);
        lowbucket = findViewById(R.id.lowbucket);

        RequestQueue queue = Volley.newRequestQueue(this);
        RequestQueue que1 = Volley.newRequestQueue(this);
        RequestQueue que2 = Volley.newRequestQueue(this);
        //first request
        StringRequest top_university = new StringRequest("https://crunchprep.com/gre/2014/universities-gre-scores-320-to-340", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //on successful request
                Toast.makeText(predList.this, "Request Successful", Toast.LENGTH_SHORT).show();
                Document doc = Jsoup.parse(response);
                Elements tuniv = doc.getElementsByTag("a");
                int k = 0;
                for (int i = 0; i < tuniv.size(); i++) {
                    Element item = tuniv.get(i);
                    String uname = item.getElementsByTag("strong").text();
                    if (uname.length() != 0) {
                        bucket1[k] = uname;
                        k++;
                    }

                }
                Toast.makeText(predList.this, "items found : " + tuniv.size(), Toast.LENGTH_LONG).show();
                for (int j = 0; j < 39; j++) {
                    tv.append(bucket1[j]);
                    tv.append("\n");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // if the request fails
                Toast.makeText(predList.this, "Request failed", Toast.LENGTH_LONG).show();
            }
        });
        // second request--- to fill bucket2
        StringRequest middle_university = new StringRequest("https://crunchprep.com/gre/2014/universities-gre-scores-310-to-320", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // request 2 is successful
                Document doc1 = Jsoup.parse(response);
                Elements tuniv = doc1.getElementsByTag("strong");
                int l = 0;
                for (int i = 0; i < tuniv.size(); i++) {
                    Element item = tuniv.get(i);
                    String uname = item.getElementsByTag("a").text();
                    if (uname.length() != 0) {
                        bucket2[l] = uname;
                        l++;
                    }

                }
                Toast.makeText(predList.this, "items found : " + tuniv.size(), Toast.LENGTH_LONG).show();
                for (int j = 0; j < 38; j++) {
                    tv.append(bucket2[j]);
                    tv.append("\n");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(predList.this, "Request failed", Toast.LENGTH_LONG).show();
            }
        });

        // make  a third request for bucket 3
        StringRequest low_university = new StringRequest("https://crunchprep.com/gre/2014/universities-gre-scores-300-to-310", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // if the  request is successful
                Toast.makeText(predList.this, "Request Successful", Toast.LENGTH_SHORT).show();
                Document doc2 = Jsoup.parse(response);
                Elements tuniv = doc2.getElementsByTag("a");
                int m = 0;
                for (int i = 0; i < tuniv.size(); i++) {
                    Element item = tuniv.get(i);
                    String uname = item.getElementsByTag("strong").text();
                    if (uname.length() != 0) {
                        bucket3[m] = uname;
                        m++;
                    }

                }
                Toast.makeText(predList.this, "items found : " + tuniv.size(), Toast.LENGTH_LONG).show();
                for (int j = 0; j < 39; j++) {
                    tv.append(bucket3[j]);
                    tv.append("\n");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // if the request fails
                Toast.makeText(predList.this, "Request failed", Toast.LENGTH_LONG).show();
            }
        });


        // add the request to the queue
        Toast.makeText(predList.this, "Request Sent. please wait", Toast.LENGTH_SHORT).show();
        queue.add(top_university);
        que1.add(middle_university);
        que2.add(low_university);


        // now all the buckets have been filled
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // now that we have waited for 5 seconds the buckets should be filled by now
                if (gre >= 325 && toefl >= 100) {
                    String stopres[] = getRandom(bucket1, 5, 1);
                    String smidres[] = getRandom(bucket2, 10, 1);
                    String slowres[] = getRandom(bucket3, 5, 1);
                    // populate safe options
                    for (int q = 0; q < 4; q++) {
                        safebucket.append(slowres[q]);
                        safebucket.append("\n");
                    }
                    for (int k = 0; k < 5; k++) {
                        safebucket.append(smidres[k]);
                        safebucket.append("\n");
                    }
                    for (int j = 0; j < 3; j++) {
                        safebucket.append(stopres[j]);
                        safebucket.append("\n");
                    }
                    // populate medium options
                    String mtopres[] = getRandom(bucket1, 5, 3);
                    String mmidres[] = getRandom(bucket2, 10, 1);
                    String mlowres[] = getRandom(bucket3, 5, 1);
                    for (int q = 0; q < 2; q++) {
                        mediumbucket.append(mlowres[q]);
                        mediumbucket.append("\n");
                    }
                    for (int k = 0; k < 9; k++) {
                        mediumbucket.append(mmidres[k]);
                        mediumbucket.append("\n");
                    }
                    for (int j = 0; j < 1; j++) {
                        mediumbucket.append(mtopres[j]);
                        mediumbucket.append("\n");
                    }
                    //populate the low chance option
                    String ltopres[] = getRandom(bucket1, 5, 1);
                    String lmidres[] = getRandom(bucket1, 5, 2);
                    String llowres[] = getRandom(bucket1, 5, 3);
                    for (int q = 0; q < 4; q++) {
                        lowbucket.append(llowres[q]);
                        lowbucket.append("\n");
                    }
                    for (int k = 0; k < 4; k++) {
                        lowbucket.append(lmidres[k]);
                        lowbucket.append("\n");
                    }
                    for (int j = 0; j < 4; j++) {
                        lowbucket.append(ltopres[j]);
                        lowbucket.append("\n");
                    }


                }

                if (gre < 325 && gre > 310 && toefl > 90) {
                    String stopres[] = getRandom(bucket1, 5, 2);
                    String smidres[] = getRandom(bucket2, 10, 1);
                    String smidres2[]= getRandom(bucket2,5,2);
                    String slowres[] = getRandom(bucket3, 5, 2);
                    // fill the safe bucket first
                    for (int q = 0; q < 4; q++) {
                        safebucket.append(slowres[q]);
                        safebucket.append("\n");
                    }
                    for (int k = 0; k < 4; k++) {
                        safebucket.append(smidres[k]);
                        safebucket.append("\n");
                    }
                    for (int k = 0; k < 4; k++) {
                        safebucket.append(smidres2[k]);
                        safebucket.append("\n");
                    }
                    //fill the medium bucket
                    String mtopres[] = getRandom(bucket1, 5, 3);
                    String mmidres[] = getRandom(bucket2, 10, 1);
                    String mlowres[] = getRandom(bucket3, 5, 1);
                    for (int q = 0; q < 4; q++) {
                        mediumbucket.append(mlowres[q]);
                        mediumbucket.append("\n");
                    }
                    for (int k = 0; k < 7; k++) {
                        mediumbucket.append(mmidres[k]);
                        mediumbucket.append("\n");
                    }
                    for (int j = 0; j < 1; j++) {
                        mediumbucket.append(mtopres[j]);
                        mediumbucket.append("\n");
                    }
                    // fill the low bucket
                    String ltopres[] = getRandom(bucket1, 4, 1);
                    String lmidres[] = getRandom(bucket1, 5, 1);
                    String llowres[] = getRandom(bucket1, 5, 3);
                    for (int q = 0; q < 1; q++) {
                        lowbucket.append(llowres[q]);
                        lowbucket.append("\n");
                    }
                    for (int k = 0; k < 4; k++) {
                        lowbucket.append(lmidres[k]);
                        lowbucket.append("\n");
                    }
                    for (int j = 0; j < 7; j++) {
                        lowbucket.append(ltopres[j]);
                        lowbucket.append("\n");
                    }
                    if(gre<310 && gre >295 && toefl>80){




                    }// if ends
                }


            }// public void run ends
        }, WAIT_RESPONCE);

    }


    public String[] getRandom(String[] bucket, int elements, int bucket_class) {
        // bucket class = 20 for top
        // bucket class  = 30 for middle
        // bucket class = 39 for low
        int low = 0;
        int mid = 15;
        int mid2 = 25;
        int high = 38;
        String result[] = new String[elements];
        if (bucket_class == 1) {
            for (int i = 0; i < elements; i++) {
                int idx = (int) (Math.random() * (mid - low)) + low;
                result[i] = bucket[idx];
                System.out.println(bucket[idx]);
            }

        }
        if (bucket_class == 2) {
            for (int i = 0; i < elements; i++) {
                int idx = (int) (Math.random() * (mid2 - mid)) + mid;
                result[i] = bucket[idx];
                System.out.println(bucket[idx]);
            }

        }
        if (bucket_class == 3) {
            for (int i = 0; i < elements; i++) {
                int idx = (int) (Math.random() * (high - mid2)) + mid2;
                result[i] = bucket[idx];
                System.out.println(bucket[idx]);
            }


        }
        return result;
    }
}

