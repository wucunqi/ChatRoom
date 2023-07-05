package com.wucunqi;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

class ThreadHandler implements Runnable {
    Socket socket;
    String message = null;
    BufferedReader cin;
    PrintWriter cout;
    public ThreadHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            cin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            cout = new PrintWriter(socket.getOutputStream());
            while(true){
                message = cin.readLine();
                if(message != null){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    String time = simpleDateFormat.format(new Date());
                    Server.broadcast(time + " " + message);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void answerMessage(String answerMessage){
        cout.println(answerMessage);
        cout.flush();
    }
}
