package com.carsonskjerdal.app.workouttracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Carson on 2017-09-15.
 *
 * Feel free to use code just give credit please :)
 */

public class FinishedAdapter extends RecyclerView.Adapter<FinishedAdapter.MyViewHolder> {

private List<Workout> workoutList;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView title, year, genre;

    public MyViewHolder(View view) {
        super(view);
        title = view.findViewById(R.id.title);
        genre = view.findViewById(R.id.weight);
        year = view.findViewById(R.id.reps);
    }
}


    public FinishedAdapter(List<Workout> workoutList) {
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
        holder.genre.setText(workout.getWeight());
        holder.year.setText(workout.getReps());
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }
}

