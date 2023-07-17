package cn.XueSong.Client.serverdetection;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class PlayerChecker {
    public static boolean isPlayerOnline() {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayerSP player = minecraft.thePlayer;
        System.out.println(player != null);
        return player != null;
    }

    public static boolean isSinglePlayerWorld() {
        Minecraft minecraft = Minecraft.getMinecraft();
        System.out.println(minecraft.isSingleplayer());
        return minecraft.isSingleplayer();
    }
}
