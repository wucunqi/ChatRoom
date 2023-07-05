package com.wucunqi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ServerWindow extends JFrame {
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 800;
    JTextArea contentArea = new JTextArea(32,20);
    JScrollPane contentScrollPane = new JScrollPane(contentArea);
    JTextArea inputArea = new JTextArea(1,15);
    JScrollPane inputScrollPane = new JScrollPane(inputArea);
    JButton button = new JButton("发送");
    JPanel inputPane = new JPanel();
    JSplitPane splitPane;
    public ServerWindow(){
        setTitle("多人聊天室服务器----开发者：吴存其");
        setBounds(500,50,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        contentArea.setEditable(false);
        inputPane.setLayout(new BorderLayout());
        inputPane.add(inputScrollPane, BorderLayout.CENTER);
        inputPane.add(button, BorderLayout.EAST);
        inputPane.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            int width = getWidth();
            int height = getHeight();
            // 根据容器的宽度调整消息编辑区和发送按钮的大小
            int textAreaWidth = (int) (width * 0.9);
            int buttonWidth = width - textAreaWidth;
            int componentHeight = height;

            inputScrollPane.setPreferredSize(new Dimension(textAreaWidth, componentHeight));
            button.setPreferredSize(new Dimension(buttonWidth, componentHeight));
            }
        });
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,contentScrollPane,inputPane);
        add(splitPane);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public JTextArea getContentArea() {
        return contentArea;
    }

    public JButton getButton() {
        return button;
    }

    public JTextArea getInputArea() {
        return inputArea;
    }
}
