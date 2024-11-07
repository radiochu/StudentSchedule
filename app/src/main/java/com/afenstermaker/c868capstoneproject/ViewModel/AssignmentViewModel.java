package com.afenstermaker.c868capstoneproject.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.afenstermaker.c868capstoneproject.Database.Repository;
import com.afenstermaker.c868capstoneproject.Entity.Assignment;

import java.util.List;

public class AssignmentViewModel extends AndroidViewModel {
    private final Repository repo;
    private final LiveData<List<Assignment>> allAssignments;

    public AssignmentViewModel(Application application) {
        super(application);
        repo = new Repository(application);
        allAssignments = repo.getAllAssignments();
    }

    public LiveData<List<Assignment>> getAllAssignments() {
        return allAssignments;
    }

    public LiveData<List<Assignment>> getAssignmentsByCourse(int courseID) {
        return repo.getCourseAssignments(courseID);
    }

    public Assignment getAssignmentByID(int assignmentID) {
        return repo.getAssignmentByID(assignmentID);
    }

    public LiveData<List<Assignment>> getAssignmentsByDate() {
        return repo.getAssignmentsByDate();
    }

    public int insert(Assignment assignment) {
        repo.insertAssignment(assignment);
        return assignment.getAssignmentID();
    }

    public void update(Assignment assignment) {
        repo.updateAssignment(assignment);
    }

    public void delete(int assignmentID) {
        repo.deleteAssignment(assignmentID);
    }
}

