package cn.XueSong.Client.mod.Combo;

import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class AimAssist extends Mod {
    // Constructor
    public AimAssist() {
        super("AimAssist", "×Ô¶¯Ãé×¼", true);
        AimAssistThread.setAimAssistEnabled(true);
        AimAssistThread AIm = new AimAssistThread(Minecraft.getMinecraft());
        Thread AimAssistThread = new Thread(AIm);
        AimAssistThread.start();
    }

    // We will not use the render function in this mod.
}