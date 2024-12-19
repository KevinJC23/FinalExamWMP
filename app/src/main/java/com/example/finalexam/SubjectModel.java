package com.example.finalexam;

public class SubjectModel {
    private String subjectId;
    private String subjectName;
    private Integer credits;

    public SubjectModel(String subjectId, String subjectName, Integer credits) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.credits = credits;
    }

    public SubjectModel() {}

    // SubjectID
    public String getSubjectId() {
        return this.subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    // Subject Name
    public String getSubjectName() {
        return this.subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    // Credits
    public Integer getCredits() {
        return this.credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }
}
