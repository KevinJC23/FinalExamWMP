package com.example.finalexam;

import java.util.List;

public class EnrollmentModel {
    private List<SubjectModel> subjects;
    private int totalCredits;

    public EnrollmentModel() {
    }

    public EnrollmentModel(List<SubjectModel> subjects, int totalCredits) {
        this.subjects = subjects;
        this.totalCredits = totalCredits;
    }

    public List<SubjectModel> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectModel> subjects) {
        this.subjects = subjects;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }
}
