package cn.XueSong.Client.mod.Render;

import cn.XueSong.Client.Client;
import cn.XueSong.Client.font.CFontRenderer;
import cn.XueSong.Client.mod.Mod;
import cn.XueSong.Client.util.render.ColorUtil;
import cn.XueSong.Client.util.render.RenderUtil;
import cn.XueSong.Client.util.shader.CShaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;
import java.util.List;
import java.util.Objects;

public class Hud extends Mod {

    public static Boolean ClientLogo = true;
    public static Boolean Modlist = true;
    public static Boolean ShowFps = true;

    private static final CFontRenderer font_A = new CFontRenderer("Roboto-Medium", 18.0F, Font.PLAIN, true, true);//普通
    private static final CFontRenderer font_B = new CFontRenderer("SFBOLD", 30.0F, Font.PLAIN, true, true);//标题
    private static final CFontRenderer font_C = new CFontRenderer("SFBOLD", 17.0F, Font.PLAIN, true, true);//版本

    public double round = 5;

    public Hud() {
        super("HUD", "界面", true);
    }
    @Override
    public void render(float partialTicks) {
        GlStateManager.pushMatrix();
        renderLogo();
        renderModList();
        renderShowFps();
        renderOwnName();
        GlStateManager.popMatrix();
    }
    public void renderLogo() {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        int width = scaledResolution.getScaledWidth();
        int height = scaledResolution.getScaledHeight();

        int y_logo = 6;
        int x_logo = 8;
        if (ClientLogo) {
            font_C.drawStringWithShadow(Client.VERSION, width - font_C.getStringWidth(Client.VERSION) - x_logo, y_logo + font_B.getStringHeight(Client.NAEM) - font_C.getStringHeight(Client.VERSION) - 1, new Color(255, 197, 0, 255).getRGB());
            font_B.drawStringWithShadow(Client.NAEM, width - font_B.getStringWidth(Client.NAEM) - font_C.getStringWidth(Client.VERSION) - x_logo - 1, y_logo, new Color(107, 228, 255, 255).getRGB());
        }
    }

    public void renderModList() {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        int width = scaledResolution.getScaledWidth();
        int height = scaledResolution.getScaledHeight();
        double y_modlist = 5;
        double x_modlist = 10;
        double x_modlist_dropdow = x_modlist;
        double y_modlist_dropdow = y_modlist;
        if (Modlist) {
            if (ClientLogo) {
                y_modlist_dropdow = y_modlist_dropdow + font_B.getStringHeight(Client.NAEM) + 2;
                y_modlist = y_modlist + font_B.getStringHeight(Client.NAEM) + 2;
            }
            List<Mod> enableMods = Client.modManager.getEnabledMods();
            enableMods.sort((o1, o2) -> font_A.getStringWidth(o2.getName() + o2.getType()) - font_A.getStringWidth(o1.getName() + o1.getType()));
            for (Mod enableMod : enableMods) {
                String Modtype = enableMod.getType();

                double ModList_x = 0;
                double ModList_long = font_A.getStringWidth(enableMod.getName()) + font_A.getStringWidth(Modtype);

                if (Objects.equals(enableMod.getType(), "")) {
                    ModList_x = width - font_A.getStringWidth(enableMod.getName()) - font_A.getStringWidth(Modtype) - x_modlist;
                } else {
                    ModList_x = width - font_A.getStringWidth(enableMod.getName()) - font_A.getStringWidth(Modtype) - x_modlist - 2;
                }

                Gui.drawRect((int) (ModList_x - 2), (int) (y_modlist + font_A.getStringHeight("A") + 3), (int) (width - x_modlist + 2), (int) (y_modlist - 1), new Color(112, 112, 112, 116).getRGB());
                Gui.drawRect((int) (ModList_x + ModList_long + 5), (int) (y_modlist + font_A.getStringHeight("A") + 3), (int) (ModList_x + ModList_long + 4), (int) (y_modlist - 1), new Color(0, 255, 255, 255).getRGB());
                CFontRenderer.DisplayFontWithShadow(enableMod.getName(), (float) ModList_x, (float) y_modlist, new Color(255, 255, 255, 255).getRGB());
                if (!Objects.equals(enableMod.getType(), "") || !Objects.equals(enableMod.getType(), " ")){
                    CFontRenderer.DisplayFontWithShadow(enableMod.getType(), (float) (width - font_A.getStringWidth(Modtype) - x_modlist), (float) y_modlist, new Color(0, 255, 178, 210).getRGB());
                }
                y_modlist = y_modlist + 11;
            }
        }
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
        if (ShowFps) {
            RenderUtil.dropShadow(10, x_showFps_backdrop, y_showFps_backdrop, width_showFps, height_shouwFps, 40, round + 5);
            CShaders.CQ_SHADER.draw(x_showFps_backdrop,y_showFps_backdrop, width_showFps, height_shouwFps, round, new Color(10, 10, 10, 170));
            font_A.drawStringWithShadow(text, x_showFps, y_showFps, Color.WHITE.getRGB());
            font_A.drawStringWithShadow(FPS, font_A.getStringWidth(text) + x_showFps, y_showFps, new Color(190, 190, 190).getRGB());
        }
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
        if (ShowFps) {
            RenderUtil.dropShadow(10, x_NAME_backdrop, y_NAME_backdrop, width_showFps, height_shouwFps, 40, round + 5);
            CShaders.CQ_SHADER.draw(x_NAME_backdrop,y_NAME_backdrop, width_showFps, height_shouwFps, round, new Color(10, 10, 10, 170));
            font_A.drawStringWithShadow(text, x_NAME, y_NAME, Color.WHITE.getRGB());
            CFontRenderer.DisplayFontWithShadow(Name, (float) (font_A.getStringWidth(text) + x_NAME), (float) y_NAME-2, new Color(190, 190, 190).getRGB());
        }
    }
}
