package com.dwinuray.app_assignment.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dwinuray.app_assignment.R;
import com.dwinuray.app_assignment.databases.Database;
import com.dwinuray.app_assignment.models.Assignments;

public class FragmentDetail extends Fragment {

    public static String ID   = "id";
    public static String TYPE = "type";
    public static String NAME = "name";
    public static String DESC = "desc";
    public static String DATE = "date";
    public static String STATUS = "status";

    TextView lblName, lblDesc, lblDate, lblType, lblStatus, tvUpdate, tvDelete;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_detail, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // init
        lblName = view.findViewById(R.id.lblName);
        lblDesc = view.findViewById(R.id.lblDesc);
        lblType = view.findViewById(R.id.lblType);
        lblStatus= view.findViewById(R.id.lblStatus);
        lblDate = view.findViewById(R.id.lblDate);


        tvDelete = view.findViewById(R.id.lblDelete);
        tvUpdate = view.findViewById(R.id.lblUpdate);




        // update
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentUpdate fragment = new FragmentUpdate();

                // bundle
                Bundle bundle = new Bundle();
                bundle.putString(fragment.ID, getArguments().getString(ID));
                bundle.putString(fragment.NAME, getArguments().getString(NAME));
                bundle.putString(fragment.DESC, getArguments().getString(DESC));
                bundle.putString(fragment.DATE, getArguments().getString(DATE));

                fragment.setArguments(bundle);

                FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fragment, FragmentUpdate.class.getSimpleName());
                transaction.addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            }
        });








        // delete
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Database db = new Database(getActivity());
                db.deleteAssignment( getArguments().getString(ID) );
                Toast.makeText(getActivity(), getArguments().getString(TYPE) + " - " + getArguments().getString(NAME) + " berhasil terhapus ",
                        Toast.LENGTH_LONG).show();

                FragmentHome fragment = new FragmentHome();
                FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fragment, FragmentHome.class.getSimpleName());
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String name, desc, type, status, date;
        name = getArguments().getString(NAME);
        desc = getArguments().getString(DESC);
        type = getArguments().getString(TYPE);
        date = getArguments().getString(DATE);
        status = getArguments().getString(STATUS);

        lblName.setText(name);
        lblDesc.setText(desc);
        lblDate.setText(date);
        lblStatus.setText(status.equals("progress") ? "Belum Selesai" : "Selesai");
        lblType.setText(type);
    }
}
