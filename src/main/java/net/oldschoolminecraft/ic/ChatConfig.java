package net.oldschoolminecraft.ic;

import org.bukkit.util.config.Configuration;

import java.io.File;

public class ChatConfig extends Configuration
{
    public ChatConfig(File file)
    {
        super(file);
        reload();
    }

    public void reload()
    {
        load();
        write();
        save();
    }

    public void write()
    {
        generateConfigOption("listener.host", "127.0.0.1");
        generateConfigOption("listener.port", 9673);
        generateConfigOption("listener.secret", Util.generateSecurePassword(16));
        generateConfigOption("forward.host", "INSERT_HOST_HERE");
        generateConfigOption("forward.port", 9673);
        generateConfigOption("forward.secret", "INSERT_SECRET_HERE");
        generateConfigOption("chatMessageFormat", "[$PREFIX] $USERNAME: $MESSAGE");
    }

    private void generateConfigOption(String key, Object defaultValue)
    {
        if (this.getProperty(key) == null) this.setProperty(key, defaultValue);
        final Object value = this.getProperty(key);
        this.removeProperty(key);
        this.setProperty(key, value);
    }

    public String getStringOption(String key)
    {
        return String.valueOf(getConfigOption(key));
    }

    public String getStringOption(String key, String defaultValue)
    {
        return String.valueOf(getConfigOption(key, defaultValue));
    }

    public Object getConfigOption(String key)
    {
        return this.getProperty(key);
    }

    public Object getConfigOption(String key, Object defaultValue)
    {
        Object value = getConfigOption(key);
        if (value == null) value = defaultValue;
        return value;
    }
}
