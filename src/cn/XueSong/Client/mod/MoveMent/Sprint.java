package cn.XueSong.Client.mod.MoveMent;

import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

import static cn.XueSong.Client.util.InstanceAccess.InstanceAccess.mc;

public class Sprint extends Mod {

    public Sprint() {
        super("Sprint", "�Զ�����", true);
        setKey(Keyboard.KEY_Z);
    }

    @Override
    public void onUpdate() {
        if (Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown()) {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindInventory.getKeyCode(), true);
        }
    }
}
