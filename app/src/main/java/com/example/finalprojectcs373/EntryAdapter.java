package com.example.finalprojectcs373;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.net.ContentHandler;
import java.text.DateFormat;
import java.util.List;

public class EntryAdapter extends ArrayAdapter<Entry> {

    private Context context;

    public EntryAdapter(Context context, List<Entry> entries) {
        super(context, 0, entries);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Entry entry = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.entry_item, parent, false);
        }

        TextView title = convertView.findViewById(R.id.entryItemTitle);
        TextView createdOn = convertView.findViewById(R.id.entryItemCreatedOnDate);

        title.setText(entry.getTitle());
        createdOn.setText("Created On: " + entry.getCreatedOn().toString());

        int currentMode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        if (currentMode == Configuration.UI_MODE_NIGHT_NO) {
            title.setTextColor(ContextCompat.getColor(context, R.color.black));
            createdOn.setTextColor(ContextCompat.getColor(context, R.color.black));
        } else {
            title.setTextColor(ContextCompat.getColor(context, R.color.nightModeTextColor));
            createdOn.setTextColor(ContextCompat.getColor(context, R.color.nightModeTextColor));
        }

        return convertView;
    }
}
