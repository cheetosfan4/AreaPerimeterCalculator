package com.example.areaperimetercalculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //triangle
    //area = base and height
    //perimeter = side a, side b, and side c

    Spinner sp_j_shapes;
    ArrayAdapter<String> spinnerAdapter;

    //--square--------------------
    EditText et_j_length;
    EditText et_j_width;
    ConstraintLayout cons_j_squareRectView;
    TextView tv_j_computedSquareRect;

    //--circle--------------------
    EditText et_j_radius;
    ConstraintLayout cons_j_circleView;
    TextView tv_j_computedCircle;

    //--triangle--------------------
    EditText et_j_height;
    EditText et_j_base;
    EditText et_j_sideTwo;
    EditText et_j_sideThree;
    ConstraintLayout cons_j_triangleView;
    TextView tv_j_computedTriangle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //--square--------------------
        et_j_length = findViewById(R.id.et_v_length);
        et_j_width = findViewById(R.id.et_v_width);
        cons_j_squareRectView = findViewById(R.id.cons_v_squareRectangle);
        tv_j_computedSquareRect = findViewById(R.id.tv_v_computedValuesSquareRect);

        //--circle--------------------
        et_j_radius = findViewById(R.id.et_v_radius);
        cons_j_circleView = findViewById(R.id.cons_v_circle);
        tv_j_computedCircle = findViewById(R.id.tv_v_computedValuesCircle);

        //--triangle--------------------
        et_j_height = findViewById(R.id.et_v_height);
        et_j_base = findViewById(R.id.et_v_base);
        et_j_sideTwo = findViewById(R.id.et_v_sideTwo);
        et_j_sideThree = findViewById(R.id.et_v_sideThree);
        cons_j_triangleView = findViewById(R.id.cons_v_triangle);
        tv_j_computedTriangle = findViewById(R.id.tv_v_computedValuesTriangle);

        //Because we are making a simple drop down menu (spinner) that will only contain
        //strings as options, we can use a string array with the built-in array adapter
        //to populate the spinner
        sp_j_shapes = findViewById(R.id.sp_v_shapes);
        //we will use this to populate our spinner
        String[] shapes = new String[]{"Square", "Rectangle", "Circle", "Triangle"};

        //adapter is what links the java code with the xml for the spinner
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, shapes);

        sp_j_shapes.setAdapter(spinnerAdapter);

        setupSpinnerChangeListener();
        textChangeListener();
    }

    public void setupSpinnerChangeListener() {
        sp_j_shapes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //0-3 indexes
                if(position == 0) {
                    //square
                    cons_j_squareRectView.setVisibility(View.VISIBLE);
                    cons_j_circleView.setVisibility(View.INVISIBLE);
                    cons_j_triangleView.setVisibility(View.INVISIBLE);
                }
                else if(position == 1) {
                    //rectangle
                    cons_j_squareRectView.setVisibility(View.VISIBLE);
                    cons_j_circleView.setVisibility(View.INVISIBLE);
                    cons_j_triangleView.setVisibility(View.INVISIBLE);
                }
                else if(position == 2) {
                    //circle
                    cons_j_circleView.setVisibility(View.VISIBLE);
                    cons_j_squareRectView.setVisibility(View.INVISIBLE);
                    cons_j_triangleView.setVisibility(View.INVISIBLE);
                }
                else if(position == 3) {
                    //triangle
                    cons_j_triangleView.setVisibility(View.VISIBLE);
                    cons_j_squareRectView.setVisibility(View.INVISIBLE);
                    cons_j_circleView.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void hideConstraintView(ConstraintLayout cl) {
        cl.setVisibility(View.INVISIBLE);
    }

    public void textChangeListener() {
        et_j_width.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setAreaAndPerimeterSquareRect(et_j_length.getText().toString(), et_j_width.getText().toString());
            }
        });
        et_j_length.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setAreaAndPerimeterSquareRect(et_j_length.getText().toString(), et_j_width.getText().toString());
            }
        });
        et_j_radius.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setAreaAndPerimeterCircle(et_j_radius.getText().toString());
            }
        });
        et_j_height.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setAreaAndPerimeterTriangle(et_j_height.getText().toString(), et_j_base.getText().toString(), et_j_sideTwo.getText().toString(), et_j_sideThree.getText().toString());
            }
        });
        et_j_base.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setAreaAndPerimeterTriangle(et_j_height.getText().toString(), et_j_base.getText().toString(), et_j_sideTwo.getText().toString(), et_j_sideThree.getText().toString());
            }
        });
        et_j_sideTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setAreaAndPerimeterTriangle(et_j_height.getText().toString(), et_j_base.getText().toString(), et_j_sideTwo.getText().toString(), et_j_sideThree.getText().toString());
            }
        });
        et_j_sideThree.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setAreaAndPerimeterTriangle(et_j_height.getText().toString(), et_j_base.getText().toString(), et_j_sideTwo.getText().toString(), et_j_sideThree.getText().toString());
            }
        });
    }

    public void setAreaAndPerimeterSquareRect(String lengthS, String widthS) {

        if(lengthS.isEmpty() || widthS.isEmpty()) {
            return;
        }
        double length = Double.parseDouble(lengthS);
        double width = Double.parseDouble(widthS);

        double area = length * width;
        double perimeter = length + length + width + width;

        tv_j_computedSquareRect.setText("Area = " + area + "\nPerimeter = " + perimeter);
    }

    public void setAreaAndPerimeterCircle(String radiusS) {
        if(radiusS.isEmpty()) {
            return;
        }
        double radius = Double.parseDouble(radiusS);
        double pi = Math.PI;

        double area = pi * radius * radius;
        double perimeter = 2 * pi * radius;

        tv_j_computedCircle.setText("Area = " + area + "\nPerimeter = " + perimeter);
    }

    public void setAreaAndPerimeterTriangle(String heightS, String baseS, String sideTwoS, String sideThreeS) {
        double height = 0.0;
        double base = 0.0;
        double sideTwo = 0.0;
        double sideThree = 0.0;
        double area = 0.0;
        double perimeter = 0.0;

        if(!heightS.isEmpty() && !baseS.isEmpty()) {
            height = Double.parseDouble(heightS);
            base = Double.parseDouble(baseS);
            area = (base * height)/2;
        }
        if(!baseS.isEmpty() && !sideTwoS.isEmpty() && !sideThreeS.isEmpty()) {
            sideTwo = Double.parseDouble(sideTwoS);
            sideThree = Double.parseDouble(sideThreeS);
            perimeter = base + sideTwo + sideThree;
        }

        tv_j_computedTriangle.setText("Area = " + area + "\nPerimeter = " + perimeter);
    }

}