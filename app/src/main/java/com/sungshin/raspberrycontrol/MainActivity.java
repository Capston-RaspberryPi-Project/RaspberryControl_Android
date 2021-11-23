package com.sungshin.raspberrycontrol;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity {
    private SwitchCompat sw1,sw2,sw3,sw4,sw5,sw6;
    private  Button btnGet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sw1 = (SwitchCompat)findViewById(R.id.switch1);
        sw2 = (SwitchCompat)findViewById(R.id.switch2);
        sw3 = (SwitchCompat)findViewById(R.id.switch3);
        sw4 = (SwitchCompat)findViewById(R.id.switch4);
        sw5 = (SwitchCompat)findViewById(R.id.switch5);
        sw6 = (SwitchCompat)findViewById(R.id.switch6);
        btnGet = (Button)findViewById(R.id.getBtn);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1, str2, str3, str4, str5, str6;
                if (sw1.isChecked())
                    str1 = sw1.getTextOn().toString();
                else
                    str1 = sw1.getTextOff().toString();

                if (sw2.isChecked())
                    str2 = sw2.getTextOn().toString();
                else
                    str2 = sw2.getTextOff().toString();

                if (sw3.isChecked())
                    str3 = sw3.getTextOn().toString();
                else
                    str3 = sw3.getTextOff().toString();

                if (sw4.isChecked())
                    str4 = sw4.getTextOn().toString();
                else
                    str4 = sw4.getTextOff().toString();

                if (sw5.isChecked())
                    str5 = sw5.getTextOn().toString();
                else
                    str5 = sw5.getTextOff().toString();

                if (sw6.isChecked())
                    str6 = sw6.getTextOn().toString();
                else
                    str6 = sw6.getTextOff().toString();

                Toast.makeText(getApplicationContext(), "Switch1 -  " + str1 + " \n" + "Switch2 - " + str2+ " \n"
                                +"Switch3 -  " + str3 + " \n" + "Switch4 -  " + str4 + " \n" + "Switch5 -  " + str5 + " \n"
                                +"Switch6 -  " + str6 + " \n"


                        , Toast.LENGTH_SHORT).show();
            }
        });
    }
}