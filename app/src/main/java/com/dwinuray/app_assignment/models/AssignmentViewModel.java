package com.dwinuray.app_assignment.models;

/*
 *  Dev - By Dwi Nur Cahyo
 *  Github : github.com/dwinuray
 *
 * */

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dwinuray.app_assignment.databases.Database;

public class AssignmentViewModel extends ViewModel {

    private static final String TAG = AssignmentViewModel.class.getSimpleName();
    MutableLiveData<Assignments> assignments;

    public MutableLiveData<Assignments> getAssignments() {

        if ( assignments == null ) {

            assignments = new MutableLiveData<>();
            setAssignments();
        }
        return assignments;
    }

    private void setAssignments() {


        this.assignments = assignments;
    }
}
