package cn.XueSong.Client.Thread.threads;

import cn.XueSong.Client.Client;
import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;

public class SubThread implements Runnable{
    Minecraft mc;

    @Override
    public void run() {
        mc = Minecraft.getMinecraft();
        while (true){
            try {
                if (mc.theWorld != null && mc.thePlayer != null) {
                    Client.modManager.getEnabledMods().forEach(Mod::subThread);
                }
                Thread.sleep(20);  // sleep for a short period to prevent high CPU usage
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}