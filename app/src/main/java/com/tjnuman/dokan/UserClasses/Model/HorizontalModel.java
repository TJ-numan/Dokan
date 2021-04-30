package com.tjnuman.dokan.UserClasses.Model;

public class HorizontalModel {

    String pname, price, description, image,pid;


    public HorizontalModel() {

    }

    public HorizontalModel(String pname, String price, String description, String image,String pid) {
        this.pname = pname;
        this.price = price;
        this.description = description;
        this.image = image;
        this.pid = pid;

    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
