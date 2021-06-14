package vn.vinhgaming.tgvnkeepinventoryrune;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Rune {
    private static ItemStack rune;
    private static ItemMeta meta;

    /**
     * Init Rune item
     * @param material Rune material
     * @param name Rune item name
     * @param lore Lore
     */
    public static void init(String material, String name, List<String> lore) {
        rune = new ItemStack(Material.getMaterial(material), 1);
        meta = rune.getItemMeta();
        meta.setDisplayName(Utils.translate(name));
        meta.setLore(Utils.translate(lore));
        rune.setItemMeta(meta);
    }

    /**
     * Get rune item
     * @param amount ItemStack amount
     * @return ItemStack
     */
    public static ItemStack getRune(int amount) {
        if (amount < 1) return null;
        ItemStack nrune = rune.clone();
        nrune.setAmount(amount);
        return nrune;
    }

    /**
     * Check if an item are rune item
     * @param item Item to check. Can be null.
     * @return true if item are rune item. false if not or null.
     */
    public static boolean checkRune(ItemStack item) {
        if (item == null) return false;
        return item.getType().equals(rune.getType()) && item.getItemMeta().equals(meta);
    }
}
