package cn.XueSong.Client.mod.Combo;

import cn.XueSong.Client.Thread.threads.AimAssistThread;
import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class AimAssist extends Mod {
    // Constructor
    public AimAssist() {
        super("AimAssist", "�Զ���׼[F]", true);
        AimAssistThread.setAimAssistEnabled(true);
        setKey(Keyboard.KEY_F);
    }

    @Override
    public void onEnable() {
        AimAssistThread.setAimAssistEnabled(isEnabled());
    }

    @Override
    public void onDisable() {
        AimAssistThread.setAimAssistEnabled(isEnabled());
    }

    // We will not use the render function in this mod.
}