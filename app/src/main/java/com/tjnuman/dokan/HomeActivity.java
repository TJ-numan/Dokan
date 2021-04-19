package com.tjnuman.dokan;

import android.content.Intent;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.tjnuman.dokan.Adapter.VerticalRecyclerViewAdapter;
import com.tjnuman.dokan.Model.HorizontalModel;
import com.tjnuman.dokan.Model.VerticalModel;
import com.tjnuman.dokan.Prevalent.Prevalent;
import com.tjnuman.dokan.Prevalent.Sessions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {
    RecyclerView verticalRecyclerView;
    VerticalRecyclerViewAdapter vRViewAdapter;
    ArrayList<VerticalModel> verticalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        verticalArrayList  = new ArrayList<>();
        verticalRecyclerView = findViewById(R.id.verticalrecyclerview);
        verticalRecyclerView.setHasFixedSize(true);
        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        vRViewAdapter = new VerticalRecyclerViewAdapter(this,verticalArrayList);
        verticalRecyclerView.setAdapter(vRViewAdapter);
        setData();














        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        ImageView fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(HomeActivity.this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.logout:
                        Toast.makeText(HomeActivity.this, "Logout successfully", Toast.LENGTH_SHORT).show();
                        Sessions.isLoginUser(HomeActivity.this,false);
                        startActivity(new Intent(HomeActivity.this, SplashActivity.class));
                        finish();
                        return true;
                    case R.id.order:
                        Toast.makeText(HomeActivity.this, "order is selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_cart:
                        Toast.makeText(HomeActivity.this, "cart is selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_category:
                        Toast.makeText(HomeActivity.this, "category is selected", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.settings:
                        Toast.makeText(HomeActivity.this, "Setting is selected", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        Toast.makeText(HomeActivity.this, "Nothing is selected", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });

        View headerView = navigationView.getHeaderView(0);
        TextView userName = headerView.findViewById(R.id.user_name);
        CircleImageView profilePic = headerView.findViewById(R.id.profile_image);
        userName.setText(Prevalent.currentOnlineUser.getName());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }


    private void setData() {

        for(int i = 0; i<5; i++){

            VerticalModel verticalModel = new VerticalModel();
            verticalModel.setCategory("Category"+i);
            ArrayList<HorizontalModel> horizontalModelArrayList = new ArrayList<>();

            for(int j = 0; j<5; i++){

                HorizontalModel horizontalModel = new HorizontalModel();
                horizontalModel.setProductName("Product Name"+j);
                horizontalModel.setProductDescription("Product Description"+j);
                horizontalModelArrayList.add(horizontalModel);
            }
            verticalModel.setHorizontalArrayList(horizontalModelArrayList);
        }
        vRViewAdapter.notifyDataSetChanged();
   }


}