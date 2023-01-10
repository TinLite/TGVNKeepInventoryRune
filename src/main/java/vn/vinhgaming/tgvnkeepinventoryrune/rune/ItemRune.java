package vn.vinhgaming.tgvnkeepinventoryrune.rune;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import vn.vinhgaming.tgvnkeepinventoryrune.ConfigManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemRune extends Rune {
    String protectedLore;
    Map<String, ItemStack[]> invList;
    List<String> whitelistedMaterial;

    @Override
    public void init(ConfigurationSection section) {
        this.whitelistedMaterial = section.getStringList("WhitelistedMaterial");
        this.protectedLore = section.getString("ProtectLoreLine");
        invList = new HashMap<>();
        super.init(section);
    }

    /**
     * Check if item contain the defined lore
     *
     * @param item The item to check
     * @return true false, what else?
     */
    public boolean checkItem(ItemStack item) {
        return item != null && item.getItemMeta().hasLore() && item.getItemMeta().getLore().contains(protectedLore);
    }

    public ItemStack addLore(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
        lore.add(protectedLore);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack removeLore(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (!meta.hasLore()) return item;
        List<String> lore = meta.getLore();
        lore.remove(protectedLore);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public void addInventory(String player, ItemStack[] contents) {
        invList.put(player, contents);
    }

    public void returnInventory(Player player) {
        if (!invList.containsKey(player.getName())) return;
        player.getInventory().setContents(invList.get(player.getName()));
        player.sendMessage(ConfigManager.getMessage("ItemRuneUsed"));
        invList.remove(player.getName());
    }

    public boolean isWhitelisted(String name) {
        return whitelistedMaterial.contains("*") || whitelistedMaterial.contains(name);
    }
}
