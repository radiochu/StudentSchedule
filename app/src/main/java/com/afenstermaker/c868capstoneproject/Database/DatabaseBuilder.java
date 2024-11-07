package com.afenstermaker.c868capstoneproject.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.afenstermaker.c868capstoneproject.DAO.AssignmentDAO;
import com.afenstermaker.c868capstoneproject.DAO.CourseDAO;
import com.afenstermaker.c868capstoneproject.Entity.Assignment;
import com.afenstermaker.c868capstoneproject.Entity.Course;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Course.class, Assignment.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class DatabaseBuilder extends RoomDatabase {
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile DatabaseBuilder INSTANCE;

    static DatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseBuilder.class, "plannerDB")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract CourseDAO courseDAO();

    public abstract AssignmentDAO assignmentDAO();
}