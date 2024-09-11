package net.oldschoolminecraft.ic.net;

import java.net.ServerSocket;
import java.net.Socket;

public class ChatClientListener extends Thread
{
    private ServerSocket serverSocket;
    private boolean running;

    public void start()
    {
        running = true;
        super.start();
    }

    public void run()
    {
        try
        {
            while (running)
            {
                Socket socket = serverSocket.accept();
                new ChatClientHandler(socket).start();
            }
        } catch (Exception ex) {
            System.out.println("Error with chat client listener, err: " + ex.getMessage());
        }
    }

    public void stopListening()
    {
        running = false;
    }
}
