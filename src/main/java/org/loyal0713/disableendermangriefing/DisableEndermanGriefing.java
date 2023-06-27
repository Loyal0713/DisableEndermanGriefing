package org.loyal0713.disableendermangriefing;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisableEndermanGriefing extends JavaPlugin implements Listener {
    private FileConfiguration config;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);

        config = getConfig();
        config.addDefault("verboseLogging", false);
        config.options().copyDefaults(true);
        saveConfig();
    }

    @EventHandler
    public void onEndermanPickup(EntityChangeBlockEvent event) {
        if (event.getEntityType() == org.bukkit.entity.EntityType.ENDERMAN) {
            if (config.getBoolean("verboseLogging")) {
                getLogger().info("Enderman tried to pick up a block - BLOCKED");
            }
            event.setCancelled(true);
        }
    }
}
