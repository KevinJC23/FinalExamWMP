package com.example.finalexam;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewEnrollmentActivity extends AppCompatActivity {

    private ListView listViewSubjects;
    private TextView tvTotalCredits;
    private SubjectAdapter subjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_enrollment);

        // Initialize UI elements
        listViewSubjects = findViewById(R.id.listViewSubjects);
        tvTotalCredits = findViewById(R.id.tvTotalCredits);

        // Get the data passed from EnrollmentActivity
        ArrayList<SubjectModel> selectedSubjects = (ArrayList<SubjectModel>) getIntent().getSerializableExtra("selectedSubjects");
        int totalCredits = getIntent().getIntExtra("totalCredits", 0);

        tvTotalCredits.setText("Total Credits: " + totalCredits);

        subjectAdapter = new SubjectAdapter(this, selectedSubjects);
        listViewSubjects.setAdapter(subjectAdapter);

        saveEnrollmentToFirestore(selectedSubjects, totalCredits);
    }

    private void saveEnrollmentToFirestore(ArrayList<SubjectModel> selectedSubjects, int totalCredits) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        EnrollmentModel enrollment = new EnrollmentModel(selectedSubjects, totalCredits);
        db.collection("Enrollments")
                .add(enrollment)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(ViewEnrollmentActivity.this, "Enrollment saved successfully!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(ViewEnrollmentActivity.this, "Failed to save enrollment", Toast.LENGTH_SHORT).show();
                });
    }
}
