package vn.vinhgaming.tgvnkeepinventoryrune;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TGVNKeepInventoryRune extends JavaPlugin {
    private static TGVNKeepInventoryRune instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        getCommand("keepinventoryrune").setExecutor(new Commands());
        ConfigManager.init();
    }

    public static TGVNKeepInventoryRune getInstance() {
        return instance;
    }
}
