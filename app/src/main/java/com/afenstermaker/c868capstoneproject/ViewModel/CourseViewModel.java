package com.afenstermaker.c868capstoneproject.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.afenstermaker.c868capstoneproject.Database.Repository;
import com.afenstermaker.c868capstoneproject.Entity.Course;
import com.afenstermaker.c868capstoneproject.Entity.Course;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {
    private final Repository repo;
    private final LiveData<List<Course>> allCourses;

    public CourseViewModel(Application application) {
        super(application);
        repo = new Repository(application);
        allCourses = repo.getAllCourses();
    }

    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    public Course getCourseByID(int courseID) {
        return repo.getCourseByID(courseID);
    }

    public int insert(Course course) {
        repo.insertCourse(course);
        return course.getCourseID();
    }

    public void update(Course course) {
        repo.updateCourse(course);
    }

    public void delete(int courseID) {
        repo.deleteCourse(courseID);
    }

    public LiveData<List<Course>> searchCourses(String searchQuery) {
        return repo.searchCourses(searchQuery);
    }

    public LiveData<List<Course>> getCoursesByDate() {
        return repo.getCoursesByDate();
    }
}
