package com.afenstermaker.c868capstoneproject.UI.Assignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afenstermaker.c868capstoneproject.R;
import com.afenstermaker.c868capstoneproject.ViewModel.CourseViewModel;
import com.afenstermaker.c868capstoneproject.databinding.ActivityEditAssignmentBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EditAssignment extends AppCompatActivity {
    private EditText assignmentName;
    private Spinner assignmentType;
    private Spinner assignmentClass;
    private TextView assignmentDate;
    private TextView assignmentID;
    private Button assignmentDateButton;
    private Button saveAssignment;
    private Button cancelAssignment;
    private ActivityEditAssignmentBinding binding;
    private final ArrayList<String> assignmentTypes = new ArrayList<>();
    final Calendar assignmentDateCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener assignmentDateSetListener;
    private String dateFormat = "MM/dd/yyyy";
    private SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    private ArrayList<Integer> courseIDs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assignment);

        binding = ActivityEditAssignmentBinding.inflate(getLayoutInflater());
        View viewToBind = binding.getRoot();
        setContentView(viewToBind);

        assignmentName = binding.editAssignmentName;
        assignmentType = binding.assignmentTypeSpinner;
        assignmentClass = binding.assignmentClassSpinner;
        assignmentDate = binding.setDueDateLabel;
        assignmentDateButton = binding.setAssignmentDateButton;
        saveAssignment = binding.saveAssignmentButton;
        cancelAssignment = binding.cancelAssignmentButton;
        assignmentID = binding.assignmentIDTV;

        ArrayAdapter<String> classAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assignmentClass.setAdapter(classAdapter);

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAllCourses().observe(this, courses -> {
            classAdapter.clear();
            for (int i = 0; i < courses.size(); i++) {
                classAdapter.add(courses.get(i).toString());
                courseIDs.add(courses.get(i).getCourseID());
            }
        });

        ArrayAdapter<String> assignmentTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, assignmentTypes);
        assignmentTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assignmentTypes.clear();
        assignmentTypes.add("Quiz");
        assignmentTypes.add("Test");
        assignmentTypes.add("Project");
        assignmentTypes.add("Homework");
        assignmentTypes.add("Other");
        assignmentType.setAdapter(assignmentTypeAdapter);

        if (getIntent().getExtras() != null) {
            assignmentID.setText(getIntent().getStringExtra("ID"));
            assignmentName.setText(getIntent().getStringExtra("name"));
            assignmentDate.setText((int) getIntent().getLongExtra("date", -1));
            assignmentClass.setSelection(classAdapter.getPosition(getIntent().getStringExtra("class")));
            assignmentType.setSelection(assignmentTypeAdapter.getPosition(getIntent().getStringExtra("type")));
        }

        assignmentDateSetListener = (view, year, monthOfYear, dayOfMonth) -> {
            assignmentDateCalendar.set(android.icu.util.Calendar.YEAR, year);
            assignmentDateCalendar.set(android.icu.util.Calendar.MONTH, monthOfYear);
            assignmentDateCalendar.set(android.icu.util.Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDueDateLabel();
        };

        assignmentDateButton.setOnClickListener(v -> {
            new DatePickerDialog(EditAssignment.this, assignmentDateSetListener,
                                 assignmentDateCalendar.get(android.icu.util.Calendar.YEAR),
                                 assignmentDateCalendar.get(android.icu.util.Calendar.MONTH),
                                 assignmentDateCalendar.get(android.icu.util.Calendar.DAY_OF_MONTH)).show();
        });

        saveAssignment.setOnClickListener(view -> {
            if (!validateInput()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent replyIntent = new Intent();
                if (!assignmentID.getText().toString().isEmpty()) {
                    replyIntent.putExtra("ID", assignmentID.getText().toString());
                }
                String name = assignmentName.getText().toString();
                String type = assignmentType.getSelectedItem().toString();
                String courseName = assignmentClass.getSelectedItem().toString();
                int courseID = Integer.parseInt(String.valueOf(assignmentClass.getSelectedItem().toString().charAt(0)));
                Date date = assignmentDateCalendar.getTime();

                replyIntent.putExtra("name", name);
                replyIntent.putExtra("type", type);
                replyIntent.putExtra("class", courseName);
                replyIntent.putExtra("courseID", courseID);
                replyIntent.putExtra("dueDate", date.getTime());

                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });

        cancelAssignment.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(EditAssignment.this);
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

    private void updateDueDateLabel() {
        assignmentDate.setText(sdf.format(assignmentDateCalendar.getTime()));
    }

    private boolean validateInput() {
        if (assignmentName.getText().toString().isEmpty()) {
            assignmentName.setError("Assignment name is required");
            return false;
        }
        if (assignmentDate.getText().toString().isEmpty()) {
            assignmentDate.setError("Assignment due date is required");
            return false;
        }
        if (assignmentClass.getSelectedItem() == null || TextUtils.isEmpty(assignmentClass.getSelectedItem().toString())) {
            Toast.makeText(this, "No class selected! Please add classes first", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}