package com.example.b1.utils;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * 对车辆功能的增删改查等功能，为接口类型，当程序编译时，系统会根据继承了RoomDatabase的类自动生成方法体；
 */
@Dao
public interface CarDao {
    @Insert
    void insertCar(Car...cars);//插入一条完整的车辆信息；

    @Query("update Car set yuE = :yuE where carNumber = :carNumber")
    void upDateYuE(int carNumber,int yuE);//根据车号对余额进行修改

    @Query("select yuE from Car where carNumber = :carNumber")
    int selectYuE(int carNumber);//根据车号查询余额

    @Query("select * from Car ")
    List<Car> selectAllCar();//获取所有车辆

}
