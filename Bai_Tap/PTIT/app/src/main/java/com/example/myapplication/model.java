package com.example.myapplication;

public class model {
    private String id, day, money, user, loaiDV;

    public model(String id, String day, String money, String user, String loaiDV) {
        this.id = id;
        this.day = day;
        this.money = money;
        this.user = user;
        this.loaiDV = loaiDV;
    }

    public model() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLoaiDV() {
        return loaiDV;
    }

    public void setLoaiDV(String loaiDV) {
        this.loaiDV = loaiDV;
    }

    @Override
    public String toString() {
        return "PhieuMuon{" +
                "id='" + id + '\n' +
                "day='" + day + '\n' +
                "money='" + money + '\n' +
                "user='" + user + '\n' +
                "loadDichVu='" + loaiDV + '\n' +
                '}';
    }

}
