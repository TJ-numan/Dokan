package com.tjnuman.dokan.Model;

public class CartListModel {
    String pname,date,pimage,price,qunatity,pid;

    public CartListModel() {
    }

    public CartListModel(String pname, String date, String pimage, String price, String qunatity, String pid) {
        this.pname = pname;
        this.date = date;
        this.pimage = pimage;
        this.price = price;
        this.qunatity = qunatity;
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQunatity() {
        return qunatity;
    }

    public void setQunatity(String qunatity) {
        this.qunatity = qunatity;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
