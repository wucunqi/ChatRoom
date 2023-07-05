package com.wucunqi;

public class ServerThread extends Thread{
    ThreadHandler handler;
    public ServerThread(ThreadHandler handler){
        this.handler = handler;
    }
    @Override
    public void run() {
        handler.run();
    }
}
