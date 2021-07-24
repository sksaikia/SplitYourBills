package com.example.splityourbillsandroid.di.modules.main;



import com.example.splityourbillsandroid.ui.main.friends.FriendsFragment;
import com.example.splityourbillsandroid.ui.main.notifications.NotificationsFragment;
import com.example.splityourbillsandroid.ui.main.profile.ProfileFragment;
import com.example.splityourbillsandroid.ui.main.spaces.SpacesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityFragmentModule {


    @ContributesAndroidInjector
    abstract FriendsFragment bindFriendsFragment();
    @ContributesAndroidInjector
    abstract SpacesFragment bindSpacesFragment();
    @ContributesAndroidInjector
    abstract NotificationsFragment bindNotificationFragment();
    @ContributesAndroidInjector
    abstract ProfileFragment bindProfileFragment();

}
