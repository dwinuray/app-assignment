package com.dwinuray.app_assignment.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dwinuray.app_assignment.R;
import com.dwinuray.app_assignment.databases.Database;
import com.dwinuray.app_assignment.models.Assignments;


public class FragmentAdd extends Fragment {

    private Database db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // init
        final RadioGroup type;
        final EditText name, description, date;

        // find id
        name = view.findViewById(R.id.txtName);
        description = view.findViewById(R.id.txtDescription);
        date = view.findViewById(R.id.txtDate);
        type = view.findViewById(R.id.radioGroup);

        Button btnSave =  view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // radio
                RadioButton radioButton;
                final int getSelectRadio = type.getCheckedRadioButtonId();
                radioButton = getActivity().findViewById(getSelectRadio);

                // radio button
                String alert = "Informasi ";
                Boolean statusInsert = true;
                if ( String.valueOf(name.getText()).equals("") ) {

                    name.requestFocus();
                    alert += "nama ";

                    statusInsert = false;
                }

                if ( String.valueOf(date.getText()).equals("") ) {

                    date.requestFocus();
                    alert += "tanggal ";

                    statusInsert = false;
                }

                // check data before insert
                if ( statusInsert ) {

                    // value
                    String setType, setName, setDescription, setDate, setStatus;
                    setType = String.valueOf(radioButton.getText());
                    setName        = String.valueOf(name.getText());
                    setDescription = String.valueOf(description.getText());
                    setDate        = String.valueOf(date.getText());
                    setStatus      = "progress";

                    // db
                    db = new Database(getActivity());
                    db.onInsertAssignment(new Assignments(null, setType, setName, setDescription, setDate, setStatus));
                    Toast.makeText(getActivity(), "200", Toast.LENGTH_SHORT).show();

                    // back to first fragment
                    FragmentHome fragmentHome = new FragmentHome();
                    FragmentManager fm = getParentFragmentManager();

                    if ( fm != null ) {
                        fm.beginTransaction().replace(R.id.fragment, fragmentHome, FragmentHome.class.getSimpleName())
                                .addToBackStack(null)
                                .commit();
                    }
                } else {

                    Toast.makeText(getActivity(), alert + "harap diisi", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
