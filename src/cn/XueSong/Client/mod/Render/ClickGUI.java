package cn.XueSong.Client.mod.Render;

import cn.XueSong.Client.Client;
import cn.XueSong.Client.mod.Category;
import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class ClickGUI extends Mod {
    public ClickGUI() {
        super("ClickGui", "Ä£¿é½çÃæ",false, Category.Render, "ClickGui");
        setKey(Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onEnable() {
        Minecraft.getMinecraft().displayGuiScreen(Client.clickGui);
        setEnabled(false);
    }
}
