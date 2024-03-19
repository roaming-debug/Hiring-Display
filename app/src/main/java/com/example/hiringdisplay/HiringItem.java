package com.example.hiringdisplay;

/**
 * Class for the hiring item
 */
public class HiringItem {
    private int id;
    private String name;
    private int listid;

    /**
     * Constructor for the hiring item
     * @param listid The list id of the hiring item
     * @param id The id of the hiring item
     * @param name The name of the hiring item
     */
    public HiringItem(int listid, int id, String name) {
        this.id = id;
        this.name = name;
        this.listid = listid;
    }

    /**
     * This method returns the name of the hiring item
     * @return The name of the hiring item
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the id of the hiring item
     * @return The id of the hiring item
     */
    public int getID() {
        return id;
    }

    /**
     * This method returns the list id of the hiring item
     * @return The list id of the hiring item
     */
    public int getListID() {
        return listid;
    }
}
