package com.example.finalprojectcs373;

import java.util.ArrayList;
import java.util.Date;

public class Entry {

    public static ArrayList<Entry> entryArrayList = new ArrayList<>();
    public static String ENTRY_EDIT_EXTRA = "entryEdit";

    private int id;
    private String title;
    private String body;
    private Date createdOn;
    private Date deletedOn;

    public Entry(int id, String title, String body, Date createdOn) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.createdOn = createdOn;
    }

    public Entry(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public static Entry getEntryForID(int passedEntryId) {
        for (Entry entry : entryArrayList) {
            if (entry.getId() == passedEntryId) {
                return entry;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(Date deletedOn) {
        this.deletedOn = deletedOn;
    }

}
