package com.example.finalexam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EnrollmentSummaryAdapter extends RecyclerView.Adapter<EnrollmentSummaryAdapter.SummaryViewHolder> {

    private Context context;
    private ArrayList<SubjectModel> selectedSubjects;

    public EnrollmentSummaryAdapter(Context context, ArrayList<SubjectModel> selectedSubjects) {
        this.context = context;
        this.selectedSubjects = selectedSubjects;
    }

    @NonNull
    @Override
    public SummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_enrollment_summary, parent, false);
        return new SummaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryViewHolder holder, int position) {
        SubjectModel subject = selectedSubjects.get(position);
        holder.tvSubjectName.setText(subject.getSubjectName());
        holder.tvCredits.setText(String.valueOf(subject.getCredits()));
    }

    @Override
    public int getItemCount() {
        return selectedSubjects.size();
    }

    public int getTotalCredits() {
        int total = 0;
        for (SubjectModel subject : selectedSubjects) {
            total += subject.getCredits();
        }
        return total;
    }

    public static class SummaryViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubjectName, tvCredits;

        public SummaryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubjectName = itemView.findViewById(R.id.tvSubjectName);
            tvCredits = itemView.findViewById(R.id.tvCredits);
        }
    }
}
