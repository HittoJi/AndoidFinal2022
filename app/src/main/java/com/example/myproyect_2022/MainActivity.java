package com.example.myproyect_2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private List<Character> characterList;
    private List<Character> characterListF;
    private CardView moreInfoCharacter;
    private TextView textViewNameHome;
    private ImageView imageViewHome;
    private TextView description;
    private LottieAnimationView lottieAnimationView;
    private LottieAnimationView allfavorites;
    private boolean switchOn = false;
    private boolean switchFavorites = false;
    private boolean popUpIfVisible = false;
    private CharacterSQLHelper characterSQLHelper;
    private int idPopUpPlayer = 0;
    private Button btnClousPopUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deleteDatabase("Characters");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        moreInfoCharacter = (CardView) findViewById(R.id.moreInfoCharacter);
        textViewNameHome = (TextView) findViewById(R.id.nameHome);
        imageViewHome = (ImageView) findViewById(R.id.cardImgHome);
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.starAnimation);
        allfavorites = (LottieAnimationView) findViewById(R.id.allfavorites);
        allfavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFavorites();
            }
        });


        description = (TextView) findViewById(R.id.description);

        btnClousPopUp = (Button) findViewById(R.id.btnClousPopUp);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = Volley.newRequestQueue(this);
        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        characterList = new ArrayList<>();
        characterListF = new ArrayList<>();
        characterSQLHelper = new CharacterSQLHelper(getBaseContext());

        fetchCharacter();
        setUpRecyclerView();
        clousPopUpWindow();
//        openComicActivity();
    }

    private void getFavorites(){
        if (switchFavorites){
            displayCharacters(characterList);
            setStarAnimationFalse(allfavorites);
            switchFavorites = false;
            return;
        }
        characterListF.removeAll(characterListF);
        for (int i = 0; i < characterList.size(); i++) {
            if (checkIfFavorite(characterList.get(i).getId())){
                characterListF.add(characterList.get(i));
            }
        }
        displayCharacters(characterListF);
        setStarAnimationTrue(allfavorites);
        switchFavorites = true;
    }

    private void openComicActivity(){
        imageViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ComicActivity.class);
                intent.putExtra("idPopUpPlayer",String.valueOf(idPopUpPlayer));
                startActivity(intent);
            }
        });
    }

    private void clousPopUpWindow(){
        btnClousPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpCardClous();
            }
        });
    }

    private String selectFavorite(int idCharacter) {
        SQLiteDatabase dbMarvel = characterSQLHelper.getWritableDatabase();
        Cursor cursor = dbMarvel.rawQuery("SELECT favorite FROM Characters WHERE id = " + idCharacter + "", null);
        String result = "";
        while (cursor.moveToNext()) {
            result = cursor.getString(0);
        }
        return result;
    }

    private void setUpRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void popUpAnimarion(int fxd, int txd, int fyd, int tyd) {
        TranslateAnimation trans = new TranslateAnimation(fxd, txd, fyd, tyd);
        trans.setDuration(500);
        trans.setInterpolator(new AccelerateInterpolator(1.0f));
        moreInfoCharacter.startAnimation(trans);
    }

    private void popUpCardClous() {
        moreInfoCharacter.setVisibility(View.GONE);
        popUpAnimarion(0, 0, 0, 2000);
        popUpIfVisible = false;
    }

    private void saveAsFavoriteCharacter(int idCharacter) {
        SQLiteDatabase dbMarvel = characterSQLHelper.getWritableDatabase();
        dbMarvel.execSQL("INSERT INTO Characters(id, favorite ) VALUES(" + idCharacter + ",'true')");
        switchOn = true;
    }

    private void removeAsFavoriteCharacter(int idCharacter) {
        SQLiteDatabase dbMarvel = characterSQLHelper.getWritableDatabase();
        Cursor cursor = dbMarvel.rawQuery("DELETE FROM Characters WHERE id = '"+idCharacter+"'",null);
        while (cursor.moveToNext()) {}
        switchOn = false;
    }

    private boolean checkIfFavorite(int idCharacter) {
        SQLiteDatabase dbMarvel = characterSQLHelper.getWritableDatabase();
        Cursor cursor = dbMarvel.rawQuery("SELECT favorite FROM Characters WHERE id = " + idCharacter + "", null);
        boolean result = false;
//        Cursor cursor = dbMarvel.rawQuery("DELETE FROM Characters WHERE id = '"+idCharacter+"'",null);
        while (cursor.moveToNext()) {
            result = Boolean.parseBoolean(cursor.getString(0));
        }
        return result;
    }

    private void popUpCardOpen(View v) {
        moreInfoCharacter.setVisibility(View.VISIBLE);
        popUpAnimarion(0, 0, 2000, 0);

        idPopUpPlayer = characterList.get(recyclerView.getChildAdapterPosition(v)).getId();
        textViewNameHome.setText(characterList.get(recyclerView.getChildAdapterPosition(v)).getName());
        Glide.with(MainActivity.this).load(characterList.get(recyclerView.getChildAdapterPosition(v)).getImgURL()).into(imageViewHome);
        description.setText(characterList.get(recyclerView.getChildAdapterPosition(v)).getDescription());

        if (checkIfFavorite(idPopUpPlayer)){
            setStarAnimationTrue(lottieAnimationView);
            switchOn = true;
        }else {
            setStarAnimationFalse(lottieAnimationView);
            switchOn = false;
        }

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            Toast toast = Toast.makeText(MainActivity.this, "HOLAAA", Toast.LENGTH_SHORT);
            toast.show();
            ComicFragment comicFragment = (ComicFragment) getSupportFragmentManager().findFragmentById(R.id.comicFragment);
            comicFragment.getIntentData(String.valueOf(idPopUpPlayer));
        }else{
            openComicActivity();
        }

        popUpIfVisible = true;
    }

    private void setStarAnimationTrue(LottieAnimationView element) {
        element.setMinAndMaxProgress(0f, 0.7f);
        element.playAnimation();
    }

    private void setStarAnimationFalse(LottieAnimationView element) {
        element.setMinAndMaxProgress(0f, 0.05f);
        element.playAnimation();
    }

    private void setStarAnimation() {
        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override //Hacer las consultas desde 0; -----------------------------------------------
            public void onClick(View v) {

                if (switchOn){
                    //Si es tru borrar el uaurio
                    removeAsFavoriteCharacter(idPopUpPlayer);
                    setStarAnimationFalse(lottieAnimationView);
                }else{
                    //Si es false ñadir
                    saveAsFavoriteCharacter(idPopUpPlayer);
                    setStarAnimationTrue(lottieAnimationView);
                }
                if (switchFavorites){
                    switchFavorites = false;
                    getFavorites();
                }
            }
        });
    }
    //--------------------------------------------------------------
    //Reciler => ok
    //SQL => ok
    //Idiomas => No es necesatio (Si hay texto hay idioma)
    //Fragmntos
    //--------------------------------------------------------------

    private void getCharacters(JSONObject response) {
        try {
            JSONArray data = response.getJSONObject("data").getJSONArray("results");
            //JSONArray thumbnail = response.getJSONObject("data").getJSONObject("results").getJSONArray("thumbnail");

            for (int i = 0; i < data.length(); i++) {
                int id = Integer.parseInt(data.getJSONObject(i).getString("id"));
                String name = data.getJSONObject(i).getString("name");
                String description = data.getJSONObject(i).getString("description");
                String modified = data.getJSONObject(i).getString("modified");
                JSONObject thumbnail = data.getJSONObject(i).getJSONObject("thumbnail");
                String imgPath = "https" + ((thumbnail.getString("path")).substring(4));
                String imgExtension = thumbnail.getString("extension");
                String imgURL = imgPath + "/landscape_incredible." + imgExtension;
                if (description.length() < 2) {
                    description = "N/A";
                }
                Character character = new Character(id, name, description, modified, imgURL);
                characterList.add(character);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void displayCharacters(List<Character> list) {
        CharacterAdapter adapter = new CharacterAdapter(MainActivity.this, list);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!popUpIfVisible) {
                    popUpCardOpen(v);
                    setStarAnimation();
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void fetchCharacter() {
        String url = "https://gateway.marvel.com/v1/public/characters?ts=1&apikey=bab2142205a25d49c10b967670c901bf&hash=e9657d59fb905451860218cd0948861a";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        getCharacters(response);
                        displayCharacters(characterList);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
}