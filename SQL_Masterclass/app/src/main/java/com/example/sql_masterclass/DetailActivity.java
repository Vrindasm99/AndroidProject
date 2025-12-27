package com.example.sql_masterclass;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Keep the UI exactly the same by using the existing layout
        setContentView(R.layout.activity_learn_detail);

        // 1. Initialize Views
        TextView tvTitle = findViewById(R.id.tvDetailTitle);
        TextView tvDesc = findViewById(R.id.tvDetailDesc);
        TextView tvCode = findViewById(R.id.tvDetailCode);
        TextView tvTable = findViewById(R.id.tvExampleTables); // Make sure this ID exists in your XML
        Button btnMenu = findViewById(R.id.btnMenu);
        Button btnTryIt = findViewById(R.id.btnTryIt);

        // 2. Catch Data from Intent
        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("desc");
        String code = getIntent().getStringExtra("code");

        // 3. Set Data to UI (This keeps your UI look unchanged)
        tvTitle.setText(title);
        tvDesc.setText(desc);
        tvCode.setText(code);

        // Optional: Logic to show simple static example tables based on topic keywords
        if (title != null) {
            if (title.contains("SELECT")) {
                tvTable.setText("| ID | Name     | City     |\n|----|----------|----------|\n| 1  | Alex     | London   |\n| 2  | Sarah    | Tokyo    |");
            } else if (title.contains("WHERE")) {
                tvTable.setText("| ID | Name     | Age | Status |\n|----|----------|-----|--------|\n| 5  | Mike     | 25  | Active |");
            } else {
                tvTable.setText("| SQL Result Table View |");
            }
        }

        // 4. Back Button Logic
        btnMenu.setOnClickListener(v -> finish());

        // 5. THE FIX: "Try it in Playground" Button Logic
        btnTryIt.setOnClickListener(v -> {
            // FIXED: Changed target from PracticeActivity (Quiz) to PlaygroundActivity (Coding)
            Intent intent = new Intent(DetailActivity.this, PlaygroundActivity.class);

            // FIXED: Changed key to "PRE_FILLED_QUERY" so PlaygroundActivity knows to catch it
            intent.putExtra("PRE_FILLED_QUERY", code);

            startActivity(intent);
        });
    }
}