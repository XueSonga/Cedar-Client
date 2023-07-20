package cn.XueSong.Client.util.bot;

import cn.XueSong.Client.mod.Other.AntiBot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;

import java.util.HashSet;

public class AntiBotUtil {
    private HashSet<String> verifiedPlayers = new HashSet<>();

    public Boolean isAntiBot;

    public boolean isPlayerBot(EntityPlayer player, Minecraft mc){
        if (verifiedPlayers.contains(player.getName())) {
            // If we have already verified this player, return false directly
            return false;
        } else {
            NetworkPlayerInfo playerInfo = mc.getNetHandler().getPlayerInfo(player.getUniqueID());
            boolean isBot = (playerInfo == null);
            if (!isBot) {
                verifiedPlayers.add(player.getName());
            }
            return isBot;
        }
    }
}

