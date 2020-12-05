package com.dwinuray.app_assignment.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.dwinuray.app_assignment.R;
import com.dwinuray.app_assignment.databases.Database;
import com.dwinuray.app_assignment.models.AssignmentAdapter;
import com.dwinuray.app_assignment.models.Assignments;

import java.util.ArrayList;
import java.util.List;


public class FragmentHome extends Fragment {

    private AssignmentAdapter adapter_off;
    private Database db;
    private List<Assignments> ListMahasiswa = new ArrayList<Assignments>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView rv = view.findViewById(R.id.rvAssignment);
        rv.setLayoutManager( new LinearLayoutManager( getActivity() ));

        db = new Database(getActivity());
        adapter_off = new AssignmentAdapter(getActivity(), ListMahasiswa );

        rv.setAdapter(adapter_off);

//        rv.setClickable(true);
        ListMahasiswa.clear();
        List<Assignments> contacts = db.getAllAssignment();
        for (Assignments cn : contacts) {
            Assignments judulModel = new Assignments(null, null, null, null, null, null);
            judulModel.setId(cn.getId());
            judulModel.setName(cn.getName());
            judulModel.setDescription(cn.getDescription().equals("") ? "Catatan Kosong" : cn.getDescription());
            judulModel.setType(cn.getType().equals("TA") ? "Tugas Akhir " : cn.getType());
            judulModel.setDate(cn.getDate());
            judulModel.setStatus(cn.getStatus());
            ListMahasiswa.add(judulModel);
            if ((ListMahasiswa.isEmpty()))
                Toast.makeText(getActivity(), "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {


            }
        }

        ConstraintLayout cl = view.findViewById(R.id.default_view);
        if ( ListMahasiswa.isEmpty() ) {

            cl.setVisibility(view.VISIBLE);
        } else {

            cl.setVisibility(view.GONE);
        }

    }

    public void set(){

    }
}
