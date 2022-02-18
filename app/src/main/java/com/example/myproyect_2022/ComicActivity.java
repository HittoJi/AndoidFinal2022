package com.example.myproyect_2022;

import static com.example.myproyect_2022.Comic.listComics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ComicActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private RecyclerView recyclerviewComic;
    private ComicFragment fragmentComic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic);

//        requestQueue = Volley.newRequestQueue(this);
//        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();
//
//        recyclerviewComic = (RecyclerView) findViewById(R.id.recyclerviewComic);
//        recyclerviewComic.setLayoutManager(new LinearLayoutManager(this));
//
//
        String idPopUpPlayer = getIntent().getStringExtra("idPopUpPlayer");

        ComicFragment comicFragment = (ComicFragment) getSupportFragmentManager().findFragmentById(R.id.comicFragment);
        comicFragment.getIntentData(idPopUpPlayer);


//        fetchComic(idPopUpPlayer);
//        setUpRecyclerView();
    }

//    private void setUpRecyclerView() {
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        recyclerviewComic.setLayoutManager(layoutManager);
//    }
//
//    private void displayComics(){
//        ComicAdapter adapter = new ComicAdapter(ComicActivity.this, listComics);
//        recyclerviewComic.setAdapter(adapter);
//    }
//
//    private void getComics(JSONObject response){
//        try {
//            JSONArray data = response.getJSONObject("data").getJSONArray("results");
//            //JSONArray thumbnail = response.getJSONObject("data").getJSONObject("results").getJSONArray("thumbnail");
//
//            for (int i = 0; i < data.length(); i++) {
//                JSONObject thumbnail = data.getJSONObject(i).getJSONObject("thumbnail");
//                String imgPath = "https" + ((thumbnail.getString("path")).substring(4));
//                if(imgPath.contains("image_not_available")){
//                    imgPath = "https://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available/portrait_incredible";
//                }
//                String imgExtension = thumbnail.getString("extension");
//                String imgURL = imgPath + "." + imgExtension;
//
//                Comic comic = new Comic(imgURL);
//                listComics.add(comic);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void fetchComic(String idPopUpPlayer) {
//        String url = "https://gateway.marvel.com/v1/public/characters/"+idPopUpPlayer+"/comics?ts=1&apikey=bab2142205a25d49c10b967670c901bf&hash=e9657d59fb905451860218cd0948861a";
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        getComics(response);
//                        displayComics();
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // TODO: Handle error
//                    }
//                });
//        requestQueue.add(jsonObjectRequest);
//    }
}