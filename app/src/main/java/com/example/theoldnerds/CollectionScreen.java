package com.example.theoldnerds;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class CollectionScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    MyDatabase myDataB;
    ArrayList<String> comic_ID, comic_name, comic_descrip, comic_date, comic_category, comic_goal;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_screen);
        myDataB = new MyDatabase(CollectionScreen.this);
        comic_ID = new ArrayList<>();
        comic_name = new ArrayList<>();
        comic_descrip = new ArrayList<>();
        comic_date = new ArrayList<>();
        comic_category = new ArrayList<>();
        comic_goal = new ArrayList<>();

        storeDatainArrays(); //stores data from database into array

        recyclerView = findViewById(R.id.recycerView);

        customAdapter = new CustomAdapter(CollectionScreen.this, CollectionScreen.this, comic_ID, comic_name, comic_descrip, comic_date, comic_category);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CollectionScreen.this));

        androidx.appcompat.widget.Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.navigation_View);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, tb,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {

            navigationView.setCheckedItem(R.id.nav_collection);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void storeDatainArrays() {
        Cursor cursor = myDataB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                comic_ID.add(cursor.getString(0));
                comic_name.add(cursor.getString(1));
                comic_descrip.add(cursor.getString(2));
                comic_date.add(cursor.getString(3));
                comic_category.add(cursor.getString(4));
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_collection:
                Intent intent = new Intent(this, CollectionScreen.class);
                startActivity(intent);
                break;

            case R.id.nav_Add_Item:
                Intent intents = new Intent(this, addComics.class);
                startActivity(intents);
                break;

            case R.id.nav_Photo:
                Intent intentsa = new Intent(this, Photos.class);
                startActivity(intentsa);
                break;

            case R.id.nav_Settings:
                Intent intentsas = new Intent(this, Settings.class);
                startActivity(intentsas);
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}