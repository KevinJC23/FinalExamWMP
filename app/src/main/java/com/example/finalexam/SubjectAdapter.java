package com.example.finalexam;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> implements ListAdapter {

    private Context context;
    private ArrayList<SubjectModel> subjectList;
    private ArrayList<SubjectModel> selectedSubjects;
    private int maxCredits;

    public SubjectAdapter(Context context, ArrayList<SubjectModel> subjectList, int maxCredits) {
        this.context = context;
        this.subjectList = subjectList;
        this.selectedSubjects = new ArrayList<>();
        this.maxCredits = maxCredits;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_subject, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        SubjectModel subject = subjectList.get(position);
        holder.tvSubjectName.setText(subject.getSubjectName());
        holder.tvCredits.setText(String.valueOf(subject.getCredits()));

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(selectedSubjects.contains(subject));

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                int totalCredits = getTotalCredits() + subject.getCredits();
                if (totalCredits <= maxCredits) {
                    selectedSubjects.add(subject);
                } else {
                    holder.checkBox.setChecked(false);
                    Toast.makeText(context, "Maximum credit limit exceeded!", Toast.LENGTH_SHORT).show();
                }
            } else {
                selectedSubjects.remove(subject);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public ArrayList<SubjectModel> getSelectedSubjects() {
        return selectedSubjects;
    }

    public int getTotalCredits() {
        int total = 0;
        for (SubjectModel subject : selectedSubjects) {
            total += subject.getCredits();
        }
        return total;
    }

    public void deselectSubject(SubjectModel subject) {
        selectedSubjects.remove(subject);
        notifyDataSetChanged();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubjectName, tvCredits;
        CheckBox checkBox;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubjectName = itemView.findViewById(R.id.tvSubjectName);
            tvCredits = itemView.findViewById(R.id.tvCredits);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
