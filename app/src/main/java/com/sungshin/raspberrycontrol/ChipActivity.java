package com.sungshin.raspberrycontrol;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class ChipActivity extends AppCompatActivity {
    private static String SERVER_IP = "220.69.172.156";
    private int port = 8080;

    Chip[] chip_real_list;
    Chip[] chip_set_list;
    SendSetting send;
    String real_list;
    String set_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chip);

        varInit();          // findViewById
        initListener();


        /** 꼭 읽어주세요!  (효림)
         *
         * 버튼 색깔은 클릭할때마다 내부적으로 자동 바뀜
         * chip 클릭마다 40 line 의 CheckSelected() 가 작동
         * 51, 52 line에서 처럼 real, set이 스위치 정보를 담고 있음.
         * */
    }

    void checkSelected() {
        StringBuilder real = new StringBuilder();
        StringBuilder set = new StringBuilder();

        for (int i = 0 ; i< 6; i++){
            if (chip_real_list[i].isSelected()) real.append(1);
            else real.append(0);

            if (chip_set_list[i].isSelected()) set.append(1);
            else set.append(0);
        }

        real_list = String.valueOf(real);
        set_list = String.valueOf(set);

        Log.d("chip_real_list", real_list);
        Log.d("chip_set_list ", set_list);


        // 일단은 졸음 감지 전이므로 real_list만 넘기는 것부터 할게 !
        send = new SendSetting();
        send.execute(real_list); // r010010

//        send2 = new SendSetting();
//        send2.excute(set_list); // s000100

        //send.execute(set_list);

        real_list="";
        set_list="";
    }

    void initListener() {
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            chip_real_list[i].setOnClickListener(v -> {
                chip_real_list[finalI].setSelected(!chip_real_list[finalI].isSelected());
                checkSelected();
            });

            chip_set_list[i].setOnClickListener(v -> {
                chip_set_list[finalI].setSelected(!chip_set_list[finalI].isSelected());     // isSelected 상태 토글
                chip_real_list[finalI].setSelected(chip_set_list[finalI].isSelected());     // real 을 set 값으로 바꿈
                checkSelected();
            });
        }
    }

    void varInit() {
        chip_real_list = new Chip[6];
        chip_set_list = new Chip[6];

        for (int i = 0; i < 6; i++) {
            chip_real_list[i] = (Chip) findViewById(getResources().getIdentifier("ch_real_" + i, "id", getPackageName()));
            chip_set_list[i] = (Chip) findViewById(getResources().getIdentifier("ch_set_" + i, "id", getPackageName()));
        }
    }

}
