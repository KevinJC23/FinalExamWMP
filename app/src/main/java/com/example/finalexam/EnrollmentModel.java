package com.example.finalexam;

import java.util.ArrayList;

public class EnrollmentModel {
    private String enrollmentId;
    private String studentId;
    private String subjectId;

    public EnrollmentModel(String enrollmentId, String studentId, String subjectId) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    public EnrollmentModel(ArrayList<SubjectModel> selectedSubjects, int totalCredits) {}

    // Enrollment ID
    public String getEnrollmentId() {
        return this.enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    // Student ID
    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    // Subject ID
    public String getSubjectId() {
        return this.subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
}
