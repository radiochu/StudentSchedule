package com.afenstermaker.c868capstoneproject.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.afenstermaker.c868capstoneproject.Entity.Course;

import java.util.List;

@Dao
public interface CourseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Course course);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM course ORDER BY courseID ASC")
    LiveData<List<Course>> getAllCourses();

    @Query("SELECT courseID FROM course")
    LiveData<List<Integer>> getAllCourseIDs();

    @Query("SELECT courseName FROM course")
    LiveData<List<String>> getAllCourseNames();

    @Query("SELECT * FROM course WHERE courseID = :courseID")
    Course getCourseByID(int courseID);

    @Query("SELECT * FROM course WHERE teacherName || teacherEmail || teacherPhone LIKE '%' || :searchQuery || '%'")
    LiveData<List<Course>> searchCourses(String searchQuery);

    @Query("SELECT * FROM course ORDER BY startDate ASC")
    LiveData<List<Course>> getCoursesByDate();
}
