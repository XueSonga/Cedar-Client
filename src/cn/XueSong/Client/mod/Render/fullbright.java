package cn.XueSong.Client.mod.Render;

import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;

public class fullbright extends Mod {
    private float originalGamma;

    public fullbright() {
        super("Fullbright", "�ӽǸ���", true);
    }

    @Override
    public void onEnable() {
        Minecraft mc = Minecraft.getMinecraft();
        originalGamma = mc.gameSettings.saturation; // ����ԭʼ����������ֵ
        mc.gameSettings.saturation = 1000f; // ������Ϸ������Ϊ���ֵ
    }

    @Override
    public void onDisable() {
        Minecraft mc = Minecraft.getMinecraft();
        mc.gameSettings.saturation = originalGamma; // �ָ�ԭʼ����������ֵ
    }
}
