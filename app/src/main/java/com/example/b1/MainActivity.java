package com.example.b1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b1.utils.CarDao;
import com.example.b1.utils.CarDatabase;

import java.util.List;

/**
 * 2019年11月17日16:01:28
 * 版本1.0，未设置dataBinding,后续版本将进一步解耦；
 */
public class MainActivity extends AppCompatActivity {

    //声明各个组件
    private Spinner spinner_carNumbers;
    private TextView yuE;
    private CarDao carDao;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Title");

        setSupportActionBar(myToolbar);
        myToolbar.setNavigationIcon(R.drawable.ic_dehaze_black_24dp);*/

        //去掉自带的标题
        //Objects.requireNonNull(getSupportActionBar()).hide();
        //获取数据库资源
        CarDatabase carDatabase = Room.databaseBuilder(this, CarDatabase.class, "car")
                .allowMainThreadQueries()
                .build();
        carDao = carDatabase.getCarDao();
        //对组件进行绑定
        yuE = findViewById(R.id.zhangHuYuE);
        Button find = findViewById(R.id.find);
        spinner_carNumbers = findViewById(R.id.cheHao);
        editText = findViewById(R.id.chongZhiEditText);
        //创建资源类的实例，并对实例（车牌号）进行自我观察（发生改变时自动对页面进行更新数据）
        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel.getCarNumber().observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(List<Integer> integers) {
                ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                        R.layout.support_simple_spinner_dropdown_item, android.R.id.text1, integers);
                spinner_carNumbers.setAdapter(arrayAdapter);
            }
        });
        //绑定查询按钮，并实现功能
        find.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                yuE.setText(carDao.selectYuE(Integer.valueOf(spinner_carNumbers.getSelectedItem().toString())) + "元");
            }
        });
        //绑定充值按钮，并实现功能
        Button pay = findViewById(R.id.chognZhi);
        pay.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                try {
                    //获取用户输入的当前数值并进行判断，如果数值为非 1-999之间的整数，程序提示“请输入1-999之间的整数”
                    //如果输入内容为非整数，系统会抛出异常并终止程序，使用try，catch进行捕获异常并提示用户输入数字；
                    Integer number = Integer.valueOf(editText.getText().toString());
                    int nowCarNumber = Integer.valueOf(spinner_carNumbers.getSelectedItem().toString());
                    //输入内容为整数，但数值不在规定范围
                    if (!(number <= 999 && number >= 1)) {
                        Toast.makeText(MainActivity.this, "请输入1-999之间的整数！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    carDao.upDateYuE(nowCarNumber, carDao.selectYuE(nowCarNumber) + number);

                } catch (Exception e) {
                    //此处发生异常，进行提示
                    Toast.makeText(MainActivity.this, "请输入1-999之间的整数！", Toast.LENGTH_SHORT).show();
                }
                yuE.setText(carDao.selectYuE(Integer.valueOf(spinner_carNumbers.getSelectedItem().toString())) + "元");
            }
        });

    }

}
