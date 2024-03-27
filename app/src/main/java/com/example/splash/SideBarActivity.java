package com.example.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import java.io.File;


public class SideBarActivity extends HomePage {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_bar);


        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id==R.id.home_menu){
                    loadFragment(new AFragment());
                }
                else if(id==R.id.video_menu){
                    startActivity(new Intent(SideBarActivity.this, UserInfoVCActivity.class));
                }
                else if(id==R.id.chat_bot){
                    startActivity(new Intent(SideBarActivity.this,HomePage.class));
                }
                else if(id==R.id.login_menu){
                    startActivity(new Intent(SideBarActivity.this,MainActivity.class));
                }
                else if(id==R.id.logout_menu){
                    signOutFunction();
                }
                else if(id==R.id.share_menu){
                    ApplicationInfo api = getApplicationContext().getApplicationInfo();
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    String shareBody = "Here is the share content";
                    intent.setType("text/plain");
                    intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Try subject");
                    intent.putExtra(android.content.Intent.EXTRA_TEXT,shareBody);
                    startActivity(Intent.createChooser(intent, "ShareVia"));
                }
                else if(id==R.id.refer_menu){
                    ApplicationInfo api = getApplicationContext().getApplicationInfo();
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    String shareBody = "Here is the share content";
                    intent.setType("text/plain");
                    intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Try subject");
                    intent.putExtra(android.content.Intent.EXTRA_TEXT,shareBody);
                    startActivity(Intent.createChooser(intent, "ShareVia"));
                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.container,fragment);
        ft.commit();
    }
    void signOutFunction(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                LoginManager.getInstance().logOut();
                finish();
                startActivity(new Intent(SideBarActivity.this,MainActivity.class));
            }
        });
    }


}