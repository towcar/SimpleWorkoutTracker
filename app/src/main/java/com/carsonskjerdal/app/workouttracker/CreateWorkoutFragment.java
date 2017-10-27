package com.carsonskjerdal.app.workouttracker;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class CreateWorkoutFragment extends DialogFragment {


    private EditText mEditText;
    private EditText mEditText2;
    OnDialogCloseListener mCallback;


    public CreateWorkoutFragment() {
        // Empty constructor required for DialogFragment
    }

    public static CreateWorkoutFragment newInstance(String title) {
        CreateWorkoutFragment frag = new CreateWorkoutFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_workout, container, false);

        //create datebase object, pass getActivity b/e object is in a fragment.
        final DatabaseHandler db = new DatabaseHandler(getActivity());

        Button mButton = view.findViewById(R.id.buttonCancel);
        Button mButton2 = view.findViewById(R.id.buttonSave);
        final Spinner mSpinner = view.findViewById(R.id.spinner);
        mEditText = view.findViewById(R.id.editText);
        mEditText2 = view.findViewById(R.id.editTextWeight);

        String[] items = new String[]{"Biceps & Triceps", "Back", "Core", "Shoulders", "Chest", "Legs"};

        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);

        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(mAdapter);

        //leaves the fragment and returns
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        //save button for saving workouts
        mButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String title = mEditText.getText().toString();
                String category = mSpinner.getSelectedItem().toString();
                String weight = mEditText2.getText().toString();
                int icon = buildIcon(category);

                //pass all the data into a new object for the Recycler View to store and show also refresh
                db.addWorkout(new Workout(title, weight, "8/8/8", icon));
                Log.e("Database Build", "Added a new workout: " + title);


                //refresh the views I guess..
                // MuscleSelectorActivity.dialogReset();
                mCallback.onDialogClose(category);

                dismiss();
            }
        });

        return view;


        //View view = inflater.inflate(R.layout.fragment_create_workout, container);
        // return inflater.inflate(R.layout.fragment_create_workout, container);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnDialogCloseListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


    private int buildIcon(String category) {
        int icon = R.drawable.cworkouticon;
        switch (category) {
            case "Biceps & Triceps": {
                icon = R.drawable.cworkouticon;
                return icon;
            }
            case "Back": {
                icon = R.drawable.backicon;
                return icon;

            }
            case "Core": {
                icon = R.drawable.coreicon;
                return icon;
            }
            case "Shoulders": {
                icon = R.drawable.shouldersicon;
                return icon;
            }
            case "Chest": {
                icon = R.drawable.chesticon;
                return icon;

            }
            case "Legs": {
                icon = R.drawable.legicon;
                return icon;
            }

        }
        return icon;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}




