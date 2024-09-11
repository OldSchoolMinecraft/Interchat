package net.oldschoolminecraft.ic;

import com.earth2me.essentials.Essentials;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Interchat extends JavaPlugin
{
    private static Interchat instance;

    public static Interchat getInstance()
    {
        return instance;
    }

    private Essentials essentials;
    private ChatConfig config;

    public void onEnable()
    {
        instance = this;

        config = new ChatConfig(new File(getDataFolder(), "config.yml"));
        essentials = (Essentials) getServer().getPluginManager().getPlugin("Essentials");

        System.out.println("Interchat enabled");
    }

    public Essentials getEssentials()
    {
        return essentials;
    }

    public ChatConfig getConfig()
    {
        return config;
    }

    public void onDisable()
    {
        System.out.println("Interchat disabled");
    }
}
