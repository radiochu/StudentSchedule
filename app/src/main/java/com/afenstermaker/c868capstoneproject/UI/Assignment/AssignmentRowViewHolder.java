package com.afenstermaker.c868capstoneproject.UI.Assignment;

import android.content.Context;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.afenstermaker.c868capstoneproject.Entity.Assignment;
import com.afenstermaker.c868capstoneproject.databinding.AssignmentListItemBinding;
import com.afenstermaker.c868capstoneproject.databinding.AssignmentScheduleRowBinding;

import java.text.SimpleDateFormat;

public class AssignmentRowViewHolder extends RecyclerView.ViewHolder {
    final TextView assignmentName;
    final TextView assignmentDate;
    final TextView assignmentCourse;
    private String dateFormat = "MM/dd/yyyy";
    private SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    AssignmentRowViewHolder(AssignmentScheduleRowBinding binding) {
        super(binding.getRoot());

        assignmentName = binding.assignmentRowName;
        assignmentDate = binding.assignmentRowDate;
        assignmentCourse = binding.assignmentRowClass;
    }

    public void bind(Assignment assignment) {
        assignmentName.setText(assignment.getAssignmentName());
        assignmentDate.setText(sdf.format(assignment.getAssignmentDate()));
        assignmentCourse.setText(assignment.getCourseName());
    }
}
