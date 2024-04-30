package com.example.finalprojectcs373;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.net.ContentHandler;
import java.util.List;

public class EntryAdapter extends ArrayAdapter<Entry> {

    public EntryAdapter(Context context, List<Entry> entries) {
        super(context, 0, entries);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Entry entry = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.entry_item, parent, false);
        }

        TextView title = convertView.findViewById(R.id.entryItemTitle);
        TextView body = convertView.findViewById(R.id.entryItemBody);

        title.setText(entry.getTitle());
        body.setText(entry.getBody());

        return convertView;
    }
}
