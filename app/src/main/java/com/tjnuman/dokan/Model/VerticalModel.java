package com.tjnuman.dokan.Model;

import java.util.ArrayList;

public class VerticalModel {

    String category;
    ArrayList<HorizontalModel> HorizontalArrayList;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<HorizontalModel> getHorizontalArrayList() {
        return HorizontalArrayList;
    }

    public void setHorizontalArrayList(ArrayList<HorizontalModel> horizontalArrayList) {
        HorizontalArrayList = horizontalArrayList;
    }
}
