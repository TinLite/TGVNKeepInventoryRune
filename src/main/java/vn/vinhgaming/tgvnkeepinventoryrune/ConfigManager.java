package vn.vinhgaming.tgvnkeepinventoryrune;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class ConfigManager {
    private static FileConfiguration config;
    public static void init() {
        TGVNKeepInventoryRune plugin = TGVNKeepInventoryRune.getInstance();
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = plugin.getConfig();
        if (config.getInt("Config-Version") != 2) {
            plugin.getLogger().severe("Outdated config detected. Please update config.yml file and restart server again.");
            File file = new File(plugin.getDataFolder(), "config.yml");
            if (file.renameTo(new File(plugin.getDataFolder(), "old-config.yml"))) {
                plugin.saveDefaultConfig();
            } else {
                plugin.getLogger().severe("Unable to save new config.yml file. Please grab the new config.yml file in the jar manually.");
            }
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
        TGVNKeepInventoryRune.getInventoryRune().init(
                config.getString("InventoryRune.Material"),
                config.getString("InventoryRune.Name"),
                config.getStringList("InventoryRune.Lore")
        );
        TGVNKeepInventoryRune.getItemRune().init(
                config.getString("ItemRune.Material"),
                config.getString("ItemRune.Name"),
                config.getStringList("ItemRune.Lore"),
                config.getString("ItemRune.ProtectLoreLine"),
                config.getStringList("ItemRune.WhitelistedMaterial")
        );
    }

    public static String getMessage(String path) {
        return Utils.translate(config.getString("Message." + path));
    }

    public static boolean getSetting(String rune, String setting) {
        return config.getBoolean(rune + ".Settings." + setting);
    }
}
