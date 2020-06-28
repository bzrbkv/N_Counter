package com.example.counterapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int count;
    TextView countValueDisplay;
    Button incrementButton;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defineValues();
        loadValues();
        setCount();
        buttonHandler();
    }

    public void defineValues() {
        countValueDisplay = (TextView) findViewById(R.id.count);
        incrementButton = (Button) findViewById(R.id.increment_button);
        sharedPreferences = getSharedPreferences("count", Context.MODE_PRIVATE);
    }

    public void loadValues() {
        sharedPreferences = getSharedPreferences("count", Context.MODE_PRIVATE);
    }

    private void setCount() {
        count = sharedPreferences.getInt("count", 0);
        if (count == 0) {
            countValueDisplay.setText("Count");

        } else {
            countValueDisplay.setText(Integer.toString(count));

        }

    }

    public void buttonHandler() {
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count != 0) {
                    count = sharedPreferences.getInt("count", 0);
                    count += 1;
                    String str = String.format("%04d", count);
                    countValueDisplay.setText(str);
                } else {
                    count += 1;
                    String str = String.format("%04d", count);
                    countValueDisplay.setText(str);
                }
                commitToSharedPreferences();
            }
        });
    }

    public void commitToSharedPreferences() {
        sharedPreferences.edit().putInt("count", count).apply();
    }

}

