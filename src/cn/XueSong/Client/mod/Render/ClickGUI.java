package cn.XueSong.Client.mod.Render;

import cn.XueSong.Client.Client;
import cn.XueSong.Client.gui.clickgui.ClickGui;
import cn.XueSong.Client.mod.Category;
import cn.XueSong.Client.mod.Mod;
import cn.XueSong.Client.util.render.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class ClickGUI extends Mod {
    public ClickGUI() {
        super("ClickGui", "Ä£¿é½çÃæ",false, Category.Render);
        setKey(Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onEnable() {
        Minecraft.getMinecraft().displayGuiScreen(Client.clickGui);
        setEnabled(false);
    }
}
