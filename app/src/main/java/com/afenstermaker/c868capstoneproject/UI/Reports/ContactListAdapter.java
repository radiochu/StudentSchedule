package com.afenstermaker.c868capstoneproject.UI.Reports;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.afenstermaker.c868capstoneproject.Entity.Course;
import com.afenstermaker.c868capstoneproject.databinding.ContactListRowBinding;

public class ContactListAdapter extends ListAdapter<Course, ContactRowViewHolder> {
    public ContactListAdapter(@NonNull DiffUtil.ItemCallback<Course> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ContactRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactListRowBinding binding = ContactListRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ContactRowViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ContactRowViewHolder holder, int position) {
        Course current = getItem(position);
        holder.bind(current);
    }

    public static class ContactDiff extends DiffUtil.ItemCallback<Course> {
        @Override
        public boolean areItemsTheSame(@NonNull Course oldCourse, @NonNull Course newCourse) {
            return oldCourse == newCourse;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Course oldCourse, @NonNull Course newCourse) {
            return oldCourse.getCourseID() == newCourse.getCourseID();

        }
    }
}
