package com.sungshin.raspberrycontrol;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private SwitchCompat sw1, sw2, sw3, sw4, sw5, sw6;
    private Button btnGet;
    private static String SERVER_IP = "220.69.172.43";
    private int port = 8080;
    String settingText;
    SendSetting sendSetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sw1 = (SwitchCompat) findViewById(R.id.switch1);
        sw2 = (SwitchCompat) findViewById(R.id.switch2);
        sw3 = (SwitchCompat) findViewById(R.id.switch3);
        sw4 = (SwitchCompat) findViewById(R.id.switch4);
        sw5 = (SwitchCompat) findViewById(R.id.switch5);
        sw6 = (SwitchCompat) findViewById(R.id.switch6);
        btnGet = (Button) findViewById(R.id.getBtn);


        initListener();

    }

    private void getSwitchData() {
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

        settingText = "Switch1 -  " + str1 + " \n" + "Switch2 - " + str2 + " \n"
                + "Switch3 -  " + str3 + " \n" + "Switch4 -  " + str4 + " \n" + "Switch5 -  " + str5 + " \n"
                + "Switch6 -  " + str6;
        Toast.makeText(getApplicationContext(), settingText, Toast.LENGTH_SHORT).show();

    }


    private void initListener() {

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSwitchData();
                new SendSetting().execute(settingText);
                settingText = "";
            }
        });
    }
}