package com.carsonskjerdal.app.workouttracker;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.github.mikephil.charting.animation.Easing.EasingOption.EaseInCirc;

public class FinishedActivity extends AppCompatActivity {

    private List<Workout> workoutList = new ArrayList<>();
    private FinishedAdapter mAdapter;
    public Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);

        //set up toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar3);
        setSupportActionBar(myToolbar);
        String myColor = "#0B9B56";
        myToolbar.setBackgroundColor(Color.parseColor(myColor));

        Button buttonEnd = findViewById(R.id.button3);

        buttonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //returns the user to the main screen clearing the stack
                Intent myIntent = new Intent(FinishedActivity.this,
                        HomeActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        //fab setup
        /*final FloatingActionButton fab = findViewById(R.id.fab);
        fab.show();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //returns the user to the main screen clearing the stack
                Intent myIntent = new Intent(FinishedActivity.this,
                        HomeActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });*/

        //set up recycler view

       /* //call the custom Adapter
        mAdapter = new FinishedAdapter(workoutList);

        int icon = R.drawable.cworkouticon;

        workout = new Workout("Flies", "20", "8/8/8", icon);
        workoutList.add(workout);
        workout = new Workout("Flies3", "20", "8/8/8", icon);
        workoutList.add(workout);*/


        //sets up the recycler view system
       /* RecyclerView recyclerView = findViewById(R.id.listview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
*/

        //pie chart
        setChart();

    }



    private void setChart() {
        /*//assign Chart
        PieChart chart = findViewById(R.id.chart);

        List<PieEntry> entries = new ArrayList<>();

        //set up variables from results
        long legsPercent = 24;
        long bicepPercent = 12;
        long corePercent = 67;
        long shouldersPercent = 0;
        long backPercent = 0;
        long totalWorkoutScore = legsPercent + bicepPercent + corePercent + shouldersPercent + backPercent;
        legsPercent = legsPercent / totalWorkoutScore;
        bicepPercent = bicepPercent / totalWorkoutScore;
        corePercent = corePercent / totalWorkoutScore;
        shouldersPercent = shouldersPercent / totalWorkoutScore;
        backPercent = backPercent / totalWorkoutScore;

        //change first value into a real calculate number
        entries.add(new PieEntry(24f, "Legs"));
        entries.add(new PieEntry(63f, "Back"));
        entries.add(new PieEntry(23f, "Biceps & Tri"));
        entries.add(new PieEntry(0f, "Core"));
        entries.add(new PieEntry(0f, "Shoulders"));

        PieDataSet dataSet = new PieDataSet(entries, "Label"); // add entries to dataset

        //sets the various colours for the chart
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData = new PieData(dataSet);
        chart.setData(pieData);

        chart.setHoleRadius(20f);
        chart.setTransparentCircleRadius(30f);
        String myColor = "#323850";
        chart.setHoleColor(Color.parseColor(myColor));

        //should delete text off of slices
        chart.setDrawEntryLabels(false);

        //description
        chart.getDescription().setEnabled(false);

        //animation;
        chart.animateX(1000, EaseInCirc);
        //set Legend to the left side
        Legend legend = chart.getLegend();
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setYOffset(20);

        chart.invalidate();
*/
    }

}
