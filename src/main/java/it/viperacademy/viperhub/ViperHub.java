package it.viperacademy.viperhub;

import it.viperacademy.viperhub.listener.HubListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ViperHub extends JavaPlugin {

    private static ViperHub instance;

    public static ViperHub getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        long time = System.currentTimeMillis();

        saveDefaultConfig();
        reloadConfig();

        Bukkit.getPluginManager().registerEvents(new HubListener(this), this);

        long after = System.currentTimeMillis() - time;
        Bukkit.getLogger().info("[ViperHub] Plugin abilitato in " + after + "ms.");
    }

    @Override
    public void onDisable() {
        long time = System.currentTimeMillis();
        long after = System.currentTimeMillis() - time;
        Bukkit.getLogger().info("[ViperHub] Plugin disabilitato in " + after + "ms.");
    }
}
