package com.example.finalexam;

public class SubjectModel {
    private String id;
    private String subjectName;
    private int credits;

    public SubjectModel() {

    }

    public SubjectModel(String id, String subjectName, int credits) {
        this.id = id;
        this.subjectName = subjectName;
        this.credits = credits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
