package com.afenstermaker.c868capstoneproject.UI.Course;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.afenstermaker.c868capstoneproject.Entity.Course;
import com.afenstermaker.c868capstoneproject.databinding.ClassScheduleRowBinding;

import java.text.SimpleDateFormat;

public class CourseRowViewHolder extends RecyclerView.ViewHolder {
    final TextView courseID;
    final TextView courseName;
    final TextView courseStart;
    private String dateFormat = "MM/dd/yyyy";
    private SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    CourseRowViewHolder(ClassScheduleRowBinding binding) {
        super(binding.getRoot());

        courseID = binding.classRowID;
        courseName = binding.classRowName;
        courseStart = binding.classRowDate;
    }

    public void bind(Course course) {
        courseID.setText(String.valueOf(course.getCourseID()));
        courseName.setText(course.getCourseName());
        courseStart.setText(sdf.format(course.getStartDate()));
    }
}
