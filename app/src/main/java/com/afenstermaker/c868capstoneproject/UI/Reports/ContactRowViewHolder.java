package com.afenstermaker.c868capstoneproject.UI.Reports;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.afenstermaker.c868capstoneproject.Entity.Course;
import com.afenstermaker.c868capstoneproject.databinding.ContactListRowBinding;

public class ContactRowViewHolder extends RecyclerView.ViewHolder {
    final TextView teacherName;
    final TextView teacherPhone;
    final TextView teacherEmail;

    ContactRowViewHolder(ContactListRowBinding binding) {
        super(binding.getRoot());

        teacherName = binding.contactRowName;
        teacherPhone = binding.contactRowPhone;
        teacherEmail = binding.contactRowEmail;
    }

    public void bind(Course course) {
        teacherName.setText(course.getTeacherName());
        teacherPhone.setText(course.getTeacherPhone());
        teacherEmail.setText(course.getTeacherEmail());
    }
}
