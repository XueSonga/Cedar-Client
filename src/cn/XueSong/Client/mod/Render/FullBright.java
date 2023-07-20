package cn.XueSong.Client.mod.Render;

import cn.XueSong.Client.mod.Category;
import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class FullBright extends Mod {
    private float originalGamma;
    Boolean fullbrightBoolean = false;

    public FullBright() {
        super("Fullbright", "视角高亮", true, Category.Render, "使世界亮度达到一个新的高度");
    }

    @Override
    public void onWorldChange() {
        System.out.println("WorldChanged Fullbright");
            Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer != null) {
            mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, Integer.MAX_VALUE, 1));
        }
            originalGamma = mc.gameSettings.saturation; // 保存原始的亮度设置值
            mc.gameSettings.saturation = 100F; // 设置游戏的亮度为最大值

    }

    @Override
    public void onEnable() {
        fullbrightBoolean=true;
        Minecraft mc = Minecraft.getMinecraft();
        mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, Integer.MAX_VALUE, 1));
        originalGamma = mc.gameSettings.saturation; // 保存原始的亮度设置值
        mc.gameSettings.saturation = 100F; // 设置游戏的亮度为最大值
    }

    @Override
    public void onDisable() {
        fullbrightBoolean=false;
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer.isPotionActive(Potion.nightVision)) {
            mc.thePlayer.removePotionEffect(Potion.nightVision.id);
        }
        mc.gameSettings.saturation = originalGamma; // 恢复原始的亮度设置值
    }
}
