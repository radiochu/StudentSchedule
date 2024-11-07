package com.afenstermaker.c868capstoneproject.UI.Reports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.afenstermaker.c868capstoneproject.R;
import com.afenstermaker.c868capstoneproject.UI.Course.ClassScheduleAdapter;
import com.afenstermaker.c868capstoneproject.ViewModel.CourseViewModel;
import com.afenstermaker.c868capstoneproject.databinding.ActivityClassScheduleReportBinding;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ClassScheduleReport extends AppCompatActivity {
    private ActivityClassScheduleReportBinding binding;
    private TextView reportDate;
    private RecyclerView rv;
    private CourseViewModel courseViewModel;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mma", Locale.getDefault());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule_report);

        binding = ActivityClassScheduleReportBinding.inflate(getLayoutInflater());
        View viewToBind = binding.getRoot();
        setContentView(viewToBind);

        reportDate = binding.reportDate;
        rv = binding.classScheduleRecyclerView;

        reportDate.setText(LocalDateTime.now().format(formatter));

        final ClassScheduleAdapter adapter = new ClassScheduleAdapter(new ClassScheduleAdapter.CourseDiff());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getCoursesByDate().observe(this, courses -> {
            adapter.submitList(courses);
        });
    }
}