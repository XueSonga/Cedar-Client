package cn.XueSong.Client.mod.MoveMent;

import cn.XueSong.Client.mod.Category;
import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

import static cn.XueSong.Client.util.InstanceAccess.InstanceAccess.mc;

public class Sprint extends Mod {

    public Sprint() {
        super("Sprint", "自动疾跑", true, Category.Movement, "自动疾跑");
    }

    @Override
    public void subThread() {
        if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown()) {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);
        }
    }
}
