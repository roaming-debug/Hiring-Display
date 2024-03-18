package com.example.hiringdisplay;

public class HiringItem {
    private int id;
    private String name;
    private int listid;

    public HiringItem(int listid, int id, String name) {
        this.id = id;
        this.name = name;
        this.listid = listid;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    public int getListID() {
        return listid;
    }
}
