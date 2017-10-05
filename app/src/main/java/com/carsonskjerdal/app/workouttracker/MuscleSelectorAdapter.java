package com.carsonskjerdal.app.workouttracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Carson on 2017-09-15.
 * <p>
 * Feel free to use code just give credit please :)
 */

public class MuscleSelectorAdapter extends RecyclerView.Adapter<MuscleSelectorAdapter.MyViewHolder> {

    private List<Workout> workoutList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, weight, reps;
        public ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            weight = view.findViewById(R.id.weight);
            reps = view.findViewById(R.id.reps);
            icon = view.findViewById(R.id.imageView);
        }
    }


    public MuscleSelectorAdapter(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Workout workout = workoutList.get(position);
        holder.title.setText(workout.getTitle());
        holder.weight.setText(workout.getWeight());
        holder.reps.setText(workout.getReps());
        holder.icon.setImageResource(workout.getIcon());

    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }
}

