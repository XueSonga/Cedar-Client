package cn.XueSong.Client.mod.Render;

import cn.XueSong.Client.Client;
import cn.XueSong.Client.font.CFontRenderer;
import cn.XueSong.Client.mod.Mod;
import cn.XueSong.Client.util.shader.CShaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

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
    private final int rainbow_step = 1;

    public double round = 5;

    public Hud() {
        super("HUD","原生中文测试Test",true);
    }

    @Override
    public void render() {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        int width = scaledResolution.getScaledWidth();
        int height = scaledResolution.getScaledHeight();

        //Logo
        int y_logo = 6;
        int x_logo = 6;
        if (ClientLogo){
            font_C.drawStringWithShadow(Client.VERSION,width-font_C.getStringWidth(Client.VERSION) - x_logo, y_logo + font_B.getStringHeight(Client.NAEM) - font_C.getStringHeight(Client.VERSION) - 1, new Color(255, 197, 0, 255).getRGB());
            font_B.drawStringWithShadow(Client.NAEM, width-font_B.getStringWidth(Client.NAEM) - font_C.getStringWidth(Client.VERSION) - x_logo - 1, y_logo, new Color(107, 228, 255, 255).getRGB());
        }


        //ModList
        int y_modlist = 5;
        int x_modlist = 10  ;
        boolean ranbow_modlist_statusB = true;//颜色变换状态
        if (Modlist){
            if (ClientLogo){
                y_modlist=y_modlist+font_B.getStringHeight(Client.NAEM) + 2;
            }
            List<Mod> enableMods = Client.modManager.getEnableMods();
            enableMods.sort((o1, o2) -> font_A.getStringWidth(o2.getName()+o2.getType()) - font_A.getStringWidth(o1.getName()+o1.getType()));
            for (Mod enableMod : enableMods) {

                String Modtype = enableMod.getType();

                int ModList_x = 0;
                int ModList_long = font_A.getStringWidth(enableMod.getName()) + font_A.getStringWidth(Modtype);

                if (Objects.equals(enableMod.getType(), "")){
                    ModList_x = width- font_A.getStringWidth(enableMod.getName()) - font_A.getStringWidth(Modtype) - x_modlist;
                }else {
                    ModList_x = width- font_A.getStringWidth(enableMod.getName()) - font_A.getStringWidth(Modtype) - x_modlist - 2;
                }
                //绘制模组列表的底部长方形
                Gui.drawRect(ModList_x-2,y_modlist + font_A.getStringHeight("A")+3,width - x_modlist + 2,y_modlist-1,new Color(162, 162, 162, 148).getRGB());

                //绘制模组列表旁边的彩条
                Gui.drawRect(ModList_x + ModList_long + 5,y_modlist + font_A.getStringHeight("A")+3,ModList_x + ModList_long + 4,y_modlist-1,new Color(0, 255, 255, 255).getRGB());


                //绘制模组内容
                CFontRenderer.DisplayFontWithShadow(enableMod.getName(),ModList_x,y_modlist,Color.WHITE.getRGB());
                CFontRenderer.DisplayFontWithShadow(enableMod.getType(),width- font_A.getStringWidth(Modtype) - x_modlist, y_modlist ,new Color(0, 255, 208, 255).getRGB());
                y_modlist = y_modlist + 11;
            }
        }

        //shouFps
        String text = "FPS ";
        String FPS = Integer.toString(Minecraft.getDebugFPS());
        double y_showFps = 6;
        double x_showFps = 6;
        double width_showFps = font_A.getStringWidth(text + FPS);
        if(ShowFps){
            CShaders.COGQ_SHADER.draw(x_showFps-5,y_showFps-5,width_showFps+10,(double) font_A.getStringHeight("A")+10,round,0.5,new Color(124, 124, 124, 171),new Color(124, 124, 124, 171));
            font_A.drawStringWithShadow(text, x_showFps, y_showFps, Color.WHITE.getRGB());
            font_A.drawStringWithShadow(FPS,font_A.getStringWidth(text) + x_showFps, y_showFps ,new Color(190, 190, 190).getRGB());
        };
    }
}
