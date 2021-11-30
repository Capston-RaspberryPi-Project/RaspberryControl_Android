package com.sungshin.raspberrycontrol;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class ChipActivity extends AppCompatActivity {

    private Chip[] chip_real_list, chip_set_list;
    private String real_list, set_list;
    private Button btn_all_on, btn_all_off;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chip);

        varInit();          // findViewById
        setPreference();
        initListener();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editor.putString("real", real_list)
                .putString("set", set_list)
                .commit();
        Log.d("pref::", "onDestroy : " + real_list + ", " + set_list);
    }

    void setPreference() {
        real_list = pref.getString("real", "000000");
        set_list = pref.getString("set", "000000");

        for (int i = 0; i < 6; i++) {
            boolean tmp;
            tmp = (real_list.charAt(i)) == '1';
            chip_real_list[i].setSelected(tmp);
            tmp = set_list.charAt(i) == '1';
            chip_set_list[i].setSelected(tmp);
        }

        Log.d("pref::", "onCreate : " + real_list + ", " + set_list);
    }

    void checkSelected() {
        StringBuilder real = new StringBuilder();
        StringBuilder set = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            if (chip_real_list[i].isSelected()) real.append(1);
            else real.append(0);

            if (chip_set_list[i].isSelected()) set.append(1);
            else set.append(0);
        }

        real_list = String.valueOf(real);
        set_list = String.valueOf(set);

        Log.d("chip_real_list", real_list);
        Log.d("chip_set_list ", set_list);
    }

    void sendToServer(String str) {
        SendSetting sendSetting = new SendSetting();
        String send = null;
        if (str.equals("r"))
            send = "r" + real_list;
        if (str.equals("s"))
            send = "s" + set_list;
        sendSetting.execute(send);
    }

    void initListener() {
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            chip_real_list[i].setOnClickListener(v -> {
                chip_real_list[finalI].setSelected(!chip_real_list[finalI].isSelected());
                checkSelected();
                sendToServer("r");
            });

            chip_set_list[i].setOnClickListener(v -> {
                chip_set_list[finalI].setSelected(!chip_set_list[finalI].isSelected());     // isSelected 상태 토글
                chip_real_list[finalI].setSelected(chip_set_list[finalI].isSelected());     // real 을 set 값으로 바꿈
                checkSelected();
                sendToServer("s");
                sendToServer("r");
            });
        }

        btn_all_on.setOnClickListener(v -> {
            for (int i = 0; i < 6; i++)
                chip_real_list[i].setSelected(true);
            real_list = "111111";
            sendToServer("r");
        });

        btn_all_off.setOnClickListener(v -> {
            for (int i = 0; i < 6; i++)
                chip_real_list[i].setSelected(false);
            real_list = "000000";
            sendToServer("r");
        });

    }

    void varInit() {
        chip_real_list = new Chip[6];
        chip_set_list = new Chip[6];

        for (int i = 0; i < 6; i++) {
            chip_real_list[i] = (Chip) findViewById(getResources().getIdentifier("ch_real_" + i, "id", getPackageName()));
            chip_set_list[i] = (Chip) findViewById(getResources().getIdentifier("ch_set_" + i, "id", getPackageName()));
        }
        btn_all_on = findViewById(R.id.btn_all_on);
        btn_all_off = findViewById(R.id.btn_all_off);

        pref = getSharedPreferences("pref", MODE_PRIVATE);
        editor = pref.edit();
    }

}
