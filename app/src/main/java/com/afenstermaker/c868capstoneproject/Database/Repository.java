package com.afenstermaker.c868capstoneproject.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.afenstermaker.c868capstoneproject.DAO.AssignmentDAO;
import com.afenstermaker.c868capstoneproject.DAO.CourseDAO;
import com.afenstermaker.c868capstoneproject.Entity.Assignment;
import com.afenstermaker.c868capstoneproject.Entity.Course;

import java.util.List;

public class Repository {
    private final CourseDAO courseDAO;
    private final AssignmentDAO assignmentDAO;
    private final LiveData<List<Course>> mAllCourses;
    private final LiveData<List<Assignment>> mAllAssignments;

    public Repository(Application application) {
        DatabaseBuilder db = DatabaseBuilder.getDatabase(application);
        courseDAO = db.courseDAO();
        assignmentDAO = db.assignmentDAO();
        mAllCourses = courseDAO.getAllCourses();
        mAllAssignments = assignmentDAO.getAllAssignments();
    }

    public LiveData<List<Course>> getAllCourses() {
        return mAllCourses;
    }

    public LiveData<List<Assignment>> getAllAssignments() {
        return mAllAssignments;
    }

    public LiveData<List<Assignment>> getCourseAssignments(int courseID) {
        return assignmentDAO.getAssignmentsByCourse(courseID);
    }

    public Course getCourseByID(int courseID) {
        return courseDAO.getCourseByID(courseID);
    }

    public Assignment getAssignmentByID(int assignmentID) {
        return assignmentDAO.getAssignmentByID(assignmentID);
    }

    public void insertCourse(Course course) {
        DatabaseBuilder.databaseWriteExecutor.execute(() -> {
            courseDAO.insert(course);
        });
    }

    public void updateCourse(Course course) {
        DatabaseBuilder.databaseWriteExecutor.execute(() -> {
            courseDAO.update(course);
        });
    }

    public void deleteCourse(int courseID) {
        DatabaseBuilder.databaseWriteExecutor.execute(() -> {
            Course course = courseDAO.getCourseByID(courseID);
            courseDAO.delete(course);
        });
    }

    public void insertAssignment(Assignment assignment) {
        DatabaseBuilder.databaseWriteExecutor.execute(() -> {
            assignmentDAO.insert(assignment);
        });
    }

    public void updateAssignment(Assignment assignment) {
        DatabaseBuilder.databaseWriteExecutor.execute(() -> {
            assignmentDAO.update(assignment);
        });
    }

    public void deleteAssignment(int assignmentID) {
        DatabaseBuilder.databaseWriteExecutor.execute(() -> {
            Assignment assignment = assignmentDAO.getAssignmentByID(assignmentID);
            assignmentDAO.delete(assignment);
        });
    }

    public LiveData<List<Assignment>> getAssignmentsByDate() {
        return assignmentDAO.getAssignmentsByDate();
    }

    public LiveData<List<Course>> searchCourses(String searchQuery) {
        return courseDAO.searchCourses(searchQuery);
    }

    public LiveData<List<Course>> getCoursesByDate() {
        return courseDAO.getCoursesByDate();
    }
}
