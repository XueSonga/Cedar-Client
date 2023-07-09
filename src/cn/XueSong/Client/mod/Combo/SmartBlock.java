package cn.XueSong.Client.mod.Combo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;

import java.util.Random;

import static cn.XueSong.Client.util.InstanceAccess.InstanceAccess.mc;

public class SmartBlock extends Thread {
    @Override
    public void run() {
            long seed = System.currentTimeMillis();            // 生成30到80之间的随机整数
            Random random = new Random(seed);
            int randomNumber = random.nextInt(60) + 20;
            try {
                KeyBinding.onTick(mc.gameSettings.keyBindPickBlock.getKeyCode());
                Thread.sleep(randomNumber);
                KeyBinding.onTick(mc.gameSettings.keyBindDrop.getKeyCode());
                Thread.sleep(randomNumber);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }
}
