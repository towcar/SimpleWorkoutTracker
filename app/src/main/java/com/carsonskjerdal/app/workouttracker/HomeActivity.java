package com.carsonskjerdal.app.workouttracker;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button button = findViewById(R.id.button);


        //set up toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        String myColor = "#0B9B56";
        myToolbar.setBackgroundColor(Color.parseColor(myColor));

        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                //Launches Workout Activity
                Intent myIntent = new Intent(HomeActivity.this,
                        WorkoutActivity.class);
                startActivity(myIntent);
            }
        });

        //ImageButton popupButton = findViewById(R.id.imageButton);
        findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(HomeActivity.this, view);
                popupMenu.setOnMenuItemClickListener(HomeActivity.this);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.show();
            }
        });

        //set up the Pie Chart
        setChart();
        //setChart2();

        //setup include bar
        View view1 = findViewById(R.id.totalreps);
        ImageView image1 = view1.findViewById(R.id.imageView5);
        image1.setImageResource(R.drawable.cworkouticon);
        TextView textReps = view1.findViewById(R.id.textNumber);
        textReps.setText("489");
        TextView textName = view1.findViewById(R.id.textName);
        textName.setText("Reps");

        View view2 = findViewById(R.id.totalfill);
        ImageView image2 = view2.findViewById(R.id.imageView5);
        image2.setImageResource(R.drawable.calorieicon);
        TextView textReps2 = view2.findViewById(R.id.textNumber);
        textReps2.setText("8020");
        TextView textName2 = view2.findViewById(R.id.textName);
        textName2.setText("Calories");

    }

    public void showPopup(View view) {

        /*PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.actions, popup.getMenu());
        popup.show();*/
    }

    /* private void setChart2() {
         LineChart sessionsChart = findViewById(R.id.sessionsChart);

         List<Entry> entries2 = new ArrayList<>();

         entries2.add(new Entry(0f, 10f));
         entries2.add(new Entry(10f, 10f));
         entries2.add(new Entry(15f, 30f));
         entries2.add(new Entry(20f, 40f));
         entries2.add(new Entry(25f, 50f));
         entries2.add(new Entry(30f, 60f));


         LineDataSet dataSet = new LineDataSet(entries2, "Label"); // add entries to dataset
         LineData lineData = new LineData(dataSet);
         sessionsChart.setData(lineData);
         sessionsChart.invalidate(); // refresh
     }
 */
    private void setChart() {
        //assign Chart
        BarChart chart = findViewById(R.id.chartbar);

        List<BarEntry> entries = new ArrayList<>();

        //change first value into a real calculate number
        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1f, 6f));
        entries.add(new BarEntry(2f, 15f));
        entries.add(new BarEntry(3f, 22f));
        entries.add(new BarEntry(4f, 23f));
        entries.add(new BarEntry(5f, 4f));

        BarDataSet set = new BarDataSet(entries, "BarDataSet"); // add entries to dataset

        //sets the various colours for the chart
        set.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(set);
        data.setBarWidth(0.3f);

        chart.setData(data);


        chart.setFitBars(true);
        chart.setDescription(null);
        //chart.setDrawBarShadow(true);

        //edit xAxis
        XAxis xAxis = chart.getXAxis();
        // xAxis.disableGridDashedLine();
        //should enable and name accordingly
        xAxis.setEnabled(false);

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setEnabled(false);
        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setGridColor(R.color.colorAccent);


        //turn of the legends on chart
        Legend legend = chart.getLegend();
        legend.setEnabled(false);


        chart.invalidate();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.one:
                //Toast.makeText(this, "Comedy Clicked", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.two:
                //Toast.makeText(this, "Movies Clicked", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.three:
                //Toast.makeText(this, "Music Clicked", Toast.LENGTH_SHORT).show();
                return true;


        }
        return false;
    }
}
