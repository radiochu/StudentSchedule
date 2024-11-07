package com.afenstermaker.c868capstoneproject.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "course")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private String courseName;
    private String classroom;
    private String teacherName;
    private String teacherPhone;
    private String teacherEmail;
    private String courseNotes;
    private Date startDate;

    public Course(int courseID, String courseName, String classroom, String teacherName, String teacherPhone, String teacherEmail, String courseNotes, Date startDate) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.classroom = classroom;
        this.teacherName = teacherName;
        this.teacherPhone = teacherPhone;
        this.teacherEmail = teacherEmail;
        this.courseNotes = courseNotes;
        this.startDate = startDate;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getCourseNotes() {
        return courseNotes;
    }

    public void setCourseNotes(String courseNotes) {
        this.courseNotes = courseNotes;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @NonNull
    @Override
    public String toString() {
        return courseID + ": " + courseName;
    }
}
