package vn.vinhgaming.tgvnkeepinventoryrune;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static String translate(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> translate(List<String> ls) {
        List<String> nls = new ArrayList<>();
        for (String s : ls)
            nls.add(translate(s));
        return nls;
    }
}
