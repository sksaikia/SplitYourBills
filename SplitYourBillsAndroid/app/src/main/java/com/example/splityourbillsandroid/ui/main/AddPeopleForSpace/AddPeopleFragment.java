package com.example.splityourbillsandroid.ui.main.AddPeopleForSpace;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.data.models.spaces.AddNewSpaceResponse;
import com.example.splityourbillsandroid.ui.main.MainViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class AddPeopleFragment extends Fragment {


    MaterialButton importContactsBTN,saveBTN;
    TextView spaceNameTV;
    ChipGroup chipGroup;

    public static final int PICK_CONTACT = 1;
    private static final String TAG = "AddPeopleFragment";

    List<Contacts> contactList;

    long spaceId = 0;
    String spaceName = "";

    @Inject
    MainViewModel viewModel;

    @Inject
    public AddPeopleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_people, container, false);

        AndroidSupportInjection.inject(this);
        initializeViews(view);

        subscribeForSpaceId();

        contactList = new ArrayList<>();


        importContactsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPermissionAndAddPeopleFromContacts();
            }
        });

        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMembers();
            }
        });



        return view;
    }

    private void addMembers() {
        for (Contacts c:contactList){
            System.out.println(c.toString());

        }

    }

    private void subscribeForSpaceId() {
       viewModel.getSpaceDetails().observe(this, new Observer<AddNewSpaceResponse>() {
           @Override
           public void onChanged(AddNewSpaceResponse addNewSpaceResponse) {
               spaceName = addNewSpaceResponse.getSpaceName();
               spaceId = addNewSpaceResponse.getSpaceId();
               spaceNameTV.setText(spaceName);
           }
       });
    }

    private void initializeViews(View view) {
        importContactsBTN = view.findViewById(R.id.btn_import_contacts);
        saveBTN = view.findViewById(R.id.btn_save);
        spaceNameTV = view.findViewById(R.id.tv_space_name);
        chipGroup = view.findViewById(R.id.chip_group);
    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    private void getPermissionAndAddPeopleFromContacts() {
        Dexter.withActivity(getActivity()).withPermission(Manifest.permission.READ_CONTACTS).
                withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select a photo"), RESULT_CODE_IMAGE);
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);

            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
              //  Toast.makeText(getActivity(), "Enable Storage read permission", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

            }
        }).check();
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT) :
                if (resultCode == Activity.RESULT_OK) {

                    Uri contactData = data.getData();
                    String phoneNo = "";
                    Cursor c =  getActivity().managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {


                        String id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                        String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                        if (hasPhone.equalsIgnoreCase("1")) {
                            Cursor phones = getActivity().getContentResolver().query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,
                                    null, null);
                            phones.moveToFirst();
                            String cNumber = phones.getString(phones.getColumnIndex("data1"));
                            System.out.println("number is:"+cNumber);
                            Log.d(TAG, "onActivityResult: " + cNumber);

                            StringBuffer stringBuffer = new StringBuffer("");
                            for (char d:cNumber.toCharArray()){
                                if(d>='0' && d<='9')
                                    stringBuffer.append(d);
                            }
                            phoneNo = stringBuffer.toString();
                            Log.d(TAG, "onActivityResult: " + phoneNo);
                        }
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        if (phoneNo!="" && phoneNo.length()==10){
                            contactList.add(new Contacts(name,phoneNo));
                            Chip chip = new Chip(getActivity());
                            chip.setText(name);
                            chipGroup.addView(chip);
                        }

                        Log.d(TAG, "onActivityResult: " +  name);
                    }
                }
                break;
        }
    }


}