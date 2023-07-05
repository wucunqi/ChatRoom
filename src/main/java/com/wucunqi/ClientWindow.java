package com.wucunqi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ClientWindow extends JFrame {
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 800;
    //信息区
    JPanel infoArea = new JPanel();
    JLabel username = new JLabel("用户名：",JLabel.CENTER);
    JTextField usernameInfo = new JTextField(10);
    JLabel host = new JLabel("服务器地址：",JLabel.CENTER);
    JTextField hostInfo = new JTextField(10);
    JLabel port = new JLabel("服务器端口号：",JLabel.CENTER);
    JTextField portInfo = new JTextField(10);
    JButton connectButton = new JButton("连接");
    //内容区
    JTextArea contentArea = new JTextArea(32,20);
    JScrollPane contentScrollArea = new JScrollPane(contentArea);
    //输入区
    JPanel inputArea = new JPanel();
    JTextArea inputInfo = new JTextArea(1,15);
    JScrollPane inputInfoScroll = new JScrollPane(inputInfo);
    JButton sendButton = new JButton("发送");
    JSplitPane splitPane;
    //receiveThread用于接收服务器广播消息
    Thread receiveThread;
    public ClientWindow(){
        setTitle("多人聊天室----开发者：吴存其");
        setBounds(500,50,DEFAULT_WIDTH,DEFAULT_HEIGHT);

        infoArea.add(username);infoArea.add(usernameInfo);
        infoArea.add(host);infoArea.add(hostInfo);
        infoArea.add(port);infoArea.add(portInfo);
        infoArea.add(connectButton);

        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);

        inputArea.setLayout(new BorderLayout());
        inputArea.add(inputInfoScroll,BorderLayout.CENTER);
        inputArea.add(sendButton,BorderLayout.EAST);

        add(infoArea,BorderLayout.NORTH);
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,contentScrollArea,inputArea);
        add(splitPane,BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JTextField getUsernameInfo() {
        return usernameInfo;
    }

    public void setUsernameInfo(JTextField usernameInfo) {
        this.usernameInfo = usernameInfo;
    }

    public JTextArea getContentArea() {
        return contentArea;
    }

    public JTextField getHostInfo() {
        return hostInfo;
    }

    public void setHostInfo(JTextField hostInfo) {
        this.hostInfo = hostInfo;
    }

    public JTextField getPortInfo() {
        return portInfo;
    }

    public Thread getReceiveThread() {
        return receiveThread;
    }

    public JButton getConnectButton() {
        return connectButton;
    }

    public void setPortInfo(JTextField portInfo) {
        this.portInfo = portInfo;
    }

    public JTextArea getInputInfo() {
        return inputInfo;
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public void setInputInfo(JTextArea inputInfo) {
        this.inputInfo = inputInfo;
    }
}

