package com.tjnuman.dokan;

import android.content.Intent;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.tjnuman.dokan.Adapter.HorizontalRecyclerViewAdapter;
import com.tjnuman.dokan.Adapter.VerticalRecyclerViewAdapter;
import com.tjnuman.dokan.Model.HorizontalModel;
import com.tjnuman.dokan.Model.VerticalModel;
import com.tjnuman.dokan.Prevalent.Prevalent;
import com.tjnuman.dokan.Prevalent.Sessions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    private static final String TAG = "home_act";

    RecyclerView verticalRecyclerView;
    VerticalRecyclerViewAdapter vRViewAdapter;
    ArrayList<VerticalModel> verticalArrayList;
    ArrayList<HorizontalModel> horizontalList,horizontalList2,horizontalList3,horizontalList7,horizontalList6,horizontalList5,horizontalList4,
    horizontalList12,horizontalList13,horizontalList11,horizontalList10,horizontalList9,horizontalList8,horizontalList14,horizontalList15,horizontalList16;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        verticalArrayList  = new ArrayList<>();
        horizontalList  = new ArrayList<>();horizontalList4  = new ArrayList<>();horizontalList5  = new ArrayList<>();horizontalList6  = new ArrayList<>();
        horizontalList2  = new ArrayList<>();horizontalList7  = new ArrayList<>();horizontalList8  = new ArrayList<>();horizontalList9  = new ArrayList<>();
        horizontalList3  = new ArrayList<>();horizontalList10  = new ArrayList<>();horizontalList11  = new ArrayList<>();horizontalList12  = new ArrayList<>();
        horizontalList16  = new ArrayList<>();horizontalList15  = new ArrayList<>();horizontalList14  = new ArrayList<>();horizontalList13  = new ArrayList<>();
        verticalRecyclerView = findViewById(R.id.verticalrecyclerview);
        verticalRecyclerView.setHasFixedSize(true);
        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        vRViewAdapter = new VerticalRecyclerViewAdapter(this,verticalArrayList);
        verticalRecyclerView.setAdapter(vRViewAdapter);
        getDataFromServer();
















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

                    case R.id.profile:
                        Toast.makeText(HomeActivity.this, "Profile is selected", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HomeActivity.this,ProfileActivity.class);
                        startActivity(intent);
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
        Glide.with(profilePic).load(Prevalent.currentOnlineUser.getImage()).into(profilePic);

    }

    private void getDataFromServer() {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        Query query = rootRef.child("Products").orderByChild("category").equalTo("Pets");//is working
        Query query2 = rootRef.child("Products").orderByChild("category").equalTo("Laptops");//is wroking
        Query query3 = rootRef.child("Products").orderByChild("category").equalTo("Cars");//is workings
        Query query4 = rootRef.child("Products").orderByChild("category").equalTo("Household");//is workings
        Query query5 = rootRef.child("Products").orderByChild("category").equalTo("Clothing ");
        Query query6 = rootRef.child("Products").orderByChild("category").equalTo("Gadgets");
        Query query7 = rootRef.child("Products").orderByChild("category").equalTo("Jewellery");
        Query query8 = rootRef.child("Products").orderByChild("category").equalTo("Medicine");
        Query query9 = rootRef.child("Products").orderByChild("category").equalTo("Bikes");
        Query query10 = rootRef.child("Products").orderByChild("category").equalTo("Properties");
        Query query11 = rootRef.child("Products").orderByChild("category").equalTo("Sports");
        Query query12 = rootRef.child("Products").orderByChild("category").equalTo("Smartphone");
        Query query13= rootRef.child("Products").orderByChild("category").equalTo("Electronics");
        Query query14= rootRef.child("Products").orderByChild("category").equalTo("Tickets");
        Query query15 = rootRef.child("Products").orderByChild("category").equalTo("Others");
        Query query16 = rootRef.child("Products").orderByChild("category").equalTo("Grocery");


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int x=0;
                if (snapshot.exists()){
                    for (DataSnapshot snp:snapshot.getChildren()){
                        Log.d(TAG, "onDataChange: name: "+snp.child("pname").getValue().toString());
                        String name = snp.child("pname").getValue().toString();
                        String price = snp.child("price").getValue().toString();
                        String image = snp.child("image").getValue().toString();
                        String category = snp.child("category").getValue().toString();
                        String description = snp.child("description").getValue().toString();

                        HorizontalModel model = new HorizontalModel(name,price,description,image);
                        horizontalList.add(model);
                        x++;
                        if (snapshot.getChildrenCount() == x){
                            verticalArrayList.add(new VerticalModel(category,horizontalList));
                            vRViewAdapter.notifyDataSetChanged();
                        }

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int x=0;
                if (snapshot.exists()){
                    for (DataSnapshot snp:snapshot.getChildren()){
                        Log.d(TAG, "onDataChange: name: "+snp.child("pname").getValue().toString());
                        String name = snp.child("pname").getValue().toString();
                        String price = snp.child("price").getValue().toString();
                        String image = snp.child("image").getValue().toString();
                        String category = snp.child("category").getValue().toString();
                        String description = snp.child("description").getValue().toString();

                        HorizontalModel model2 = new HorizontalModel(name,price,description,image);
                        horizontalList2.add(model2);
                        x++;
                        if (snapshot.getChildrenCount() == x){
                            verticalArrayList.add(new VerticalModel(category,horizontalList2));
                            vRViewAdapter.notifyDataSetChanged();
                        }

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int x=0;
                if (snapshot.exists()){
                    for (DataSnapshot snp:snapshot.getChildren()){
                        Log.d(TAG, "onDataChange: name: "+snp.child("pname").getValue().toString());
                        String name = snp.child("pname").getValue().toString();
                        String price = snp.child("price").getValue().toString();
                        String image = snp.child("image").getValue().toString();
                        String category = snp.child("category").getValue().toString();
                        String description = snp.child("description").getValue().toString();

                        HorizontalModel model3 = new HorizontalModel(name,price,description,image);
                        horizontalList3.add(model3);
                        x++;
                        if (snapshot.getChildrenCount() == x){
                            verticalArrayList.add(new VerticalModel(category,horizontalList3));
                            vRViewAdapter.notifyDataSetChanged();
                        }

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int x=0;
                if (snapshot.exists()){
                    for (DataSnapshot snp:snapshot.getChildren()){
                        Log.d(TAG, "onDataChange: name: "+snp.child("pname").getValue().toString());
                        String name = snp.child("pname").getValue().toString();
                        String price = snp.child("price").getValue().toString();
                        String image = snp.child("image").getValue().toString();
                        String category = snp.child("category").getValue().toString();
                        String description = snp.child("description").getValue().toString();

                        HorizontalModel model4 = new HorizontalModel(name,price,description,image);
                        horizontalList4.add(model4);
                        x++;
                        if (snapshot.getChildrenCount() == x){
                            verticalArrayList.add(new VerticalModel(category,horizontalList4));
                            vRViewAdapter.notifyDataSetChanged();
                        }

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int x=0;
                if (snapshot.exists()){
                    for (DataSnapshot snp:snapshot.getChildren()){
                        Log.d(TAG, "onDataChange: name: "+snp.child("pname").getValue().toString());
                        String name = snp.child("pname").getValue().toString();
                        String price = snp.child("price").getValue().toString();
                        String image = snp.child("image").getValue().toString();
                        String category = snp.child("category").getValue().toString();
                        String description = snp.child("description").getValue().toString();

                        HorizontalModel model5 = new HorizontalModel(name,price,description,image);
                        horizontalList5.add(model5);
                        x++;
                        if (snapshot.getChildrenCount() == x){
                            verticalArrayList.add(new VerticalModel(category,horizontalList5));
                            vRViewAdapter.notifyDataSetChanged();
                        }

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query6.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int x=0;
                if (snapshot.exists()){
                    for (DataSnapshot snp:snapshot.getChildren()){
                        Log.d(TAG, "onDataChange: name: "+snp.child("pname").getValue().toString());
                        String name = snp.child("pname").getValue().toString();
                        String price = snp.child("price").getValue().toString();
                        String image = snp.child("image").getValue().toString();
                        String category = snp.child("category").getValue().toString();
                        String description = snp.child("description").getValue().toString();

                        HorizontalModel model6 = new HorizontalModel(name,price,description,image);
                        horizontalList6.add(model6);
                        x++;
                        if (snapshot.getChildrenCount() == x){
                            verticalArrayList.add(new VerticalModel(category,horizontalList6));
                            vRViewAdapter.notifyDataSetChanged();
                        }

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query7.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int x=0;
                if (snapshot.exists()){
                    for (DataSnapshot snp:snapshot.getChildren()){
                        Log.d(TAG, "onDataChange: name: "+snp.child("pname").getValue().toString());
                        String name = snp.child("pname").getValue().toString();
                        String price = snp.child("price").getValue().toString();
                        String image = snp.child("image").getValue().toString();
                        String category = snp.child("category").getValue().toString();
                        String description = snp.child("description").getValue().toString();

                        HorizontalModel model7 = new HorizontalModel(name,price,description,image);
                        horizontalList7.add(model7);
                        x++;
                        if (snapshot.getChildrenCount() == x){
                            verticalArrayList.add(new VerticalModel(category,horizontalList7));
                            vRViewAdapter.notifyDataSetChanged();
                        }

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query8.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int x=0;
                if (snapshot.exists()){
                    for (DataSnapshot snp:snapshot.getChildren()){
                        Log.d(TAG, "onDataChange: name: "+snp.child("pname").getValue().toString());
                        String name = snp.child("pname").getValue().toString();
                        String price = snp.child("price").getValue().toString();
                        String image = snp.child("image").getValue().toString();
                        String category = snp.child("category").getValue().toString();
                        String description = snp.child("description").getValue().toString();

                        HorizontalModel model8 = new HorizontalModel(name,price,description,image);
                        horizontalList8.add(model8);
                        x++;
                        if (snapshot.getChildrenCount() == x){
                            verticalArrayList.add(new VerticalModel(category,horizontalList8));
                            vRViewAdapter.notifyDataSetChanged();
                        }

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query9.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int x=0;
                if (snapshot.exists()){
                    for (DataSnapshot snp:snapshot.getChildren()){
                        Log.d(TAG, "onDataChange: name: "+snp.child("pname").getValue().toString());
                        String name = snp.child("pname").getValue().toString();
                        String price = snp.child("price").getValue().toString();
                        String image = snp.child("image").getValue().toString();
                        String category = snp.child("category").getValue().toString();
                        String description = snp.child("description").getValue().toString();

                        HorizontalModel model9 = new HorizontalModel(name,price,description,image);
                        horizontalList9.add(model9);
                        x++;
                        if (snapshot.getChildrenCount() == x){
                            verticalArrayList.add(new VerticalModel(category,horizontalList9));
                            vRViewAdapter.notifyDataSetChanged();
                        }

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query10.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int x=0;
                if (snapshot.exists()){
                    for (DataSnapshot snp:snapshot.getChildren()){
                        Log.d(TAG, "onDataChange: name: "+snp.child("pname").getValue().toString());
                        String name = snp.child("pname").getValue().toString();
                        String price = snp.child("price").getValue().toString();
                        String image = snp.child("image").getValue().toString();
                        String category = snp.child("category").getValue().toString();
                        String description = snp.child("description").getValue().toString();

                        HorizontalModel model10 = new HorizontalModel(name,price,description,image);
                        horizontalList10.add(model10);
                        x++;
                        if (snapshot.getChildrenCount() == x){
                            verticalArrayList.add(new VerticalModel(category,horizontalList10));
                            vRViewAdapter.notifyDataSetChanged();
                        }

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query11.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int x=0;
                if (snapshot.exists()){
                    for (DataSnapshot snp:snapshot.getChildren()){
                        Log.d(TAG, "onDataChange: name: "+snp.child("pname").getValue().toString());
                        String name = snp.child("pname").getValue().toString();
                        String price = snp.child("price").getValue().toString();
                        String image = snp.child("image").getValue().toString();
                        String category = snp.child("category").getValue().toString();
                        String description = snp.child("description").getValue().toString();

                        HorizontalModel model11 = new HorizontalModel(name,price,description,image);
                        horizontalList11.add(model11);
                        x++;
                        if (snapshot.getChildrenCount() == x){
                            verticalArrayList.add(new VerticalModel(category,horizontalList11));
                            vRViewAdapter.notifyDataSetChanged();
                        }

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query12.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int x=0;
                if (snapshot.exists()){
                    for (DataSnapshot snp:snapshot.getChildren()){
                        Log.d(TAG, "onDataChange: name: "+snp.child("pname").getValue().toString());
                        String name = snp.child("pname").getValue().toString();
                        String price = snp.child("price").getValue().toString();
                        String image = snp.child("image").getValue().toString();
                        String category = snp.child("category").getValue().toString();
                        String description = snp.child("description").getValue().toString();

                        HorizontalModel model12 = new HorizontalModel(name,price,description,image);
                        horizontalList12.add(model12);
                        x++;
                        if (snapshot.getChildrenCount() == x){
                            verticalArrayList.add(new VerticalModel(category,horizontalList12));
                            vRViewAdapter.notifyDataSetChanged();
                        }

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query13.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int x=0;
                if (snapshot.exists()){
                    for (DataSnapshot snp:snapshot.getChildren()){
                        Log.d(TAG, "onDataChange: name: "+snp.child("pname").getValue().toString());
                        String name = snp.child("pname").getValue().toString();
                        String price = snp.child("price").getValue().toString();
                        String image = snp.child("image").getValue().toString();
                        String category = snp.child("category").getValue().toString();
                        String description = snp.child("description").getValue().toString();

                        HorizontalModel model13 = new HorizontalModel(name,price,description,image);
                        horizontalList13.add(model13);
                        x++;
                        if (snapshot.getChildrenCount() == x){
                            verticalArrayList.add(new VerticalModel(category,horizontalList13));
                            vRViewAdapter.notifyDataSetChanged();
                        }

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query14.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int x=0;
                if (snapshot.exists()){
                    for (DataSnapshot snp:snapshot.getChildren()){
                        Log.d(TAG, "onDataChange: name: "+snp.child("pname").getValue().toString());
                        String name = snp.child("pname").getValue().toString();
                        String price = snp.child("price").getValue().toString();
                        String image = snp.child("image").getValue().toString();
                        String category = snp.child("category").getValue().toString();
                        String description = snp.child("description").getValue().toString();

                        HorizontalModel model14 = new HorizontalModel(name,price,description,image);
                        horizontalList14.add(model14);
                        x++;
                        if (snapshot.getChildrenCount() == x){
                            verticalArrayList.add(new VerticalModel(category,horizontalList14));
                            vRViewAdapter.notifyDataSetChanged();
                        }

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query15.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int x=0;
                if (snapshot.exists()){
                    for (DataSnapshot snp:snapshot.getChildren()){
                        Log.d(TAG, "onDataChange: name: "+snp.child("pname").getValue().toString());
                        String name = snp.child("pname").getValue().toString();
                        String price = snp.child("price").getValue().toString();
                        String image = snp.child("image").getValue().toString();
                        String category = snp.child("category").getValue().toString();
                        String description = snp.child("description").getValue().toString();

                        HorizontalModel model15 = new HorizontalModel(name,price,description,image);
                        horizontalList15.add(model15);
                        x++;
                        if (snapshot.getChildrenCount() == x){
                            verticalArrayList.add(new VerticalModel(category,horizontalList15));
                            vRViewAdapter.notifyDataSetChanged();
                        }

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        query16.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int x=0;
                if (snapshot.exists()){
                    for (DataSnapshot snp:snapshot.getChildren()){
                        Log.d(TAG, "onDataChange: name: "+snp.child("pname").getValue().toString());
                        String name = snp.child("pname").getValue().toString();
                        String price = snp.child("price").getValue().toString();
                        String image = snp.child("image").getValue().toString();
                        String category = snp.child("category").getValue().toString();
                        String description = snp.child("description").getValue().toString();

                        HorizontalModel model16 = new HorizontalModel(name,price,description,image);
                        horizontalList16.add(model16);
                        x++;
                        if (snapshot.getChildrenCount() == x){
                            verticalArrayList.add(new VerticalModel(category,horizontalList16));
                            vRViewAdapter.notifyDataSetChanged();
                        }

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

}