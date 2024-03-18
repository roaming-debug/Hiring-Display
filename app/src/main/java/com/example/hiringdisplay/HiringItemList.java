package com.example.hiringdisplay;

import java.util.ArrayList;

public class HiringItemList {
    private ArrayList<HiringItem> list;
    private int listID;
    public HiringItemList(int listID) {
        list = new ArrayList<HiringItem>();
        this.listID = listID;
    }
    public void addItem(HiringItem item) {
        list.add(item);
    }

    public void sort() {
        list.sort((i1, i2) -> {
            String[] splitedName1 = i1.getName().split(" ");
            String[] splitedName2 = i2.getName().split(" ");
            return Integer.parseInt(splitedName1[splitedName1.length-1])
                    - Integer.parseInt(splitedName2[splitedName2.length-1]);
        });
    }

    public int getListID() {
        return listID;
    }

    public ArrayList<HiringItem> getList() {
        return list;
    }
}
