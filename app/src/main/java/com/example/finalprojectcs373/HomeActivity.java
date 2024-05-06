package com.example.finalprojectcs373;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    private ListView entryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        View fab = findViewById(R.id.bottomAppBar);

        ViewCompat.setOnApplyWindowInsetsListener(fab, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());

            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            mlp.leftMargin = insets.left;
            mlp.bottomMargin = insets.bottom;
            mlp.rightMargin = insets.right;
            v.setLayoutParams(mlp);

            return WindowInsetsCompat.CONSUMED;
        });

        initWidgets();
        LoadFromDbToMemory();
        setEntryAdapter();
        setOnClickListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setEntryAdapter();
    }

    private void initWidgets() {
        entryListView = findViewById(R.id.entryListView);
    }

    private void LoadFromDbToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateEntryListArray();
    }

    private void setEntryAdapter() {
        EntryAdapter entryAdapter = new EntryAdapter(this, Entry.entryArrayList);
        entryListView.setAdapter(entryAdapter);
    }

    private void setOnClickListener() {
        entryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Entry selectedEntry = (Entry) entryListView.getItemAtPosition(position);
                Intent editEntryIntent = new Intent(getApplicationContext(), NewEntryActivity.class);
                editEntryIntent.putExtra(Entry.ENTRY_EDIT_EXTRA, selectedEntry.getId());
                startActivity(editEntryIntent);
            }
        });
    }

    public void ReturnToLoginHome(View v) {
        Intent intent = new Intent(this, StartingActivity.class);
        startActivity(intent);
    }

    public void AddNewEntry(View v) {
        Intent intent = new Intent(this, NewEntryActivity.class);
        startActivity(intent);
    }

}
