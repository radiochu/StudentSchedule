package com.afenstermaker.c868capstoneproject.UI.Course;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.afenstermaker.c868capstoneproject.Entity.Course;
import com.afenstermaker.c868capstoneproject.databinding.ClassScheduleRowBinding;

public class ClassScheduleAdapter extends ListAdapter<Course, CourseRowViewHolder> {
    public ClassScheduleAdapter(@NonNull DiffUtil.ItemCallback<Course> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CourseRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ClassScheduleRowBinding binding = ClassScheduleRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CourseRowViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CourseRowViewHolder holder, int position) {
        Course current = getItem(position);
        holder.bind(current);
    }

    public static class CourseDiff extends DiffUtil.ItemCallback<Course> {
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
