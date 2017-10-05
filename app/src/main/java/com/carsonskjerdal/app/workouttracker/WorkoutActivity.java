package com.carsonskjerdal.app.workouttracker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class WorkoutActivity extends AppCompatActivity {

    private List<Workout> workoutList = new ArrayList<>();
    private WorkoutAdapter mAdapter;
    public Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        //set up toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        String myColor = "#0B9B56";
        myToolbar.setBackgroundColor(Color.parseColor(myColor));

        int drawIcon = R.drawable.cworkouticon;

        //call the custom Adapter
        mAdapter = new WorkoutAdapter(workoutList);

        //delete these later, here for testing
        /*workout = new Workout("Flies", "20", "8/8/8", drawIcon);
        workoutList.add(workout);
        workout = new Workout("Bench Press Cross Attack", "35", "8/8/8", drawIcon);
        workoutList.add(workout);*/

        //sets up the recycler view system
        RecyclerView recyclerView = findViewById(R.id.listview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        Log.e("test", "on click");
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                        Log.e("test", "on long click");
                    }
                })
        );

        //temporary button click for adding workouts
        Button button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Integer int1 = 0;
                //Adds new workout to Adapter
                Intent myIntent = new Intent(WorkoutActivity.this,
                        MuscleSelectorActivity.class);
                startActivityForResult(myIntent, int1);

                //refresh the adapter
                //mAdapter.notifyDataSetChanged();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //String STRING_IDENTIFIER = "STRING_IDENTIFIER";
        super.onActivityResult(requestCode, resultCode, data);
        //modified this, but when returning use the resulting text
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                //pulls the selected info from the item to pass them.. reps needs to change accordingly still
                String resultTitle = data.getStringExtra("resultTitle");
                String resultWeight = data.getStringExtra("resultWeight");
                String resultReps = data.getStringExtra("resultReps");
                int drawIcon = data.getIntExtra("resultIcon", 0);
                //add new workout to list
                //int drawIcon = R.drawable.cworkouticon;
                workout = new Workout(resultTitle, resultWeight, resultReps, drawIcon);
                workoutList.add(workout);
                mAdapter.notifyDataSetChanged();
            }
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_complete:
                // complete workout
                Integer int1 = 0;
                //Adds new workout to Adapter
                Intent myIntent = new Intent(WorkoutActivity.this,
                       FinishedActivity.class);
                startActivityForResult(myIntent, int1);
                return true;

            /*case R.id.action_complete:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;*/

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
