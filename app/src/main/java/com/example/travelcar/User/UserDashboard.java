package com.example.travelcar.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.travelcar.Common.LoginSignup.Login;
import com.example.travelcar.Common.LoginSignup.StartUpScreen;
import com.example.travelcar.HelperClasses.FeaturedAdapter;
import com.example.travelcar.HelperClasses.FeaturedHelperClass;
import com.example.travelcar.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;


import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;
    private RecyclerView featuredRecycler;
    private RecyclerView.Adapter adapter;
    private ArrayList<FeaturedAdapter> items = new ArrayList<>();
    private FeaturedAdapter featuredAdapter;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ShapeableImageView menuIcon;
    private LinearLayout contentPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //Hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        contentPanel = findViewById(R.id.content_panel);

        navigationDrawer();
        featuredRecycler();
    }

    private void navigationDrawer() {
        //Navigation
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.navigation_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.banner_background));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentPanel.setScaleX(offsetScale);
                contentPanel.setScaleY(offsetScale);

                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentPanel.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentPanel.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                break;
            case R.id.navigation_logout:
                Intent intentLogout = new Intent(UserDashboard.this, Login.class);
                startActivity(intentLogout);
                break;
            case R.id.navigation_profile:
                Intent intentProfile = new Intent(UserDashboard.this, Profile.class);
                startActivity(intentProfile);
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Shared", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_rate_us:
                Intent intentRating = new Intent(UserDashboard.this, Rating.class);
                startActivity(intentRating);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.drawable_driver1, "Alex", "To: Tel-Aviv\nDeparture at: 15:00"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.drawable_driver2, "Michel", "To: Ber-Sheva\nDeparture at: 15:20"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.drawable_driver3, "Cortney", "To: Holon\nDeparture at: 18:00"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.drawable_driver5, "Lisa", "To: Netanya\nDeparture at: 17:00"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.drawable_driver6, "Joseph", "To: Ashkelon\nDeparture at: 17:25"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.drawable_driver7, "Loren", "To: Haifa\nDeparture at: 15:20"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.drawable_driver8, "Yosef", "To: Eilat\nDeparture at: 16:00"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.drawable_driver9, "Jacob", "To: Caesarea\nDeparture at: 18:00"));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);
    }

    public void callStartUpScreen(View view) {
        startActivity(new Intent(getApplicationContext(), StartUpScreen.class));
    }

    public void callJoinScreen(View view) {
        startActivity(new Intent(getApplicationContext(), JoinScreen.class));
    }
}
