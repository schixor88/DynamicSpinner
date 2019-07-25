package com.aashma.findme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static int COUNT ;
    private static final int EMPTY = 0;
    private int flagCount=0;
    LinearLayout linearLayout;
    LinearLayout linearLayout2;
    EditText editText;
    ArrayList<String> spinnerArray;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        

        //the linear layout where we show our spinner and buttons
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        //not actually necessary but helps if multiple linear layouts
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //

        editText = new EditText(this);
        linearLayout.addView(editText);



        //button to add spinners
        Button button = new Button(this);
        linearLayout.addView(button);
        button.setText("Click Me");
        button.setWidth(50);
        button.setHeight(50);
        button.setOnClickListener(clickListener);

        //TODO: clear process on hold, is it necessary?
        //button to clear spinner
        Button clear = new Button(this);

        //button now invisible
        linearLayout.addView(clear);
        //
        clear.setText("Clear");
        clear.setWidth(50);
        clear.setHeight(50);
        clear.setOnClickListener(clearListener);

        //array of data
        //later make a container array adapter
        //make array from api or json or anything
        spinnerArray= new ArrayList<String>();
        spinnerArray.add("First");
        spinnerArray.add("Second");
        spinnerArray.add("Third");
        spinnerArray.add("Fourth");
        spinnerArray.add("Fifth");
        spinnerArray.add("Sixth");
        spinnerArray.add("Seventh");

        setContentView(linearLayout);


    }

    //write code to clear the spinner or anything you like
    private View.OnClickListener clearListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    };

    //method to add the number of spinner
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {



            if (!editText.getText().toString().isEmpty()) {

                COUNT = Integer.parseInt(editText.getText().toString());

                if (COUNT>1 && COUNT<8) {
                    if (flagCount < COUNT) {
                        spinner = new Spinner(MainActivity.this);
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
                        spinner.setAdapter(spinnerArrayAdapter);
                        spinner.setMinimumWidth(100);
                        linearLayout.addView(spinner);
                        flagCount++;

                    } else {
                        Toast.makeText(MainActivity.this, "Cannot add any more", Toast.LENGTH_SHORT).show();
                        setContentView(linearLayout);
                    }
                }
                else {

                    Toast.makeText(MainActivity.this, "Please enter between 1 and 8", Toast.LENGTH_SHORT).show();

                }

            }
            else {
                Toast.makeText(MainActivity.this, "Please enter a number", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
