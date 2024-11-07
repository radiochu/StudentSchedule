package com.afenstermaker.c868capstoneproject.UI.Assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afenstermaker.c868capstoneproject.AlarmReceiver;
import com.afenstermaker.c868capstoneproject.MainActivity;
import com.afenstermaker.c868capstoneproject.R;
import com.afenstermaker.c868capstoneproject.ViewModel.AssignmentViewModel;
import com.afenstermaker.c868capstoneproject.databinding.ActivityAssignmentDetailBinding;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class AssignmentDetail extends AppCompatActivity {
    private TextView assignmentID;
    private TextView assignmentName;
    private TextView assignmentType;
    private TextView assignmentDueDate;
    private TextView assignmentClass;
    private ImageButton setAlert;
    private ActivityAssignmentDetailBinding binding;
    private AssignmentViewModel assignmentViewModel;
    String dateFormat = "MM/dd/yyyy";
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_detail);

        binding = ActivityAssignmentDetailBinding.inflate(getLayoutInflater());
        View viewToBind = binding.getRoot();
        setContentView(viewToBind);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        assignmentViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);

        assignmentID = binding.assignmentDetailID;
        assignmentName = binding.assignmentDetailName;
        assignmentType = binding.assignmentDetailType;
        assignmentDueDate = binding.assignmentDetailDueDate;
        assignmentClass = binding.assignmentDetailCourseID;
        setAlert = binding.dueDateAlertButton;

        if (getIntent().getExtras() != null) {
            assignmentID.setText(getIntent().getStringExtra("ID"));
            assignmentName.setText(getIntent().getStringExtra("name"));
            assignmentType.setText(getIntent().getStringExtra("type"));
            assignmentDueDate.setText(getIntent().getStringExtra("dueDate"));
            assignmentClass.setText(getIntent().getStringExtra("class"));
        }

            setAlert.setOnClickListener(v -> {
            String date = assignmentDueDate.getText().toString();
            Date startDate = null;
            try {
                startDate = sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long trigger = startDate.getTime();
            Intent intent = new Intent(this, AlarmReceiver.class);
            intent.putExtra("message", "Your assignment " + assignmentName.getText().toString() + " is due today!");
            PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(this, MainActivity.alertNum++, intent, PendingIntent.FLAG_IMMUTABLE);
            Toast.makeText(AssignmentDetail.this, "Due Date Alert Set", Toast.LENGTH_SHORT).show();
            alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, notifyPendingIntent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.assignment_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_assignment) {
            assignmentViewModel.delete(Integer.parseInt(assignmentID.getText().toString()));
            Intent intent = new Intent(AssignmentDetail.this, AssignmentList.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}