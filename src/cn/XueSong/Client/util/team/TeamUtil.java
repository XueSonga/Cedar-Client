package cn.XueSong.Client.util.team;

import net.minecraft.entity.player.EntityPlayer;

public class TeamUtil {
    public static boolean isSameTeam(EntityPlayer player1, EntityPlayer player2) {
        String name1 = player1.getDisplayName().getFormattedText();
        String name2 = player2.getDisplayName().getFormattedText();

        // Check if names have enough length and start with a color code
        if (name1.length() < 2 || name2.length() < 2 || name1.charAt(0) != '¡ì' || name2.charAt(0) != '¡ì') {
            return false;
        }

        // Check if the color codes are the same
        return name1.charAt(1) == name2.charAt(1);
    }
}

