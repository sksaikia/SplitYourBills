package com.example.splityourbillsandroid.di.modules.main;



import com.example.splityourbillsandroid.ui.main.AddPeopleForSpace.AddPeopleFragment;
import com.example.splityourbillsandroid.ui.main.SpaceMembers.SpaceMembersFragment;
import com.example.splityourbillsandroid.ui.main.createSpace.CreateSpaceFragment;
import com.example.splityourbillsandroid.ui.main.friends.FriendsFragment;
import com.example.splityourbillsandroid.ui.main.newTransaction.NewTransactionFragment;
import com.example.splityourbillsandroid.ui.main.notifications.NotificationsFragment;
import com.example.splityourbillsandroid.ui.main.profile.ProfileFragment;
import com.example.splityourbillsandroid.ui.main.spaceDetails.SpaceDetailsFragment;
import com.example.splityourbillsandroid.ui.main.spaces.SpacesFragment;
import com.example.splityourbillsandroid.ui.main.transactionAmountManual.TransactionAmountFragment;
import com.example.splityourbillsandroid.ui.main.transactionDetails.TransactionDetailsFragment;
import com.example.splityourbillsandroid.ui.main.viewDetailsTXN.ViewDetailsTransactionFragment;

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
    @ContributesAndroidInjector
    abstract SpaceDetailsFragment bindSpaceDetailsFragment();
    @ContributesAndroidInjector
    abstract CreateSpaceFragment bindCreateSpaceFragment();
    @ContributesAndroidInjector
    abstract AddPeopleFragment bindAddPeopleFragment();
    @ContributesAndroidInjector
    abstract SpaceMembersFragment bindSpaceMembersFragment();
    @ContributesAndroidInjector
    abstract NewTransactionFragment bindNewTransactionFragment();
    @ContributesAndroidInjector
    abstract TransactionAmountFragment bindTransactionAmountFragment();
    @ContributesAndroidInjector
    abstract ViewDetailsTransactionFragment bindViewDetailsTXNFragment();
    @ContributesAndroidInjector
    abstract TransactionDetailsFragment bindTXNDetailsFragment();

}
