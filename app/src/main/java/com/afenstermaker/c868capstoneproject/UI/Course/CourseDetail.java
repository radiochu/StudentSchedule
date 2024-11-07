package com.afenstermaker.c868capstoneproject.UI.Course;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afenstermaker.c868capstoneproject.Entity.Assignment;
import com.afenstermaker.c868capstoneproject.R;
import com.afenstermaker.c868capstoneproject.UI.Assignment.AssignmentListAdapter;
import com.afenstermaker.c868capstoneproject.ViewModel.AssignmentViewModel;
import com.afenstermaker.c868capstoneproject.ViewModel.CourseViewModel;
import com.afenstermaker.c868capstoneproject.databinding.ActivityCourseDetailBinding;

import java.util.ArrayList;
import java.util.List;

public class CourseDetail extends AppCompatActivity {
    private TextView courseID;
    private TextView courseName;
    private TextView classroom;
    private TextView teacherName;
    private TextView teacherPhone;
    private TextView teacherEmail;
    private TextView courseNotes;
    private ImageButton shareNotes;
    private TextView startDate;
    private ActivityCourseDetailBinding binding;
    private AssignmentViewModel assignmentViewModel;
    private CourseViewModel courseViewModel;
    private final List<Assignment> assignments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        binding = ActivityCourseDetailBinding.inflate(getLayoutInflater());
        View viewToBind = binding.getRoot();
        setContentView(viewToBind);

        courseID = binding.courseDetailID;
        courseName = binding.courseDetailName;
        classroom = binding.courseDetailClassroom;
        teacherName = binding.courseDetailTeacherName;
        teacherPhone = binding.courseDetailTeacherPhone;
        teacherEmail = binding.courseDetailTeacherEmail;
        courseNotes = binding.courseDetailNotes;
        shareNotes = binding.shareNotesButton;
        startDate = binding.startDateDisplay;

        RecyclerView assignmentsRV = binding.courseDetailAssignmentsRV;
        final AssignmentListAdapter adapter = new AssignmentListAdapter(new AssignmentListAdapter.AssignmentDiff());
        assignmentsRV.setAdapter(adapter);
        assignmentsRV.setLayoutManager(new LinearLayoutManager(this));

        assignmentViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);
        final Observer<List<Assignment>> assignmentObserver =
                classAssignments -> {
                    assignments.clear();
                    assignments.addAll(classAssignments);
                    adapter.submitList(assignments);
                };

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        assignmentViewModel.getAssignmentsByCourse(getIntent().getIntExtra("id", -1)).observe(this, assignmentObserver);

        if (getIntent().getExtras() != null) {
            courseID.setText(String.valueOf(getIntent().getIntExtra("id", -1)));
            courseName.setText(getIntent().getStringExtra("name"));
            classroom.setText(getIntent().getStringExtra("room"));
            teacherName.setText(getIntent().getStringExtra("teacher"));
            teacherPhone.setText(getIntent().getStringExtra("phone"));
            teacherEmail.setText(getIntent().getStringExtra("email"));
            courseNotes.setText(getIntent().getStringExtra("notes"));
            startDate.setText(getIntent().getStringExtra("startDate"));
        }

        shareNotes.setOnClickListener(view -> {
            String notes = courseNotes.getText().toString();
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, notes);
            shareIntent.setType("text/plain");

            Intent share = Intent.createChooser(shareIntent, null);
            startActivity(share);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.class_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_class) {
            if (assignments.size() == 0) {
                courseViewModel.delete(Integer.parseInt(courseID.getText().toString()));
                Intent intent = new Intent(CourseDetail.this, CourseList.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "You can't delete a class while you still have assignments in it.", Toast.LENGTH_LONG).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}