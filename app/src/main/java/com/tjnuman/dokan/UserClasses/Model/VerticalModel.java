package com.tjnuman.dokan.UserClasses.Model;

import java.util.ArrayList;

public class VerticalModel {

    String category;
    //FirebaseRecyclerOptions<HorizontalModel>Options;
    ArrayList<HorizontalModel> myList;

    public VerticalModel(String category, ArrayList<HorizontalModel> myList) {
        this.category = category;
        this.myList = myList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<HorizontalModel> getMyList() {
        return myList;
    }

    public void setMyList(ArrayList<HorizontalModel> myList) {
        this.myList = myList;
    }
}
