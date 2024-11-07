package com.afenstermaker.c868capstoneproject.UI.Course;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.afenstermaker.c868capstoneproject.Entity.Course;
import com.afenstermaker.c868capstoneproject.databinding.CourseListItemBinding;

import java.text.SimpleDateFormat;

public class CourseViewHolder extends RecyclerView.ViewHolder {
    final TextView courseName;
    final TextView classroom;
    final TextView teacherName;
    final TextView courseID;
    final CardView cv;
    String teacherPhone;
    String teacherEmail;
    String courseNotes;
    String startDate;
    private String dateFormat = "MM/dd/yyyy";
    private SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    CourseViewHolder(CourseListItemBinding binding) {
        super(binding.getRoot());

        courseName = binding.courseName;
        classroom = binding.classroom;
        teacherName = binding.teacherName;
        courseID = binding.courseID;
        cv = binding.courseCardView;

        Context context = cv.getContext();
        cv.setOnClickListener(view -> {
            Intent intent = new Intent(context, CourseDetail.class);
            intent.putExtra("id", Integer.parseInt(courseID.getText().toString()));
            intent.putExtra("name", courseName.getText().toString());
            intent.putExtra("room", classroom.getText().toString());
            intent.putExtra("teacher", teacherName.getText().toString());
            intent.putExtra("phone", teacherPhone);
            intent.putExtra("email", teacherEmail);
            intent.putExtra("notes", courseNotes);
            intent.putExtra("startDate", startDate);
            context.startActivity(intent);
        });
    }

    public void bind(Course course) {
        courseID.setText(String.valueOf(course.getCourseID()));
        courseName.setText(course.getCourseName());
        classroom.setText(course.getClassroom());
        teacherName.setText(course.getTeacherName());
        teacherPhone = course.getTeacherPhone();
        teacherEmail = course.getTeacherEmail();
        courseNotes = course.getCourseNotes();
        startDate = (sdf.format(course.getStartDate()));
    }
}
