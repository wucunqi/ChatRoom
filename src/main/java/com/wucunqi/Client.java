package com.wucunqi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Client {
    static String usernameInfo;
    static String hostInfo;
    static Integer portInfo;
    //与服务器进程连接的socket
    static Socket socket;
    static ClientWindow clientWindow;
    static BufferedReader sin;
    static PrintWriter sout;
    static volatile boolean isConnected = false;
    public static void main(String[] args) {
        //事件分派线程主要负责创建和显示GUI窗体，并处理与窗体相关的事件，以确保图形用户界面的响应和交互，ServerWindow的创建在事件分派线程中执行
        EventQueue.invokeLater(() -> {
            clientWindow = new ClientWindow();

            clientWindow.getConnectButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        usernameInfo = clientWindow.getUsernameInfo().getText();
                        hostInfo = clientWindow.getHostInfo().getText();
                        portInfo = Integer.parseInt(clientWindow.getPortInfo().getText());
                        socket = new Socket(hostInfo, portInfo);
                        sin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        sout = new PrintWriter(socket.getOutputStream());
                        clientWindow.getConnectButton().setEnabled(false);
                        clientWindow.getUsernameInfo().setEditable(false);
                        clientWindow.getHostInfo().setEditable(false);
                        clientWindow.getPortInfo().setEditable(false);

                        String message = "【" + usernameInfo + "】进入了聊天室";
                        sout.println(message);
                        sout.flush();
                        clientWindow.receiveThread = new Thread(()->{
                            while(true){
                                try {
                                    String answerMessage = sin.readLine();
                                    clientWindow.getContentArea().append(answerMessage);
                                    clientWindow.getContentArea().append("\n");
                                } catch (IOException exp) {
                                    throw new RuntimeException(exp);
                                }
                            }

                        });
                        clientWindow.receiveThread.start();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,"信息错误","连接失败",JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            clientWindow.getSendButton().addActionListener((ActionEvent e)->{
                String message = "【" + usernameInfo + "】" + clientWindow.inputInfo.getText();
                clientWindow.inputInfo.setText("");
                //发送给服务器，服务器广播
                sout.println(message);
                sout.flush();
            });
        });
    }
}




