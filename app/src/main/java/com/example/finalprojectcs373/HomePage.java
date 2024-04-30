package com.example.finalprojectcs373;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

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
    }

    public void ReturnToLoginHome(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void AddNewEntry(View v) {
        Intent intent = new Intent(this, NewEntry.class);
        startActivity(intent);
    }

    public void PopulateRecyclerView() {
        ArrayList<String> entries = new ArrayList<>();
        entries.add("Entry 1");
        entries.add("Entry 2");
        entries.add("Entry 3");

        RecyclerView recyclerView = findViewById(R.id.entryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, entries);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

}
