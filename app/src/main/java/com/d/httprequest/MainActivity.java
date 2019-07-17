package com.d.httprequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void httpGet(View view) {

        HttpGetTask httpGetTask = new HttpGetTask();

        httpGetTask.execute("http://asian.dotplays.com/wp-json/wp/v2/posts");

    }

    public void httpPost(View view) {

        HttpPostTask httpPostTask = new HttpPostTask();

        httpPostTask.equals("http://dev.parduota.com/api/search/");

    }
}
