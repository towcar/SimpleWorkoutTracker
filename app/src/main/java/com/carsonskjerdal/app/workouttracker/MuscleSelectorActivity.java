package com.carsonskjerdal.app.workouttracker;


import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static com.carsonskjerdal.app.workouttracker.WorkoutStorage.getList;

public class MuscleSelectorActivity extends AppCompatActivity {

    private List<Workout> workoutList = new ArrayList<>();
    MuscleSelectorAdapter myAdapter;
    RecyclerView recyclerView;
   // private String[] workoutTitles = {"Biceps & Triceps", "Back", "Core", "Shoulders", "Chest", "Legs"};
    public Workout workout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    NavigationView navView;
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_selector);

        //set up toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar2);
        setSupportActionBar(myToolbar);
        String myColor = "#0B9B56";
        myToolbar.setBackgroundColor(Color.parseColor(myColor));

        //fab
        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.show();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showCreateDialog();
            }
        });

        //call the custom Adapter
        setupAdapterList("bicep");
        //myAdapter.notifyDataSetChanged();

        //sets up the recycler view system
        recyclerView = findViewById(R.id.listview2);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(myAdapter);

        //set up drawer
        mDrawerLayout = findViewById(R.id.drawer_layout);
        //automatically opens up the drawer with a delay at activity start
        mDrawerLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mDrawerLayout.openDrawer(Gravity.START);
            }
        }, 200);

       navView = findViewById(R.id.nav_view);
        NavigationMenuView navMenuView = (NavigationMenuView) navView.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

        mDrawerLayout.clearFocus();

        //sets a listener to clicking the navigation drawer
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                int position = 0;

                if (id == R.id.nav_biceps) {
                    position = 0;
                } else if (id == R.id.nav_back) {
                    position = 1;
                } else if (id == R.id.nav_core) {
                    position = 2;
                } else if (id == R.id.nav_shoulders) {
                    position = 3;
                } else if (id == R.id.nav_chest) {
                    position = 4;
                } else if (id == R.id.nav_legs) {
                    position = 5;
                }
                selectItem(position);
                return true;
            }
        });
        //mDrawerLayout.openDrawer(mDrawerLayout);
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                drawerView.bringToFront();
                drawerView.requestLayout();
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.addDrawerListener(mDrawerToggle);


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Grabs the item and passes it back to add to a list
                        Workout workoutGet = workoutList.get(position);
                        String titleToPass = workoutGet.getTitle();
                        String weightToPass = workoutGet.getWeight();
                        String repsToPass = workoutGet.getReps();
                        int iconToPass = workoutGet.getIcon();
                        //return back to previous screen and add to list.
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("resultTitle", titleToPass);
                        resultIntent.putExtra("resultWeight", weightToPass);
                        resultIntent.putExtra("resultReps", repsToPass);
                        resultIntent.putExtra("resultIcon", iconToPass);
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                        Log.e("test", "on long click");
                    }
                })
        );

        //should make return button work
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_muscle_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


    private void selectItem(int position) {
    //changes the recyclerView based on selection
        switch (position) {
            case 0:
                Log.e("case test", "" + position);
                setupAdapterList("bicep");
                break;
            case 1:
                setupAdapterList("back");
                Log.e("case test", "" + position);
                break;
            case 2:
                setupAdapterList("core");
                Log.e("case test", "" + position);
                break;
            case 3:
                setupAdapterList("shoulders");
                Log.e("case test", "" + position);
                break;
            case 4:
                setupAdapterList("chest");
                Log.e("case test", "" + position);
                break;
            case 5:
                setupAdapterList("legs");
                Log.e("case test", "" + position);
                break;
        }
        //sets the recyclerView, the adapter is new so needs to be re-set to work.
        recyclerView.setAdapter(myAdapter);
        mDrawerLayout.closeDrawers();
    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    private void setupAdapterList(String muscle) {
        //wipes the workout list of data
        workoutList.clear();
        int drawIcon;

        //grabs a workout based on the workout selected
        switch (muscle) {

            case "bicep": {
                drawIcon = R.drawable.cworkouticon;

               /* workout = new Workout("Arm Pulls", "25", "8/8/8", drawIcon);
                workoutList.add(workout);
                workout = new Workout("Lined Reverse Triceps", "15", "8/8/8", drawIcon);
                workoutList.add(workout);
                workout = new Workout("Should Tricep Extensions", "20", "8/8/8", drawIcon);
                workoutList.add(workout);
                workout = new Workout("Tricep Rip Pull", "40", "8/8/8", drawIcon);
                workoutList.add(workout);
                workout = new Workout("Bicep Pull Up", "42.5", "8/8/8", drawIcon);
                workoutList.add(workout);
                workout = new Workout("Cable Curl", "20", "8/8/8", drawIcon);
                workoutList.add(workout);
                workout = new Workout("Hammer Curl", "20", "8/8/8", drawIcon);
                workoutList.add(workout);
                workout = new Workout("Dual Arm Pulls", "10", "8/8/8", drawIcon);
                workoutList.add(workout);
                */

                workoutList = getList(muscle);

                break;

            }
            case "back": {
                drawIcon = R.drawable.cworkouticon;

                workout = new Workout("Arm Pulls", "25", "8/8/8", drawIcon);
                workoutList.add(workout);
                workout = new Workout("Overhead Pull", "20", "8/8/8", drawIcon);
                workoutList.add(workout);
                workout = new Workout("Back Extensions", "0", "8/8/8", drawIcon);
                workoutList.add(workout);
                break;
            }

            case "core": {
                drawIcon = R.drawable.cworkouticon;

                workout = new Workout("Lift Ups with Support", "10", "8/8/8", drawIcon);
                workoutList.add(workout);
                workout = new Workout("Core Leg Raises", "15", "8/8/8", drawIcon);
                workoutList.add(workout);
                break;
            }

            case "shoulders": {
                drawIcon = R.drawable.cworkouticon;

                workout = new Workout("Shoulders", "35", "8/8/8", drawIcon);
                workoutList.add(workout);
                break;
            }

            case "chest": {
                drawIcon = R.drawable.chesticon;

                workout = new Workout("Arm Chest", "21", "8/8/8", drawIcon);
                workoutList.add(workout);
                break;
            }

            case "legs": {
                drawIcon = R.drawable.legicon;

                workout = new Workout("Leg Pulls", "45", "8/8/8", drawIcon);
                workoutList.add(workout);
                break;
            }

        }
        //sets myAdapter to the new list thats changed. List does not ayto Updated
        myAdapter = new MuscleSelectorAdapter(workoutList);

    }

    private void showCreateDialog() {
        // OPens the create new workout layout
        FragmentManager fm = getSupportFragmentManager();
        CreateWorkoutFragment createDialog = CreateWorkoutFragment.newInstance("Create Workout");
        createDialog.setRetainInstance(true);
        createDialog.show(fm, "fragment_create_workout");
    }


}




