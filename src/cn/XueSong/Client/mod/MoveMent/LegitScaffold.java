package cn.XueSong.Client.mod.MoveMent;

import cn.XueSong.Client.Thread.threads.ScaffoldThread;
import cn.XueSong.Client.mod.Category;
import cn.XueSong.Client.mod.Mod;
import cn.XueSong.Client.util.chat.ChatUtil;
import cn.XueSong.Client.util.player.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import java.security.Key;

public class LegitScaffold extends Mod {
    public LegitScaffold() {
        super("Scaffold", "Legit", false, Category.Movement, "合法的辅助搭路");
    }

    Minecraft mc = Minecraft.getMinecraft();
    ScaffoldThread scaffoldThread = new ScaffoldThread();


    @Override
    public void onDisable() {
        scaffoldThread.setScaffold(false);
    }

    @Override
    public void onEnable() {
        scaffoldThread.setScaffold(true);
        Thread ScaffoldThread = new Thread(scaffoldThread);
        ScaffoldThread.start();
    }
}
