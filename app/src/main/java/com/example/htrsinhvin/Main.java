package com.example.htrsinhvin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.FrameLayout;

import com.example.htrsinhvin.ui.gT.GtFragment;
import com.example.htrsinhvin.ui.home.HomeFragment;
import com.example.htrsinhvin.ui.pass.PassFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.htrsinhvin.databinding.MainBinding;

public class Main extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private MainBinding binding;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    FrameLayout frameLayout;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gT, R.id.nav_rePass,R.id.nav_out)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
//
        frameLayout = findViewById(R.id.nav_host_fragment_content_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        fm = getSupportFragmentManager();

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        setupDrawerContent(navigationView);
//        HomeFragment main = new HomeFragment();
//        fm.beginTransaction().add(R.id.nav_host_fragment_content_main,main).commit();
//        NavigationView finalNavigationView = navigationView;
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem item) {
//
//                switch (item.getItemId()) {
//                    case R.id.nav_home:
////                        setTitle("HOME");
////                        HomeFragment main = new HomeFragment();
////                        fm.beginTransaction().replace(R.id.nav_host_fragment_content_main,main).commit();
//                        break;
//                    case R.id.nav_gT:
////                        setTitle("GIỚI THIỆU");
////                        GtFragment gtFragment = new GtFragment();
////                        fm.beginTransaction().replace(R.id.nav_host_fragment_content_main,gtFragment).commit();
//                        break;
//
//                    case R.id.nav_rePass:
////                        setTitle("ĐỔI MẬT KHẨU");
////                        PassFragment passFragment = new PassFragment();
////                        fm.beginTransaction().replace(R.id.nav_host_fragment_content_main,passFragment).commit();
//                        break;
//                    case R.id.nav_out:
//                        startActivity(new Intent(Main.this,PH18428_Login.class));
//                        finish();
//                        break;
//
//                }
//                // ẩn menu drawer khi bấm vào item
//                drawerLayout.closeDrawers();
//
//                return false;
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (toggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


//    private void selectedItemDrawer(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.nav_home:
//                setTitle("HOME");
//                HomeFragment main = new HomeFragment();
//                fm.beginTransaction().replace(R.id.nav_host_fragment_content_main,main).commit();
//                break;
//            case R.id.nav_gT:
//                setTitle("GIỚI THIỆU");
//                GtFragment gtFragment = new GtFragment();
//                fm.beginTransaction().replace(R.id.nav_host_fragment_content_main,gtFragment).commit();
//                break;
//
//            case R.id.nav_rePass:
//                setTitle("ĐỔI MẬT KHẨU");
//                PassFragment passFragment = new PassFragment();
//                fm.beginTransaction().replace(R.id.nav_host_fragment_content_main,passFragment).commit();
//                break;
//            case R.id.nav_out:
//                startActivity(new Intent(Main.this,PH18428_Login.class));
//                finish();
//                break;
//
//        }
//
//        item.setChecked(true);
//
//        drawerLayout.closeDrawers();
//    }
//
//    private void setupDrawerContent(NavigationView navigationView) {
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem item) {
//                selectedItemDrawer(item);
//                return true;
//            }
//        });
//    }
}