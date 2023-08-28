package it.viperacademy.viperhub.listener;

import it.viperacademy.viperhub.ViperHub;
import it.viperacademy.viperhub.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class HubListener implements Listener {

    private final ViperHub plugin;

    public HubListener(ViperHub plugin) {
        this.plugin = plugin;
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void OnJoin(PlayerJoinEvent event) {

        double x = plugin.getConfig().getDouble("spawn-coordinate.x");
        double y = plugin.getConfig().getDouble("spawn-coordinate.y");
        double z = plugin.getConfig().getDouble("spawn-coordinate.z");
        String worldName = plugin.getConfig().getString("spawn-coordinate.mondo");

        World world = Bukkit.getWorld(worldName);

        Player p = event.getPlayer();
        if (world != null) {
            Location teleportLocation = new Location(world, x, y, z);
            p.teleport(teleportLocation);
        } else {
            Bukkit.getLogger().info("Il mondo specificato non esiste.");
        }
    }

    @EventHandler
    public void OnDrop(PlayerDropItemEvent event) {
        Player p = event.getPlayer();
        event.setCancelled(true);
        p.sendMessage(StringUtil.cc("&cNon puoi droppare oggetti."));

    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        String permesso = plugin.getConfig().getString("permessi.chat");

        if (!p.hasPermission(permesso)) {
            event.setCancelled(true);
            p.sendMessage(StringUtil.cc("&cNon puoi inviare messaggi alla hub."));
            return;
        }

        event.setCancelled(false);
    }
}
