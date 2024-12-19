package com.example.finalexam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnSubmit;
    private TextView tvTotalCredits;
    private SubjectAdapter subjectAdapter;
    private FirebaseAuth mAuth;
    private DatabaseReference database;

    private ArrayList<SubjectModel> subjectList = new ArrayList<>();
    private int totalCredits = 0;
    private final int MAX_CREDITS = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment);

        recyclerView = findViewById(R.id.recyclerView);
        btnSubmit = findViewById(R.id.btnSubmitEnrollment);
        tvTotalCredits = findViewById(R.id.tvTotalCredits);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        subjectAdapter = new SubjectAdapter(this, subjectList, MAX_CREDITS);
        recyclerView.setAdapter(subjectAdapter);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        loadSubjects();

        btnSubmit.setOnClickListener(view -> submitEnrollment());
    }

    private void loadSubjects() {
        subjectList.add(new SubjectModel("S001", "Artificial Intelligence", 3));
        subjectList.add(new SubjectModel("S002", "Wireless and Mobile Programming", 3));
        subjectList.add(new SubjectModel("S003", "Network Security", 3));
        subjectList.add(new SubjectModel("S004", "Numerical Methods", 3));
        subjectList.add(new SubjectModel("S005", "Software Engineering", 3));
        subjectList.add(new SubjectModel("S006", "Data Structure and Algorithm", 3));
        subjectList.add(new SubjectModel("S007", "3D Computer Graphics and Animation", 3));
        subjectList.add(new SubjectModel("S008", "Computer Network", 3));
        subjectList.add(new SubjectModel("S009", "Object Oriented and Visual Programming", 3));
        subjectList.add(new SubjectModel("S010", "Database System", 3));
        subjectList.add(new SubjectModel("S011", "Probability and Statistics", 3));
        subjectList.add(new SubjectModel("S012", "Economic Survival 2: Business Launch / Internship Experience", 3));
        subjectList.add(new SubjectModel("S013", "Server-Side Internet Programming", 3));
        subjectList.add(new SubjectModel("S014", "Linear Algebra", 3));

        subjectAdapter.notifyDataSetChanged();
    }

    private void submitEnrollment() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = user.getUid();
        List<SubjectModel> selectedSubjects = subjectAdapter.getSelectedSubjects();
        int totalCredits = subjectAdapter.getTotalCredits();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        EnrollmentModel enrollment = new EnrollmentModel(new ArrayList<>(selectedSubjects), totalCredits);
        db.collection("Enrollments")
                .add(enrollment)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(EnrollmentActivity.this, "Enrollment submitted successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(EnrollmentActivity.this, ViewEnrollmentActivity.class);
                    intent.putExtra("selectedSubjects", new ArrayList<>(selectedSubjects));
                    intent.putExtra("totalCredits", totalCredits);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(EnrollmentActivity.this, "Failed to submit enrollment", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                });
    }

}
