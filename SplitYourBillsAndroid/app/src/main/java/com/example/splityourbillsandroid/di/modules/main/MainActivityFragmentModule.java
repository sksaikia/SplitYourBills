package com.example.splityourbillsandroid.di.modules.main;



import com.example.splityourbillsandroid.ui.main.friends.FriendsFragment;
import com.example.splityourbillsandroid.ui.main.spaces.SpacesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityFragmentModule {


    @ContributesAndroidInjector
    abstract FriendsFragment bindFriendsFragment();
    @ContributesAndroidInjector
    abstract SpacesFragment bindSpacesFragment();

}
