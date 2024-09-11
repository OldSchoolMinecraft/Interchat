package net.oldschoolminecraft.ic.net;

import com.earth2me.essentials.Essentials;
import com.google.gson.Gson;
import net.oldschoolminecraft.ic.Interchat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClientHandler extends Thread
{
    private static Gson gson = new Gson();

    private Socket socket;
    private DataInputStream dis;
    private boolean loggedIn = false;

    public ChatClientHandler(Socket socket)
    {
        this.socket = socket;

        try
        {
            this.dis = new DataInputStream(socket.getInputStream());
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    public void run()
    {
        try
        {
            while (socket.isConnected() && !socket.isClosed())
            {
                if (!loggedIn)
                {
                    String secret = this.dis.readUTF();
                    if (secret.equals(Interchat.getInstance().getConfig().getStringOption("listener.secret")))
                    {
                        loggedIn = true;
                        System.out.println("[Interchat] Relay login from " + socket.getInetAddress().getHostAddress() + " successful");
                    } else {
                        System.out.println("[Interchat] Relay login from " + socket.getInetAddress().getHostAddress() + " failed");
                        closeSocket();
                        return;
                    }
                }

                String inboundChat = this.dis.readUTF();

                if (inboundChat.equals("~")) // keep alive
                    return;

                ChatMessage message = gson.fromJson(inboundChat, ChatMessage.class);
                for (Player player : Bukkit.getOnlinePlayers())
                {
                    boolean ignored = false; // TODO: check if the player wants to ignore interchat messages
                    boolean muted = false; // TODO: check if the player receiving the message has ignored the sender
                }
            }
        } catch (Exception ex) {
            closeSocket();
        }
    }

    private void closeSocket()
    {
        try
        {
            this.socket.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
