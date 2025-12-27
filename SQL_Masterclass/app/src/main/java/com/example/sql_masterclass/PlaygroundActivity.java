package com.example.sql_masterclass;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class PlaygroundActivity extends AppCompatActivity {

    // 1️⃣ CLASS VARIABLES (already explained)
    EditText etQuery;
    Button btnRun, btnClear;
    TextView tvResult, tvDbStructure;
    DatabaseHelper dbHelper;

    // 2️⃣ onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground);

        dbHelper = new DatabaseHelper(this);

        etQuery = findViewById(R.id.etQuery);
        btnRun = findViewById(R.id.btnRun);
        btnClear = findViewById(R.id.btnClear);
        tvResult = findViewById(R.id.tvResult);
        tvDbStructure = findViewById(R.id.tvDbStructure);

        // 🔹 CALL THE METHOD HERE
        loadDatabaseStructure();
        TextView tvDbTitle = findViewById(R.id.tvDbTitle);
        TextView tvDbStructure = findViewById(R.id.tvDbStructure);

        tvDbTitle.setOnClickListener(v -> {
            if (tvDbStructure.getVisibility() == View.GONE) {
                tvDbStructure.setVisibility(View.VISIBLE);
                tvDbTitle.setText("📦 Database Structure ▲");
            } else {
                tvDbStructure.setVisibility(View.GONE);
                tvDbTitle.setText("📦 Database Structure ▼");
            }
        });

    }

    // 3️⃣ WRITE STEP 2 METHOD HERE (⬅️ THIS IS THE ANSWER)
    private void loadDatabaseStructure() {
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            StringBuilder builder = new StringBuilder();

            Cursor tables = db.rawQuery(
                    "SELECT name FROM sqlite_master WHERE type='table' AND name NOT LIKE 'android_%'",
                    null
            );

            while (tables.moveToNext()) {
                String tableName = tables.getString(0);
                builder.append(tableName).append("\n");

                Cursor columns = db.rawQuery("PRAGMA table_info(" + tableName + ")", null);
                while (columns.moveToNext()) {
                    builder.append("  • ")
                            .append(columns.getString(1))
                            .append(" (")
                            .append(columns.getString(2))
                            .append(")\n");
                }
                columns.close();
                builder.append("\n");
            }
            tables.close();

            tvDbStructure.setText(builder.toString());

        } catch (Exception e) {
            tvDbStructure.setText("Unable to load database structure");
        }
    }
}
