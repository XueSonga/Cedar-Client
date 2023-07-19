package cn.XueSong.Client.gui.clickgui;

import cn.XueSong.Client.Client;
import cn.XueSong.Client.font.CFontRenderer;
import cn.XueSong.Client.mod.Category;
import cn.XueSong.Client.util.render.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ClickGui extends GuiScreen {

    public List<CategoryPanel> categoryPanels = new ArrayList<>();

    public ClickGui(){
        int y=20;
        int x=20;
        for (Category value: Category.values()){
            categoryPanels.add(new CategoryPanel(x,y,value));
            x+=110;
        }
    }
    private static final CFontRenderer font_B = new CFontRenderer("tenacity-bold", 20.0F, Font.PLAIN, true, true);//БъЬт
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        int PMwidth = scaledResolution.getScaledWidth();
        int PMheight = scaledResolution.getScaledHeight();
        RenderUtil.drawRect(0,0,PMwidth,PMheight,new Color(0, 0, 0, 103).getRGB());
        font_B.drawString(Client.NAEM+"Client BY CedarToday",5,PMheight-font_B.getStringHeight("A")-5,Color.white.getRGB());
        categoryPanels.forEach(it->it.drawScreen(mouseX,mouseY,partialTicks));
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        categoryPanels.forEach(it->it.mouseClicked(mouseX,mouseY,mouseButton));
        super.mouseClicked(mouseX, mouseY, mouseButton);

    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        categoryPanels.forEach(it->it.mouseReleased(mouseX,mouseY,state));
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
