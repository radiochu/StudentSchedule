package com.afenstermaker.c868capstoneproject.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.afenstermaker.c868capstoneproject.Entity.Assignment;

import java.util.List;

@Dao
public interface AssignmentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Assignment assignment);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Assignment assignment);

    @Delete
    void delete(Assignment assignment);

    @Query("SELECT * FROM Assignment ORDER BY assignmentID ASC")
    LiveData<List<Assignment>> getAllAssignments();

    @Query("SELECT * FROM Assignment WHERE courseID = :courseID")
    LiveData<List<Assignment>> getAssignmentsByCourse(int courseID);

    @Query("SELECT * FROM Assignment WHERE assignmentID = :assignmentID")
    Assignment getAssignmentByID(int assignmentID);

    @Query("SELECT * FROM Assignment ORDER BY assignmentDate ASC")
    LiveData<List<Assignment>> getAssignmentsByDate();
}
