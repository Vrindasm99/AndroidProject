package com.example.sql_masterclass;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PlaygroundActivity extends AppCompatActivity {

    EditText etQuery;
    Button btnRun, btnClear;
    TextView tvResult;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground);

        dbHelper = new DatabaseHelper(this);

        // Link Views
        etQuery = findViewById(R.id.etQuery);
        btnRun = findViewById(R.id.btnRun);
        btnClear = findViewById(R.id.btnClear);
        tvResult = findViewById(R.id.tvResult);

        // Check for pre-filled query (from Learn section)
        String preFilled = getIntent().getStringExtra("PRE_FILLED_QUERY");
        if(preFilled != null) etQuery.setText(preFilled);

        // Run Button
        btnRun.setOnClickListener(v -> {
            String query = etQuery.getText().toString().trim();
            if (!query.isEmpty()) runQuery(query);
        });

        // Clear Button
        btnClear.setOnClickListener(v -> {
            etQuery.setText("");
            tvResult.setText("Results cleared.");
            tvResult.setTextColor(Color.BLACK);
        });
    }

    private void runQuery(String query) {
        try {
            tvResult.setTextColor(Color.BLACK); // Reset color to black for normal results

            if (query.toLowerCase().startsWith("select")) {
                Cursor cursor = dbHelper.getReadableDatabase().rawQuery(query, null);
                if (cursor != null) {
                    StringBuilder builder = new StringBuilder();

                    // 1. Get Columns
                    String[] colNames = cursor.getColumnNames();
                    for(String name : colNames) builder.append(name).append(" | ");
                    builder.append("\n" + "-".repeat(colNames.length * 10) + "\n");

                    // 2. Get Rows
                    while (cursor.moveToNext()) {
                        for (int i = 0; i < cursor.getColumnCount(); i++) {
                            builder.append(cursor.getString(i)).append(" | ");
                        }
                        builder.append("\n");
                    }

                    if (cursor.getCount() == 0) {
                        builder.append("\n(No results found)");
                    }

                    tvResult.setText(builder.toString());
                    cursor.close();
                }
            } else {
                // Handle UPDATE, INSERT, DELETE
                dbHelper.getWritableDatabase().execSQL(query);
                tvResult.setText("✓ Query Executed Successfully.");
                tvResult.setTextColor(Color.parseColor("#2E7D32")); // Success Green
            }
        } catch (Exception e) {
            // Show Error inside the result box
            tvResult.setText("❌ SQL Error:\n" + e.getMessage());
            tvResult.setTextColor(Color.RED);
        }
    }
}