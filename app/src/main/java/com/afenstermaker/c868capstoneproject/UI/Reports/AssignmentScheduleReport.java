package com.afenstermaker.c868capstoneproject.UI.Reports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.afenstermaker.c868capstoneproject.R;
import com.afenstermaker.c868capstoneproject.UI.Assignment.AssignmentScheduleAdapter;
import com.afenstermaker.c868capstoneproject.ViewModel.AssignmentViewModel;
import com.afenstermaker.c868capstoneproject.databinding.ActivityAssignmentScheduleReportBinding;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AssignmentScheduleReport extends AppCompatActivity {
    private ActivityAssignmentScheduleReportBinding binding;
    private TextView reportDate;
    private RecyclerView rv;
    private AssignmentViewModel assignmentViewModel;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mma", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_schedule_report);

        binding = ActivityAssignmentScheduleReportBinding.inflate(getLayoutInflater());
        View viewToBind = binding.getRoot();
        setContentView(viewToBind);

        reportDate = binding.assignmentReportDate;
        rv = binding.assignmentScheduleRecyclerView;

        reportDate.setText(LocalDateTime.now().format(formatter));


        final AssignmentScheduleAdapter adapter = new AssignmentScheduleAdapter(new AssignmentScheduleAdapter.AssignmentDiff());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        assignmentViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);
        assignmentViewModel.getAssignmentsByDate().observe(this, assignments -> {
            adapter.submitList(assignments);
        });
    }
}