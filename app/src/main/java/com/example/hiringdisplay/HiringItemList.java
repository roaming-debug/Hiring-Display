package com.example.hiringdisplay;

import java.util.ArrayList;

/**
 * Class for the hiring item list
 */
public class HiringItemList {
    private ArrayList<HiringItem> list;
    private int listID;

    /**
     * Constructor for the hiring item list
     * @param listID The list id of the hiring item list
     */
    public HiringItemList(int listID) {
        list = new ArrayList<HiringItem>();
        this.listID = listID;
    }

    /**
     * This method adds an item to the hiring item list
     * @param item The hiring item to add
     */
    public void addItem(HiringItem item) {
        list.add(item);
    }

    /**
     * This method sorts the hiring item list
     */
    public void sort() {
        list.sort((i1, i2) -> {
            String[] splitedName1 = i1.getName().split(" ");
            String[] splitedName2 = i2.getName().split(" ");
            return Integer.parseInt(splitedName1[splitedName1.length-1])
                    - Integer.parseInt(splitedName2[splitedName2.length-1]);
        });
    }

    /**
     * This method returns the list id of the hiring item list
     * @return The list id of the hiring item list
     */
    public int getListID() {
        return listID;
    }

    /**
     * This method returns the list of hiring items
     * @return The list of hiring items
     */
    public ArrayList<HiringItem> getList() {
        return list;
    }
}
