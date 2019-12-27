package com.example.b1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.b1.utils.Car;
import com.example.b1.utils.CarDao;
import com.example.b1.utils.CarDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类仅仅对车牌号进行数据分离，余额并没有设置自我观察，因为题目的是点击查询按钮后才更新
 */
public class MyViewModel extends AndroidViewModel {

    //获取数据库资源
    private CarDao carDao;
    public MyViewModel(@NonNull Application application) {
        super(application);
        CarDatabase carDatabase = CarDatabase.getCarDatabase(application);
        carDao = carDatabase.getCarDao();
    }

    //创建一个自我观察的车牌号变量，类型为Integer型数组
    private MutableLiveData<List<Integer>> carNumber;
    private List<Integer> carNumbers = new ArrayList<>();
    //增加车辆完整信息（仅为测试时创建，实际完成后程序需求并没有使用）
    public void addCar(Car car) {
        carDao.insertCar(car);
        Integer newCarNumber = car.getCarNumber();
        carNumbers.add(newCarNumber);

        carNumber.setValue(carNumbers);
    }
    //获取车牌号的集合，返回类型为可变集合，当内容发生改变时会通知页面进行刷新
    MutableLiveData<List<Integer>> getCarNumber() {
        if (carNumber == null) {
            carNumber = new MutableLiveData<>();
            List<Car> cars = carDao.selectAllCar();
            for (Car car : cars) {
                carNumbers.add(car.getCarNumber());
            }
            carNumber.setValue(carNumbers);
        }
        return carNumber;
    }

}
