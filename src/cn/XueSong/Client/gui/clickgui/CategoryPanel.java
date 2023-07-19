package cn.XueSong.Client.gui.clickgui;

import cn.XueSong.Client.Client;
import cn.XueSong.Client.font.CFontRenderer;
import cn.XueSong.Client.mod.Category;
import cn.XueSong.Client.mod.Mod;
import cn.XueSong.Client.mod.Other.Nick;
import cn.XueSong.Client.util.render.RenderUtil;
import cn.XueSong.Client.util.shader.CShaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CategoryPanel{
    private final int width = 100;
    private final int height = 20;
    private int x;
    private int y;
    private Category category;

    private boolean press;
    private int prevX;
    private int prevY;

    private boolean hovered;
    private boolean displayMod = false;

    private final List<ModPanel> modPanels = new ArrayList<>();

    private static final CFontRenderer font_A = new CFontRenderer("Nunito", 18.0F, Font.PLAIN, true, true);//普通
    private static final CFontRenderer icon_A = new CFontRenderer("Tenacityicon", 18.0F, Font.PLAIN, true, true);//图标

    public CategoryPanel(int x, int y, Category category){
        this.x = x;
        this.y = y;
        this.category = category;
        int index = 0;
        for (Mod mod : Client.modManager.getByCategory(category)){
            modPanels.add(new ModPanel(0, height + index * height, mod));  // 使用相对偏移量
            index++;
        }
    }

    public static String replacetoIcon(String str) {
        str = str.replace("Render", "d");
        str = str.replace("Combo", "c");
        str = str.replace("Movement", "f");
        str = str.replace("MiniGames", "b");
        str = str.replace("Other", "a");
        return str;
    }
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Color backgroundColor = new Color(26, 25, 26,255);
        hovered = mouseX >= x && mouseY >= y && mouseX < x + this.width && mouseY < y + this.height;
        if (displayMod) {
            int modsheight = modPanels.size()*height;
            RenderUtil.dropShadow(15, x, y, width, height+modsheight+5, 25, 5);
            RenderUtil.drawRoundedGradientRect(x,y, width, height+modsheight+5, 3, backgroundColor,backgroundColor,true);
            RenderUtil.drawRect(x,y+height-1,width,1,new Color(94, 94, 94, 255).getRGB());
            String icon = replacetoIcon(category.name());
            icon_A.drawString(icon,x + 5,y + ((float) height / 2 - (float) icon_A.getStringHeight("A") / 2)+1,new Color(205, 205, 205,255).getRGB());
            CFontRenderer.DisplayFont(category.name(),x + ( width / 2 - font_A.getStringWidth(category.name()) / 2),y + (height / 2 - font_A.getStringHeight("A") / 2),new Color(205, 205, 205,255).getRGB());
            for (ModPanel it : modPanels) {
                it.drawScreen(mouseX, mouseY, partialTicks, x + it.x, y + it.y);  // 添加 x 和 y
            }
        }else{
            RenderUtil.dropShadow(15, x, y, width, height, 25, 5);
            CShaders.CQ_SHADER.draw(x,y, width, height, 3, backgroundColor);
            String icon = replacetoIcon(category.name());
            icon_A.drawString(icon,x + 5,y + ((float) height / 2 - (float) icon_A.getStringHeight("A") / 2)+1,new Color(205, 205, 205,255).getRGB());
            CFontRenderer.DisplayFont(category.name(),x + ( width / 2 - font_A.getStringWidth(category.name()) / 2),y + (height / 2 - font_A.getStringHeight("A") / 2),new Color(205, 205, 205,255).getRGB());
        }
        if (press) {
            x += mouseX - prevX;
            y += mouseY - prevY;
        }
        prevX = mouseX;
        prevY = mouseY;
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton==0){
            press = mouseX >= x && mouseY >= y && mouseX < x + this.width && mouseY < y + this.height;
            prevX = mouseX;
            prevY = mouseY;
        }
        if (displayMod) {
            for (ModPanel it : modPanels) {
                it.mouseClicked(mouseX, mouseY, mouseButton, x + it.x, y + it.y);  // 添加 x 和 y
            }
        }
    }

    protected void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton==0){
            press = false;
        }else if (mouseButton == 1 && hovered) {
            displayMod = !displayMod;
        }
        if (displayMod) {
            for (ModPanel it : modPanels) {
                it.mouseReleased(mouseX, mouseY, mouseButton, x + it.x, y + it.y);  // 添加 x 和 y
            }
        }
    }


}
