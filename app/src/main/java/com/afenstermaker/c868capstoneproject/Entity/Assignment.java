package com.afenstermaker.c868capstoneproject.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "assignment")
public class Assignment {
    @PrimaryKey(autoGenerate = true)
    private int assignmentID;
    private String courseName;
    private int courseID;
    private String assignmentName;
    private String assignmentType;
    private Date assignmentDate;

    public Assignment(int assignmentID, String courseName, int courseID, String assignmentName, String assignmentType, Date assignmentDate) {
        this.assignmentID = assignmentID;
        this.courseName = courseName;
        this.courseID = courseID;
        this.assignmentName = assignmentName;
        this.assignmentType = assignmentType;
        this.assignmentDate = assignmentDate;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    public Date getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Date assignmentDate) {
        this.assignmentDate = assignmentDate;
    }
}
