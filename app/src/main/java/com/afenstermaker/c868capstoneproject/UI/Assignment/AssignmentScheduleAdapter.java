package com.afenstermaker.c868capstoneproject.UI.Assignment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.afenstermaker.c868capstoneproject.Entity.Assignment;
import com.afenstermaker.c868capstoneproject.databinding.AssignmentScheduleRowBinding;

public class AssignmentScheduleAdapter extends ListAdapter<Assignment, AssignmentRowViewHolder> {
    public AssignmentScheduleAdapter(@NonNull DiffUtil.ItemCallback<Assignment> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public AssignmentRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AssignmentScheduleRowBinding binding = AssignmentScheduleRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AssignmentRowViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(AssignmentRowViewHolder holder, int position) {
        Assignment current = getItem(position);
        holder.bind(current);
    }

    public static class AssignmentDiff extends DiffUtil.ItemCallback<Assignment> {
        @Override
        public boolean areItemsTheSame(@NonNull Assignment oldAssignment, @NonNull Assignment newAssignment) {
            return oldAssignment == newAssignment;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Assignment oldAssignment, @NonNull Assignment newAssignment) {
            return oldAssignment.getAssignmentID() == newAssignment.getAssignmentID();

        }
    }
}
