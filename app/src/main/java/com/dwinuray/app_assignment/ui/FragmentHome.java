/*
*  Dev - By Dwi Nur Cahyo
*  Github : github.com/dwinuray
* */

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
    private List<Assignments> listAssignment = new ArrayList<Assignments>();

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
        adapter_off = new AssignmentAdapter(getActivity(), listAssignment );

        rv.setAdapter(adapter_off);


        listAssignment.clear();
        List<Assignments> rowAssignment = db.getAllAssignment();
        for (Assignments row : rowAssignment) {
            Assignments assignmentObject = new Assignments(null, null, null, null, null, null);
            assignmentObject.setId(row.getId());
            assignmentObject.setName(row.getName());
            assignmentObject.setDescription(row.getDescription().equals("") ? "Catatan Kosong" : row.getDescription());
            assignmentObject.setType(row.getType().equals("TA") ? "Tugas Akhir " : row.getType());
            assignmentObject.setDate(row.getDate());
            assignmentObject.setStatus(row.getStatus());
            listAssignment.add(assignmentObject);
            if ((listAssignment.isEmpty()))
                Toast.makeText(getActivity(), "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {


            }
        }

        ConstraintLayout cl = view.findViewById(R.id.default_view);
        if ( listAssignment.isEmpty() ) {

            cl.setVisibility(view.VISIBLE);
        } else {

            cl.setVisibility(view.GONE);
        }

    }

    /*
     *  Dev - By Dwi Nur Cahyo
     *  Github : github.com/dwinuray
     * */
}
