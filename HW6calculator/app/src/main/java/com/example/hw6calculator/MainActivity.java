package com.example.hw6calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button Addition, Subtraction, Multiplication, Division;
    private EditText et1, et2, et3;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Add");
        categories.add("Subtract");
        categories.add("Multiplication");
        categories.add("Division");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        Addition = (Button) findViewById(R.id.btnadd);
        Subtraction = (Button) findViewById(R.id.btnsubtract);
        Multiplication = (Button) findViewById(R.id.btnmultiply);
        Division = (Button) findViewById(R.id.btndivide);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);



        Addition.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(et1.getText().toString().equals("")||et2.getText().equals(""))
                {
                    Toast.makeText(MainActivity.this, "Please Enter a Proper Number", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    et1.getText().toString();
                    et2.getText().toString();
                    double a1 = Double.valueOf(et1.getText().toString());
                    double a2 = Double.valueOf(et2.getText().toString());
                    double a3;
                    a3 = a1 + a2;
                    et3.setText(String.valueOf(a3));
                }
            }
                                    }

        );


        Subtraction.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v) {
                                            if(et1.getText().toString().equals("")||et2.getText().equals(""))
                                            {
                                                Toast.makeText(MainActivity.this, "Please Enter a Proper Number", Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                et1.getText().toString();
                                                et2.getText().toString();
                                                double a1 = Double.valueOf(et1.getText().toString());
                                                double a2 = Double.valueOf(et2.getText().toString());
                                                double a3;
                                                a3 = a1 - a2;
                                                et3.setText(String.valueOf(a3));
                                            }
                                        }
                                    }

        );

        Multiplication.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v) {
                                            if(et1.getText().toString().equals("")||et2.getText().equals(""))
                                            {
                                                Toast.makeText(MainActivity.this, "Please Enter a Proper Number", Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                et1.getText().toString();
                                                et2.getText().toString();
                                                double a1 = Double.valueOf(et1.getText().toString());
                                                double a2 = Double.valueOf(et2.getText().toString());
                                                double a3;
                                                a3 = a1 * a2;
                                                et3.setText(String.valueOf(a3));
                                            }
                                        }
                                    }

        );

        Division.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v) {
                                            if(et1.getText().toString().equals("")||et2.getText().equals(""))
                                            {
                                                Toast.makeText(MainActivity.this, "Please Enter a Proper Number", Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                et1.getText().toString();
                                                et2.getText().toString();
                                                double a1 = Double.valueOf(et1.getText().toString());
                                                double a2 = Double.valueOf(et2.getText().toString());
                                                double a3;
                                                a3 = a1 / a2;
                                                et3.setText(String.valueOf(a3));
                                            }
                                        }
                                    }

        );
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

        Subtraction.setOnClickListener(new View.OnClickListener(){
                                           @Override
                                           public void onClick(View v) {
                                               if(et1.getText().toString().equals("")||et2.getText().equals(""))
                                               {
                                                   Toast.makeText(MainActivity.this, "Please Enter a Proper Number", Toast.LENGTH_SHORT).show();
                                               }
                                               else
                                               {
                                                   et1.getText().toString();
                                                   et2.getText().toString();
                                                   double a1 = Double.valueOf(et1.getText().toString());
                                                   double a2 = Double.valueOf(et2.getText().toString());
                                                   double a3;
                                                   a3 = a1 - a2;
                                                   et3.setText(String.valueOf(a3));
                                               }
                                           }
                                       }

                                       else
        {
            et1.getText().toString();
            et2.getText().toString();
            double a1 = Double.valueOf(et1.getText().toString());
            double a2 = Double.valueOf(et2.getText().toString());
            double a3;
            a3 = a1 + a2;
            et3.setText(String.valueOf(a3));
        }

        else
        {
            et1.getText().toString();
            et2.getText().toString();
            double a1 = Double.valueOf(et1.getText().toString());
            double a2 = Double.valueOf(et2.getText().toString());
            double a3;
            a3 = a1 * a2;
            et3.setText(String.valueOf(a3));
        }
    }

    Division.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(et1.getText().toString().equals("")||et2.getText().equals(""))
            {
                Toast.makeText(MainActivity.this, "Please Enter a Proper Number", Toast.LENGTH_SHORT).show();
            }
            else
            {
                et1.getText().toString();
                et2.getText().toString();
                double a1 = Double.valueOf(et1.getText().toString());
                double a2 = Double.valueOf(et2.getText().toString());
                double a3;
                a3 = a1 / a2;
                et3.setText(String.valueOf(a3));
            }
        }
    }

        );





    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
