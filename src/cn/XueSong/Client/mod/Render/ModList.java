package cn.XueSong.Client.mod.Render;

import cn.XueSong.Client.Client;
import cn.XueSong.Client.font.CFontRenderer;
import cn.XueSong.Client.mod.Category;
import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.util.List;
import java.util.Objects;

public class ModList extends Mod {

    public static Boolean ClientLogo = true;
    public static Boolean Modlist = true;

    private static final CFontRenderer font_A = new CFontRenderer("Nunito", 18.0F, Font.PLAIN, true, true);//普通
    private static final CFontRenderer font_B = new CFontRenderer("tenacity-bold", 30.0F, Font.PLAIN, true, true);//标题
    private static final CFontRenderer font_C = new CFontRenderer("tenacity-bold", 17.0F, Font.PLAIN, true, true);//版本

    public double round = 5;

    public ModList() {
        super("ModList", "模组列表", true, Category.Render, "显示模组列表");
    }
    @Override
    public void render(float partialTicks) {
        renderLogo();
        renderModList();
    }
    public void renderLogo() {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        int width = scaledResolution.getScaledWidth();
        int height = scaledResolution.getScaledHeight();

        int y_logo = 6;
        int x_logo = 8;
        if (ClientLogo) {
            font_C.drawString(Client.VERSION, width - font_C.getStringWidth(Client.VERSION) - x_logo, y_logo + font_B.getStringHeight(Client.NAEM) - font_C.getStringHeight(Client.VERSION) - 1, new Color(218, 218, 218, 255).getRGB());
            font_B.drawString(Client.NAEM, width - font_B.getStringWidth(Client.NAEM) - font_C.getStringWidth(Client.VERSION) - x_logo - 1, y_logo, new Color(255, 255, 255, 255).getRGB());
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

                Gui.drawRect((int) (ModList_x - 2), (int) (y_modlist + font_A.getStringHeight("A")+1    ), (int) (width - x_modlist + 2), (int) (y_modlist - 1), new Color(103, 103, 103, 116).getRGB());
                Gui.drawRect((int) (ModList_x + ModList_long + 5), (int) (y_modlist + font_A.getStringHeight("A") + 1), (int) (ModList_x + ModList_long + 4), (int) (y_modlist - 1), new Color(255, 255, 255, 255).getRGB());
                CFontRenderer.DisplayFont(enableMod.getName(), (float) ModList_x, (float) y_modlist+1, new Color(255, 255, 255, 255).getRGB());
                if (!Objects.equals(enableMod.getType(), "") || !Objects.equals(enableMod.getType(), " ")){
                    CFontRenderer.DisplayFontWithShadow(enableMod.getType(), (float) (width - font_A.getStringWidth(Modtype) - x_modlist), (float) y_modlist, new Color(0, 255, 181, 210).getRGB());
                }
                y_modlist = y_modlist + 11;
            }
        }
    }
}
