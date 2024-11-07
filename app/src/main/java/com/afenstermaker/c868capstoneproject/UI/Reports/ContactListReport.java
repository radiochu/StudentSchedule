package com.afenstermaker.c868capstoneproject.UI.Reports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.afenstermaker.c868capstoneproject.R;
import com.afenstermaker.c868capstoneproject.ViewModel.CourseViewModel;
import com.afenstermaker.c868capstoneproject.databinding.ActivityContactListReportBinding;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ContactListReport extends AppCompatActivity {
    private ActivityContactListReportBinding binding;
    private TextView reportDate;
    private RecyclerView rv;
    private CourseViewModel courseViewModel;
    private ImageButton searchButton;
    private EditText searchField;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mma", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list_report);

        binding = ActivityContactListReportBinding.inflate(getLayoutInflater());
        View viewToBind = binding.getRoot();
        setContentView(viewToBind);

        reportDate = binding.reportDate;
        rv = binding.contactListRv;
        searchButton = binding.searchButton;
        searchField = binding.contactSearch;

        reportDate.setText(LocalDateTime.now().format(formatter));

        final ContactListAdapter adapter = new ContactListAdapter(new ContactListAdapter.ContactDiff());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAllCourses().observe(this, adapter::submitList);

        searchButton.setOnClickListener(v -> {
            String searchQuery = searchField.getText().toString();
            courseViewModel.searchCourses(searchQuery).observe(this, adapter::submitList);
        });
    }
}