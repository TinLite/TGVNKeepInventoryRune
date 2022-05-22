package vn.vinhgaming.tgvnkeepinventoryrune;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vn.vinhgaming.tgvnkeepinventoryrune.rune.Rune;

import java.util.Locale;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("kir.admin")) {
            sender.sendMessage(ConfigManager.getMessage("NoPermission"));
            return true;
        }
        if (args.length == 0) return false;
        switch (args[0].toLowerCase(Locale.ROOT)) {
            case "give":
                if (args.length < 2) {
                    sender.sendMessage(String.format(ChatColor.RED + "Missing argruments. Please type /%s help for help", label));
                    return true;
                }
                Rune rune;
                int amount = 1;
                if (args[1].equalsIgnoreCase("inventory")) rune = TGVNKeepInventoryRune.getInventoryRune();
                else if (args[1].equalsIgnoreCase("item")) rune = TGVNKeepInventoryRune.getItemRune();
                else {
                    sender.sendMessage(ChatColor.RED + String.format("%s is not a vaild rune type. Available type: Inventory, Item", args[1]));
                    return true;
                }
                if (args.length >= 3) {
                    try {
                        amount = Integer.parseInt(args[2]);
                    } catch (NumberFormatException exception) {
                        sender.sendMessage(String.format(ChatColor.RED + "%s is not a number.", args[2]));
                        return true;
                    }
                }
                if (!(sender instanceof Player) && args.length < 4) {
                    sender.sendMessage(ChatColor.RED + "Console must add player's name to the command.");
                    return true;
                }
                if (args.length >= 4) {
                    Player target = Bukkit.getPlayerExact(args[3]);
                    if (target == null) {
                        sender.sendMessage(ChatColor.RED + "Player not found.");
                        return true;
                    }
                    target.getInventory().addItem(rune.getRune(amount));
                    sender.sendMessage(ChatColor.GREEN + String.format("%s received %d rune(s).", args[3], amount));
                    return true;
                }
                ((Player) sender).getInventory().addItem(rune.getRune(amount));
                sender.sendMessage(ChatColor.GREEN + String.format("You received %d rune(s).", amount));
                return true;
            case "removeprotection":
            case "rp":
                // /kir removeprotection
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.RED + "Only player can execute this command.");
                    return true;
                }
                TGVNKeepInventoryRune.getItemRune().removeLore(((Player) sender).getInventory().getItemInMainHand());
                sender.sendMessage(ChatColor.GREEN + "Attempted removing protection status from item in your main hand.");
                return true;
            case "reload":
                ConfigManager.init();
                sender.sendMessage(ChatColor.GREEN + "Configuration reloaded.");
                return true;
            case "help":
                sender.sendMessage(Utils.translate("&f&lTG&c&lVN&aKeepInventoryRune &ev" + TGVNKeepInventoryRune.getInstance().getDescription().getVersion() + " by VinhGaming"));
                sender.sendMessage(Utils.translate("&a/kir give <inventory/item> [amount] [playername] &eGive rune to player"));
                sender.sendMessage(Utils.translate("&a/kir removeprotection|rp &eRemove ItemRune protection status from item in your main hand"));
                sender.sendMessage(Utils.translate("&a/kir reload &eReload configuration"));
                return true;
            default:
                sender.sendMessage(ChatColor.RED + String.format("Unknown command. Please type /%s help for help.", label));
        }
        return false;
    }
}
