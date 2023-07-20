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

public class ShowFPS extends Mod {

    public double round = 5;
    private static final CFontRenderer font_A = new CFontRenderer("Nunito", 18.0F, Font.PLAIN, true, true);//普通
    public ShowFPS() {
        super("ShowFPS", "帧数显示", true, Category.Render, "显示实时游戏帧数");
    }

    @Override
    public void render(float partialTicks) {
        renderShowFps();
    }

    public void renderShowFps() {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        int width = scaledResolution.getScaledWidth();
        int height = scaledResolution.getScaledHeight();
        String text = "FPS ";
        String FPS = Integer.toString(Minecraft.getDebugFPS());
        double y_showFps = 10;
        double x_showFps = 10;
        double y_showFps_backdrop = y_showFps - 5;
        double x_showFps_backdrop = x_showFps - 5;
        double width_showFps = font_A.getStringWidth(text + FPS) + 10;
        double height_shouwFps = (double) font_A.getStringHeight("A") + 8;
        final double progress_showfps = 1;
        final Color bloomColor = ColorUtil.withAlpha(Color.BLACK, (int) (progress_showfps * 150));
            RenderUtil.dropShadow(10, x_showFps_backdrop, y_showFps_backdrop, width_showFps, height_shouwFps, 40, round + 5);
            CShaders.CQ_SHADER.draw(x_showFps_backdrop,y_showFps_backdrop, width_showFps, height_shouwFps, round, new Color(10, 10, 10, 170));
            font_A.drawString(text, x_showFps, y_showFps, Color.WHITE.getRGB());
            font_A.drawString(FPS, font_A.getStringWidth(text) + x_showFps, y_showFps, new Color(190, 190, 190).getRGB());

    }
}
