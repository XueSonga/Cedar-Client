package cn.XueSong.Client.mod.Render;

import cn.XueSong.Client.Client;
import cn.XueSong.Client.mod.Mod;
import cn.XueSong.Client.mod.ModManager;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class fullbright extends Mod {
    private float originalGamma;
    Boolean fullbrightBoolean = false;

    public fullbright() {
        super("Fullbright", "�ӽǸ���", true);
    }

    @Override
    public void onWorldChange() {
        System.out.println("WorldChanged Fullbright");
            Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer != null) {
            mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, Integer.MAX_VALUE, 1));
        }
            originalGamma = mc.gameSettings.saturation; // ����ԭʼ����������ֵ
            mc.gameSettings.saturation = 100F; // ������Ϸ������Ϊ���ֵ

    }

    @Override
    public void onEnable() {
        fullbrightBoolean=true;
        Minecraft mc = Minecraft.getMinecraft();
        mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, Integer.MAX_VALUE, 1));
        originalGamma = mc.gameSettings.saturation; // ����ԭʼ����������ֵ
        mc.gameSettings.saturation = 100F; // ������Ϸ������Ϊ���ֵ
    }

    @Override
    public void onDisable() {
        fullbrightBoolean=false;
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer.isPotionActive(Potion.nightVision)) {
            mc.thePlayer.removePotionEffect(Potion.nightVision.id);
        }
        mc.gameSettings.saturation = originalGamma; // �ָ�ԭʼ����������ֵ
    }
}
