package vn.vinhgaming.tgvnkeepinventoryrune;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static List<String> translate(List<String> stringList) {
        List<String> newStringList = new ArrayList<>();
        for (String string : stringList)
            newStringList.add(translate(string));
        return newStringList;
    }
}
