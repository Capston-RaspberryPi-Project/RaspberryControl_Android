package com.sungshin.rapberrycontrol;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class MainActivity extends AppCompatActivity {

    Chip[] chip_real_list = new Chip[6];
    Chip[] chip_set_list = new Chip[6];
    ChipGroup chip_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raspberryandroid);

        for(int i=0; i<chip_real_list.length; i++) {
            chip_real_list[i] = findViewById(getResources().getIdentifier("realSW" + (i+1),"id", getPackageName()));
            chip_set_list[i] = findViewById(getResources().getIdentifier("setSW" + (i+1),"id", getPackageName()));

            int finalI = i;
            chip_real_list[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 원하는 onclickListener 작성
                    Toast.makeText(getApplicationContext(), "Real : " + chip_real_list[finalI].getText(), Toast.LENGTH_SHORT).show();
                }
            });
            chip_set_list[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 원하는 onclickListener 작성
                    Toast.makeText(getApplicationContext(), "Set : " + chip_set_list[finalI].getText(), Toast.LENGTH_SHORT).show();
                }
            });

            chip_real_list[i].setOnCheckedChangeListener((buttonView, isChecked) -> {
                if(isChecked) {
                    chip_real_list[finalI].setChipBackgroundColor(getColorStateList(R.color.custom_chip_color));
                }
                else {
                    chip_real_list[finalI].setChipBackgroundColor(getColorStateList(R.color.custom_chip_defalut_color));
                }
            });
            chip_set_list[i].setOnCheckedChangeListener((buttonView, isChecked) -> {
                if(isChecked) {
                    chip_set_list[finalI].setChipBackgroundColor(getColorStateList(R.color.custom_chip_color));
                }
                else {
                    chip_set_list[finalI].setChipBackgroundColor(getColorStateList(R.color.custom_chip_defalut_color));
                }
            });
        }

    }
}