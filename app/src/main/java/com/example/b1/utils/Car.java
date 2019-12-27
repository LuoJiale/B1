package com.example.b1.utils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 车辆的实现类，请学习过Room后再阅读；
 */
@Entity
public class Car {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "carNumber")
    private Integer carNumber;

    @ColumnInfo(name = "yuE")
    private int yuE;

    @ColumnInfo(name = "person")
    private String person;

    public Car(Integer carNumber, int yuE, String person) {
        this.carNumber = carNumber;
        this.yuE = yuE;
        this.person = person;
    }

    public Integer getCarNumber() {
        return carNumber;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public void setCarNumber(Integer carNumber) {
        this.carNumber = carNumber;
    }

    int getYuE() {
        return yuE;
    }

    public void setYuE(int yuE) {
        this.yuE = yuE;
    }

    String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
