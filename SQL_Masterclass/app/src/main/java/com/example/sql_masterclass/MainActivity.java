package com.example.sql_masterclass;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnLearn).setOnClickListener(v ->
                startActivity(new Intent(this, LearnActivity.class)));

        findViewById(R.id.btnPractice).setOnClickListener(v ->
                startActivity(new Intent(this, PracticeListActivity.class)));

        findViewById(R.id.btnPlayground).setOnClickListener(v ->
                startActivity(new Intent(this, PlaygroundActivity.class)));
    }
}