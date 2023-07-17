package cn.XueSong.Client.mod.Render;

import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;

public class fullbright extends Mod {
    private float originalGamma;

    public fullbright() {
        super("Fullbright", "视角高亮", true);
    }

    @Override
    public void onEnable() {
        Minecraft mc = Minecraft.getMinecraft();
        originalGamma = mc.gameSettings.saturation; // 保存原始的亮度设置值
        mc.gameSettings.saturation = 1000f; // 设置游戏的亮度为最大值
    }

    @Override
    public void onDisable() {
        Minecraft mc = Minecraft.getMinecraft();
        mc.gameSettings.saturation = originalGamma; // 恢复原始的亮度设置值
    }
}
