package com.afenstermaker.c868capstoneproject.UI.Reports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.afenstermaker.c868capstoneproject.R;
import com.afenstermaker.c868capstoneproject.databinding.ActivityReportsBinding;

public class Reports extends AppCompatActivity {
    private CardView assignmentScheduleReport;
    private CardView courseScheduleReport;
    private CardView teacherContactReport;
    private ActivityReportsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        binding = ActivityReportsBinding.inflate(getLayoutInflater());
        View viewToBind = binding.getRoot();
        setContentView(viewToBind);

        assignmentScheduleReport = binding.assignmentScheduleCV;
        courseScheduleReport = binding.classScheduleCV;
        teacherContactReport = binding.contactListCV;

        assignmentScheduleReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reports.this, AssignmentScheduleReport.class);
                startActivity(intent);
            }
        });

        courseScheduleReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reports.this, ClassScheduleReport.class);
                startActivity(intent);
            }
        });

        teacherContactReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reports.this, ContactListReport.class);
                startActivity(intent);
            }
        });
    }



}