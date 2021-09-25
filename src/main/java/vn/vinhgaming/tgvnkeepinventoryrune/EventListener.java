package vn.vinhgaming.tgvnkeepinventoryrune;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import vn.vinhgaming.tgvnkeepinventoryrune.rune.ItemRune;

public class EventListener implements Listener {
    @EventHandler
    public static void onRespawn(PlayerRespawnEvent event) {
        TGVNKeepInventoryRune.getItemRune().returnInventory(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public static void onClick(InventoryClickEvent event) {
        // Viết xong câu if này tôi chỉ muốn tự sát thôi
        if (!event.isCancelled() // Nếu trước đó đã bị cancelled bởi plugin khác thì dẹp đi là vừa
                && (event.getAction() == InventoryAction.SWAP_WITH_CURSOR)
                && TGVNKeepInventoryRune.getItemRune().checkRune(event.getCursor())
                && event.getCurrentItem() != null // Kiểm tra xem có null không trước khi getType().name(), để không dính NullPointerException
                && TGVNKeepInventoryRune.getItemRune().isWhitelisted(event.getCurrentItem().getType().name())) {

            event.setCancelled(true);
            HumanEntity player = event.getWhoClicked();
            if (event.getCursor().getAmount() != 1) {
                player.sendMessage(ConfigManager.getMessage("ItemRuneErrorStackedRune"));
                return;
            }
            if (event.getCurrentItem().getAmount() != 1) {
                player.sendMessage(ConfigManager.getMessage("ItemRuneErrorStackedItem"));
                return;
            }
            ItemStack item = event.getCurrentItem();
            if (TGVNKeepInventoryRune.getItemRune().checkItem(item)) {
                player.sendMessage(ConfigManager.getMessage("ItemRuneErrorProtectedAlready"));
                return;
            }
            event.getWhoClicked().setItemOnCursor(null);
            item = TGVNKeepInventoryRune.getItemRune().addLore(item);
            event.setCurrentItem(item);
            player.sendMessage(ConfigManager.getMessage("ItemRuneAppied"));
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        PlayerInventory inventory = player.getInventory();
        for (int index = 0 ; index < inventory.getSize(); index++)
            if (TGVNKeepInventoryRune.getInventoryRune().checkRune(inventory.getItem(index))) {
                ItemStack item = TGVNKeepInventoryRune.getInventoryRune().getRune(inventory.getItem(index).getAmount() - 1);
                inventory.setItem(index, item);
                if (ConfigManager.getSetting("InventoryRune", "KeepLevel")) {
                    event.setKeepLevel(true);
                    event.setDroppedExp(0);
                }
                if (ConfigManager.getSetting("InventoryRune", "KeepInventory")) {
                    event.setKeepInventory(true);
                    event.getDrops().clear();
                }
                if (item != null) player.sendMessage(ConfigManager.getMessage("InventoryRuneUsed"));
                    else player.sendMessage(ConfigManager.getMessage("LastInventoryRuneUsed"));
                // We return because inventory are keeped by InventoryRune, we don't want to check and remove lore of already keeped items
                return;
            }
        ItemRune itemRune = TGVNKeepInventoryRune.getItemRune();
        ItemStack[] contents = new ItemStack[inventory.getSize()];
        boolean weHaveSomethingToReturn = false;
        for (int index = 0; index < inventory.getSize(); index++) {
            if (itemRune.checkItem(inventory.getItem(index))) {
                ItemStack itemStack = inventory.getItem(index);
                itemStack = itemRune.removeLore(itemStack);
                contents[index] = itemStack;
                weHaveSomethingToReturn = true;
                event.getDrops().remove(itemStack);
            }
        }
        if (weHaveSomethingToReturn) itemRune.addInventory(event.getEntity().getName(), contents);
    }
}
