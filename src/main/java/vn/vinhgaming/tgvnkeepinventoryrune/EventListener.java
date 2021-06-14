package vn.vinhgaming.tgvnkeepinventoryrune;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class EventListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        PlayerInventory inventory = player.getInventory();
        for (int index = 0 ; index < inventory.getSize(); index++)
            if (Rune.checkRune(inventory.getItem(index))) {
                ItemStack item = Rune.getRune(inventory.getItem(index).getAmount() - 1);
                inventory.setItem(index, item);
                if (ConfigManager.getSetting("KeepLevel")) {
                    event.setKeepLevel(true);
                    event.setDroppedExp(0);
                }
                if (ConfigManager.getSetting("KeepInventory")) {
                    event.setKeepInventory(true);
                    event.getDrops().clear();
                }
                if (item != null) player.sendMessage(ConfigManager.getMessage("RuneUsed"));
                    else player.sendMessage(ConfigManager.getMessage("LastRuneUsed"));
                break;
            }
    }
}
