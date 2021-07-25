package com.example.splityourbillsandroid.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.ui.main.friends.FriendsFragment;
import com.example.splityourbillsandroid.ui.main.spaces.SpacesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {


    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;


    @Inject
    MainViewModel viewModel;

    FragmentManager fragmentManager;

    @Inject
    SpacesFragment spacesFragment;

    @Inject
    FriendsFragment friendsFragment;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidInjection.inject(this);

        fragmentManager = getSupportFragmentManager();

        initFrag(spacesFragment);

        Log.d(TAG, "onCreate: intialized spaces Fragment" );
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_space:
                    return true;
                case R.id.nav_friends:

                    return true;
                case R.id.nav_notifications:

                    return true;
                case R.id.nav_profile:

                    return true;
            }
            return false;
        }
    };

    private void initFrag(Fragment fragment) {

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame_layout_main, fragment);
        ft.commit();


    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }




}