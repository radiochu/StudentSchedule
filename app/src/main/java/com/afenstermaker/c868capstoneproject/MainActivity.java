package com.afenstermaker.c868capstoneproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.afenstermaker.c868capstoneproject.UI.Assignment.AssignmentList;
import com.afenstermaker.c868capstoneproject.UI.Course.CourseList;
import com.afenstermaker.c868capstoneproject.UI.Reports.Reports;
import com.afenstermaker.c868capstoneproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Button toCourseList;
    private Button toAssignmentList;
    private Button toReports;
    public static int alertNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewToBind = binding.getRoot();
        setContentView(viewToBind);

        toCourseList = binding.myClassesButton;
        toAssignmentList = binding.myAssignmentsButton;
        toReports = binding.reportsButton;

        toCourseList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CourseList.class);
            startActivity(intent);
        });

        toAssignmentList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AssignmentList.class);
            startActivity(intent);
        });

        toReports.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Reports.class);
            startActivity(intent);
        });
    }
}