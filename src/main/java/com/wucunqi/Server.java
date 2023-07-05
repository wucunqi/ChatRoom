package com.wucunqi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static boolean isListening = true;
    static ServerWindow serverWindow;
    //为每一个连接到服务器的socket分配一个线程
    static ArrayList<ServerThread> threadArrayList = new ArrayList<>();
    //向所有客户端广播消息
    public static void broadcast(String answerMessage){
        for(ServerThread t : threadArrayList){
            t.handler.answerMessage(answerMessage);
        }
        serverWindow.getContentArea().append(answerMessage);
        serverWindow.getContentArea().append("\n");
    }
    public static void main(String[] args){
        //事件分派线程主要负责创建和显示GUI窗体，并处理与窗体相关的事件，以确保图形用户界面的响应和交互
        EventQueue.invokeLater(()->{
            serverWindow = new ServerWindow();
            serverWindow.getButton().addActionListener((ActionEvent e)->{
                String message = "【" + "管理员" + "】" + serverWindow.getInputArea().getText();
                serverWindow.getInputArea().setText("");
                broadcast(message);
            });
        });

        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while(isListening){
                Socket socket = serverSocket.accept();
                //替换为进入聊天室
                System.out.println("连接成功");
                ThreadHandler hander = new ThreadHandler(socket);
                ServerThread serverThread = new ServerThread(hander);
                threadArrayList.add(serverThread);
                serverThread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

