package cn.XueSong.Client.mod.MoveMent;

import cn.XueSong.Client.mod.Category;
import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

import static cn.XueSong.Client.util.InstanceAccess.InstanceAccess.mc;

public class Sprint extends Mod {

    public Sprint() {
        super("Sprint", "�Զ�����", true, Category.Movement, "�Զ�����");
    }

    @Override
    public void subThread() {
        if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown()) {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);
        }
    }
}
