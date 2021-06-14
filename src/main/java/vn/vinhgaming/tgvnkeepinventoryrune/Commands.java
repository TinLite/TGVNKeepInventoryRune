package vn.vinhgaming.tgvnkeepinventoryrune;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("kir.admin")) {
            sender.sendMessage(ConfigManager.getMessage("NoPermission"));
            return true;
        }
        if (args.length == 0) return false;
        if (args[0].equalsIgnoreCase("give")) {
            if (args.length >= 3) {
                Player player = Bukkit.getPlayerExact(args[2]);
                if (player == null) {
                    sender.sendMessage(ChatColor.RED + "Player not found.");
                    return true;
                }
                player.getInventory().addItem(Rune.getRune(Integer.parseInt(args[1])));
                sender.sendMessage(ChatColor.GREEN + "Added " + args[1] + " rune to " + args[2] + "'s inventory");
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage("Because console can't receive item, the give command should be in format /kir give <amount> <player name>. Please try again.");
                return true;
            }
            if (args.length == 2)
                ((Player) sender).getInventory().addItem(Rune.getRune(Integer.parseInt(args[1])));
            else ((Player) sender).getInventory().addItem(Rune.getRune(1));
            sender.sendMessage("You received rune.");
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            ConfigManager.init();
            sender.sendMessage(ChatColor.GREEN + "Configuration reloaded.");
            return true;
        }
        return false;
    }
}
