package vn.vinhgaming.tgvnkeepinventoryrune;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("kir.admin")) {
            sender.sendMessage(ConfigManager.getMessage("NoPermission"));
            return true;
        }
        if (args.length == 0) return false;
        if (args[0].equalsIgnoreCase("giveinventory")) {
            if (args.length >= 3) {
                Player player = Bukkit.getPlayerExact(args[2]);
                if (player == null) {
                    sender.sendMessage(ChatColor.RED + "Player not found.");
                    return true;
                }
                player.getInventory().addItem(TGVNKeepInventoryRune.getInventoryRune().getRune(Integer.parseInt(args[1])));
                sender.sendMessage(ChatColor.GREEN + "Added " + args[1] + " rune to " + args[2] + "'s inventory");
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage("Because console can't receive item, the give command should be in format /kir give <amount> <player name>. Please try again.");
                return true;
            }
            if (args.length == 2) {
                try {
                    ((Player) sender).getInventory().addItem(TGVNKeepInventoryRune.getInventoryRune().getRune(Integer.parseInt(args[1])));
                } catch (NumberFormatException exception) {
                    sender.sendMessage(String.format("%s is not a number.", args[1]));
                }
            } else ((Player) sender).getInventory().addItem(TGVNKeepInventoryRune.getInventoryRune().getRune(1));
            sender.sendMessage("You received rune.");
            return true;
        }
        if (args[0].equalsIgnoreCase("giveitem")) {
            if (args.length >= 3) {
                Player player = Bukkit.getPlayerExact(args[2]);
                if (player == null) {
                    sender.sendMessage(ChatColor.RED + "Player not found.");
                    return true;
                }
                player.getInventory().addItem(TGVNKeepInventoryRune.getItemRune().getRune(Integer.parseInt(args[1])));
                sender.sendMessage(ChatColor.GREEN + "Added " + args[1] + " rune to " + args[2] + "'s inventory");
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage("Because console can't receive item, the give command should be in format /kir give <amount> <player name>. Please try again.");
                return true;
            }
            if (args.length == 2) {
                try {
                    ((Player) sender).getInventory().addItem(TGVNKeepInventoryRune.getItemRune().getRune(Integer.parseInt(args[1])));
                } catch (NumberFormatException exception) {
                    sender.sendMessage(String.format("%s is not a number.", args[1]));
                }
            } else ((Player) sender).getInventory().addItem(TGVNKeepInventoryRune.getItemRune().getRune(1));
            sender.sendMessage("You received rune.");
            return true;
        }
        if (args[0].equalsIgnoreCase("removerune")) {
            Player player;
            if (args.length == 1) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(label + " removerune <player>");
                    return true;
                }
                player = (Player) sender;
            } else {
                Player p = Bukkit.getPlayerExact(args[1]);
                if (p == null) {
                    sender.sendMessage(ChatColor.RED + "Player not found.");
                    return true;
                }
                player = p;
            }
            ItemStack item = player.getInventory().getItemInMainHand();
            item = TGVNKeepInventoryRune.getItemRune().removeLore(item);
            player.getInventory().setItemInMainHand(item);
        }
        if (args[0].equalsIgnoreCase("reload")) {
            ConfigManager.init();
            sender.sendMessage(ChatColor.GREEN + "Configuration reloaded.");
            return true;
        }
        return false;
    }
}
