package com.d.httprequest;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HttpGetTask extends AsyncTask<String, Long, String> {


    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();

            Scanner scanner = new Scanner(inputStream);

            String data = "";

            while (scanner.hasNext()) {

                data = data + scanner.nextLine();
            }

            scanner.close();

            return data;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONArray root = new JSONArray(s);


            List<Post> postList = new ArrayList<>();

            Log.e("Root size", String.valueOf(root.length()));
            for (int i = 0; i < root.length(); i++) {

                JSONObject post = root.getJSONObject(i);
                int id = post.getInt("id");

                Log.e("id", String.valueOf(id));

                String date = post.getString("date");
                Log.e("date", String.valueOf(date));

                JSONObject title = post.getJSONObject("title");

                String rendered = title.getString("rendered");

                Log.e("title", rendered);

                Post postModel = new Post();

                postModel.id = id;
                postModel.date = date;
                postModel.title = rendered;
                postList.add(postModel);
            }

            Log.e("PostSize", postList.size() + "");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonParser jsonParser = new JsonParser();

        JsonElement jsonElement = jsonParser.parse(s);

        JsonArray root = jsonElement.getAsJsonArray();

        List<Post> postList = new ArrayList<>();

        for (int i = 0; i < root.size(); i++) {

            JsonObject post = root.get(i).getAsJsonObject();

            int id = post.get("id").getAsInt();

            String date = post.get("date").getAsString();

            JsonObject title = post.get("title").getAsJsonObject();

            String rendered = title.get("rendered").getAsString();


            Post postModel = new Post();

            postModel.id = id;
            postModel.title = rendered;
            postModel.date = date;

            postList.add(postModel);

        }


        Gson gson = new Gson();

        List<com.d.httprequest.model.Post> postListGson =
                gson.fromJson(s, new TypeToken<List<com.d.httprequest.model.Post>>() {
                }.getType());




        Log.e("GSON SIZE", postListGson.size() + "");


    }
}
