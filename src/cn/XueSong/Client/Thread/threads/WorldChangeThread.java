package cn.XueSong.Client.Thread.threads;

import cn.XueSong.Client.Client;
import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class WorldChangeThread implements Runnable {
    private final Minecraft mc;
    private World lastWorld;

    public WorldChangeThread(Minecraft mc) {
        this.mc = mc;
        this.lastWorld = mc.theWorld;
    }

    @Override
    public void run() {
        while (true) {
            if (mc.theWorld != lastWorld) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                onWorldChange();
                lastWorld = mc.theWorld;
            }
            try {
                Thread.sleep(500);  // sleep for a short period to prevent high CPU usage
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void onWorldChange() {
        System.out.println("WorldChanged");
        Client.modManager.getEnabledMods().forEach(Mod::onWorldChange);
    }
}

