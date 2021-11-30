package com.sungshin.raspberrycontrol;

import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SendSetting extends AsyncTask {
    private Exception exception;

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            Socket socket = new Socket("220.69.172.156", 8080);
            PrintWriter outToServer = new PrintWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()));
            Log.d("Print Value", objects[0].toString());
            outToServer.print(objects[0].toString());
            outToServer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
