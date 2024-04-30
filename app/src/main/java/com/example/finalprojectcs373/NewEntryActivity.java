package com.example.finalprojectcs373;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class NewEntryActivity extends AppCompatActivity {

    private EditText entryTitleEditText, entryBodyEditText;
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
    }

    private void checkForEditEntry() {
        Intent previousIntent = getIntent();

        int passedEntryId = previousIntent.getIntExtra(Entry.ENTRY_EDIT_EXTRA, -1);
        selectedEntry = Entry.getEntryForID(passedEntryId);

        if (selectedEntry != null) {
            entryTitleEditText.setText(selectedEntry.getTitle());
            entryBodyEditText.setText(selectedEntry.getBody());
        }
    }


    public void SubmitNewEntry(View v) {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String title = String.valueOf(entryTitleEditText.getText());
        String body = String.valueOf(entryBodyEditText.getText());

        if (selectedEntry == null) {

            int id = Entry.entryArrayList.size();
            Entry newEntry = new Entry(id, title, body);
            Entry.entryArrayList.add(newEntry);
            sqLiteManager.addEntryToDatabase(newEntry);
        } else {
            selectedEntry.setTitle(title);
            selectedEntry.setBody(body);
            sqLiteManager.updateEntryInDB(selectedEntry);
        }

        finish();
    }
}
