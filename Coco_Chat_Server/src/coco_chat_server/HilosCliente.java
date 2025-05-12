package coco_chat_server;

import java.net.Socket;

public class HilosCliente implements Runnable{
    
    private final Socket clientSocket;
    
    public HilosCliente(Socket socket)
    {
        this.clientSocket = socket;
    }
    
    @Override
    public void run() {
        System.out.println("Cliente conectado desde " + clientSocket.getInetAddress().getHostAddress());
    }
    
}
