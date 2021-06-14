package vn.vinhgaming.tgvnkeepinventoryrune;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    private static FileConfiguration config;
    public static void init() {
        TGVNKeepInventoryRune plugin = TGVNKeepInventoryRune.getInstance();
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = plugin.getConfig();
        Rune.init(
                config.getString("Rune.Material"),
                config.getString("Rune.Name"),
                config.getStringList("Rune.Lore")
        );
    }

    public static String getMessage(String path) {
        return Utils.translate(config.getString("Message." + path));
    }

    public static boolean getSetting(String setting) {
        return config.getBoolean("Settings." + setting);
    }
}
