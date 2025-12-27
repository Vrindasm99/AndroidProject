package com.example.sql_masterclass;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LearnDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This MUST match your XML filename exactly
        setContentView(R.layout.activity_learn_detail);

        // 1. Initialize all views from the "Super Layout"
        TextView tvTitle = findViewById(R.id.tvDetailTitle);
        TextView tvDesc = findViewById(R.id.tvDetailDesc);
        TextView tvCode = findViewById(R.id.tvDetailCode);
        TextView tvTable = findViewById(R.id.tvExampleTables);
        Button btnMenu = findViewById(R.id.btnMenu);
        Button btnTryIt = findViewById(R.id.btnTryIt);

        // 2. Extract the SQL content passed from the List (LearnAdapter)
        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("desc");
        String code = getIntent().getStringExtra("code");

        // 3. Update the UI with the data
        tvTitle.setText(title);
        tvDesc.setText(desc);
        tvCode.setText(code);

        // 4. Logic to show dynamic Example Tables based on the topic
        // This is a "Super Layout" feature to show users real data
        if (title != null) {
            if (title.contains("SELECT")) {
                tvTable.setText("| ID | Name     | City     |\n|----|----------|----------|\n| 1  | Alex     | London   |\n| 2  | Sarah    | Tokyo    |");
            } else if (title.contains("WHERE")) {
                tvTable.setText("| ID | Name     | Age | Status |\n|----|----------|-----|--------|\n| 5  | Mike     | 25  | Active |");
            } else {
                tvTable.setText("| SQL Result Table View |");
            }
        }

        // 5. STICKY BAR: Back to Topics Button
        btnMenu.setOnClickListener(v -> {
            finish(); // Closes this page and goes back to the list
        });

        // 6. ACTION BUTTON: Go to Practice/Playground
        btnTryIt.setOnClickListener(v -> {
            Intent intent = new Intent(LearnDetailActivity.this, PracticeActivity.class);
            // Pass the topic name so the practice session knows what to test
            intent.putExtra("TOPIC_NAME", title);
            startActivity(intent);
        });
    }
}