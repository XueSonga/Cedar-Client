package cn.XueSong.Client.mod.Render;

import cn.XueSong.Client.font.CFontRenderer;
import cn.XueSong.Client.mod.Category;
import cn.XueSong.Client.mod.Mod;
import cn.XueSong.Client.util.render.ColorUtil;
import cn.XueSong.Client.util.render.RenderUtil;
import cn.XueSong.Client.util.shader.CShaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;

public class ShowName extends Mod {
    public double round = 5;
    private static final CFontRenderer font_A = new CFontRenderer("Nunito", 18.0F, Font.PLAIN, true, true);//普通
    public ShowName() {
        super("ShowName", "名称显示", true, Category.Render, "显示自己名字在屏幕上");
    }

    @Override
    public void render(float partialTicks) {
        renderOwnName();
    }

    public void renderOwnName() {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        String text = "Name: ";
        String Name = Minecraft.getMinecraft().thePlayer.getName();
        double y_NAME = 30;
        double x_NAME = 10;
        double y_NAME_backdrop = y_NAME - 5;
        double x_NAME_backdrop = x_NAME - 5;
        double width_showFps = font_A.getStringWidth(text + Name) + 10;
        double height_shouwFps = (double) font_A.getStringHeight("A") + 8;
        final double progress_showfps = 1;
        final Color bloomColor = ColorUtil.withAlpha(Color.BLACK, (int) (progress_showfps * 150));
        RenderUtil.dropShadow(10, x_NAME_backdrop, y_NAME_backdrop, width_showFps, height_shouwFps, 40, round + 5);
        CShaders.CQ_SHADER.draw(x_NAME_backdrop,y_NAME_backdrop, width_showFps, height_shouwFps, round, new Color(10, 10, 10, 170));
        font_A.drawString(text, x_NAME, y_NAME, Color.WHITE.getRGB());
        CFontRenderer.DisplayFont(Name, (float) (font_A.getStringWidth(text) + x_NAME), (float) y_NAME, new Color(190, 190, 190).getRGB());
    }
}
