package com.example.finalprojectcs373;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class NewEntryActivity extends AppCompatActivity {

    private EditText entryTitleEditText, entryBodyEditText;
    private Button deleteEntryButton;
    private Entry selectedEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_entry_activity);

        initWidgets();
        checkForEditEntry();

    }

    private void initWidgets() {
        entryTitleEditText = findViewById(R.id.entryTitle);
        entryBodyEditText = findViewById(R.id.entryBody);
        deleteEntryButton = findViewById(R.id.deleteEntryButton);

    }

    private void checkForEditEntry() {
        Intent previousIntent = getIntent();

        int passedEntryId = previousIntent.getIntExtra(Entry.ENTRY_EDIT_EXTRA, -1);
        selectedEntry = Entry.getEntryForID(passedEntryId);

        if (selectedEntry != null) {
            entryTitleEditText.setText(selectedEntry.getTitle());
            entryBodyEditText.setText(selectedEntry.getBody());
        } else {
            deleteEntryButton.setVisibility(View.INVISIBLE);
        }
    }


    public void SubmitNewEntry(View v) {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String title = String.valueOf(entryTitleEditText.getText());
        String body = String.valueOf(entryBodyEditText.getText());
        Date createdOn = new Date();

        if (selectedEntry == null) {

            int id = Entry.entryArrayList.size();
            Entry newEntry = new Entry(id, title, body, createdOn);
            Entry.entryArrayList.add(newEntry);
            sqLiteManager.addEntryToDatabase(newEntry);
        } else {
            selectedEntry.setTitle(title);
            selectedEntry.setBody(body);
            sqLiteManager.updateEntryInDB(selectedEntry);
        }

        finish();
    }

    public void DeleteEntry(View v) {
        selectedEntry.setDeletedOn(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.deleteEntryInDB(selectedEntry);
        Entry.entryArrayList.remove(selectedEntry);
        finish();
    }
}
