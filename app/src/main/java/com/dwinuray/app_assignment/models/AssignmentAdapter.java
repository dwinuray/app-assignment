package com.dwinuray.app_assignment.models;

/*
 *  Dev - By Dwi Nur Cahyo
 *  Github : github.com/dwinuray
 * */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.dwinuray.app_assignment.R;
import com.dwinuray.app_assignment.ui.FragmentAbout;
import com.dwinuray.app_assignment.ui.FragmentAdd;
import com.dwinuray.app_assignment.ui.FragmentDetail;

import org.w3c.dom.Text;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {

    private Context mtcx;
    private List<Assignments> assignmentsItemList;

    public AssignmentAdapter(Context mtcx, List<Assignments> assignmentsItemList) {
        this.mtcx = mtcx;
        this.assignmentsItemList = assignmentsItemList;
    }


    public List<Assignments> getAssignmentsItemList() {
        return assignmentsItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mtcx).inflate(R.layout.fragment_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Assignments assignments = getAssignmentsItemList().get(position);
        holder.lblName.setText(assignments.getName());
        holder.lblDesc.setText(assignments.getDescription());
        holder.lblType.setText(assignments.getType());
        holder.lblDate.setText(assignments.getDate());


        holder.bind(assignments);
    }

    @Override
    public int getItemCount() {
        return assignmentsItemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView lblName, lblDesc, lblType, lblDate, lblStatus;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            lblName = itemView.findViewById(R.id.lblName);
            lblDesc = itemView.findViewById(R.id.lblDesc);
            lblType = itemView.findViewById(R.id.lblType);
            lblDate = itemView.findViewById(R.id.lblDate);
        }

        public void bind( final Assignments item ) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentDetail fragmentDetail = new FragmentDetail();

                    // bundle
                    Bundle bundle = new Bundle();
                    bundle.putString(fragmentDetail.ID, item.getId());
                    bundle.putString(fragmentDetail.NAME, item.getName());
                    bundle.putString(fragmentDetail.DESC, item.getDescription());
                    bundle.putString(fragmentDetail.DATE, item.getDate());
                    bundle.putString(fragmentDetail.TYPE, item.getType());
                    bundle.putString(fragmentDetail.STATUS, item.getStatus());

                    fragmentDetail.setArguments(bundle);


                    FragmentTransaction transaction = ((FragmentActivity)mtcx).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment, fragmentDetail, FragmentDetail.class.getSimpleName());
                    transaction.addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

//                    Toast.makeText(itemView.getContext(), "Msg " + item.getStatus(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}


/*
 *  Dev - By Dwi Nur Cahyo
 *  Github : github.com/dwinuray
 * */
