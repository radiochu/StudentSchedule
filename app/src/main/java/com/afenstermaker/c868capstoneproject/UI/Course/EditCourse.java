package com.afenstermaker.c868capstoneproject.UI.Course;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afenstermaker.c868capstoneproject.R;
import com.afenstermaker.c868capstoneproject.databinding.ActivityEditCourseBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditCourse extends AppCompatActivity {
    private TextView courseID;
    private EditText courseName;
    private EditText classroom;
    private EditText teacherName;
    private EditText teacherPhone;
    private EditText teacherEmail;
    private EditText courseNotes;
    private TextView startDate;
    private Button startDateButton;
    private String dateFormat = "MM/dd/yyyy";
    private SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    final Calendar courseDateCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener courseDateSetListener;
    private ActivityEditCourseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        binding = ActivityEditCourseBinding.inflate(getLayoutInflater());
        View viewToBind = binding.getRoot();
        setContentView(viewToBind);

        courseID = binding.courseIDTV;
        courseName = binding.editCourseName;
        classroom = binding.editClassroom;
        teacherName = binding.editTeacherName;
        teacherPhone = binding.editTeacherPhone;
        teacherEmail = binding.editTeacherEmail;
        courseNotes = binding.editCourseNotes;
        startDate = binding.startDateLabel;
        startDateButton = binding.courseStartButton;
        final Button saveCourse = binding.saveCourseButton;
        final Button cancelCourse = binding.cancelCourseButton;

        if (getIntent().getExtras() != null) {
            courseID.setText(getIntent().getStringExtra("courseID"));
            courseName.setText(getIntent().getStringExtra("courseName"));
            classroom.setText(getIntent().getStringExtra("classroom"));
            startDate.setText((int) getIntent().getLongExtra("date", -1));
            teacherName.setText(getIntent().getStringExtra("teacherName"));
            teacherPhone.setText(getIntent().getStringExtra("teacherPhone"));
            teacherEmail.setText(getIntent().getStringExtra("teacherEmail"));
            courseNotes.setText(getIntent().getStringExtra("courseNotes"));
        }

        saveCourse.setOnClickListener(view -> {
            if (!validateInput()) {
                Toast.makeText(getApplicationContext(), "Some values were missing. Course not saved.", Toast.LENGTH_LONG).show();
            } else {
                Intent replyIntent = new Intent();
                String name = courseName.getText().toString();
                String room = classroom.getText().toString();
                String teacher = teacherName.getText().toString();
                String phone = teacherPhone.getText().toString();
                String email = teacherEmail.getText().toString();
                String notes = courseNotes.getText().toString();
                Date date = courseDateCalendar.getTime();

                if (!courseID.getText().toString().isEmpty()) {
                    replyIntent.putExtra("courseID", courseID.getText().toString());
                }
                replyIntent.putExtra("name", name);
                replyIntent.putExtra("room", room);
                replyIntent.putExtra("teacher", teacher);
                replyIntent.putExtra("phone", phone);
                replyIntent.putExtra("email", email);
                replyIntent.putExtra("notes", notes);
                replyIntent.putExtra("startDate", date.getTime());

                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });

        courseDateSetListener = (view, year, monthOfYear, dayOfMonth) -> {
            courseDateCalendar.set(android.icu.util.Calendar.YEAR, year);
            courseDateCalendar.set(android.icu.util.Calendar.MONTH, monthOfYear);
            courseDateCalendar.set(android.icu.util.Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateLabel();
        };

        startDateButton.setOnClickListener(v -> {
            new DatePickerDialog(EditCourse.this, courseDateSetListener,
                                 courseDateCalendar.get(android.icu.util.Calendar.YEAR),
                                 courseDateCalendar.get(android.icu.util.Calendar.MONTH),
                                 courseDateCalendar.get(android.icu.util.Calendar.DAY_OF_MONTH)).show();
        });

        cancelCourse.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(EditCourse.this);
            builder.setTitle(R.string.app_name);
            builder.setMessage("Cancel without saving changes?");
            builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                Intent replyIntent = new Intent();
                setResult(RESULT_CANCELED, replyIntent);
                finish();
            });
            builder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());
            AlertDialog alert = builder.create();
            alert.show();
        });
    }

    private boolean validateInput() {
        if (courseName.getText().toString().trim().isEmpty()) {
            courseName.setError("Course name is required");
            return false;
        }
        if (classroom.getText().toString().trim().isEmpty()) {
            classroom.setError("Classroom is required");
            return false;
        }
        if (teacherName.getText().toString().trim().isEmpty()) {
            teacherName.setError("Teacher name is required");
            return false;
        }
        if (teacherPhone.getText().toString().trim().isEmpty()) {
            teacherPhone.setError("Teacher phone number is required");
            return false;
        }
        if (teacherEmail.getText().toString().trim().isEmpty()) {
            teacherEmail.setError("Teacher email address is required");
            return false;
        }
        if (startDate.getText().toString().isEmpty()) {
            startDate.setError("Start date is required");
            return false;
        }
        return true;
    }

    private void updateDateLabel() {
        startDate.setText(sdf.format(courseDateCalendar.getTime()));
    }
}