package cn.XueSong.Client.mod.Combo;

import net.minecraft.client.settings.KeyBinding;

import java.util.Random;

import static cn.XueSong.Client.util.InstanceAccess.InstanceAccess.mc;

public class SmartBlock extends Thread {
    @Override
    public void run() {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        // ����50��200֮����������
        int randomNumber = random.nextInt(50) + 30;
        try {
            Thread.sleep(randomNumber);
            KeyBinding.onTick(mc.gameSettings.keyBindDrop.getKeyCode());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
