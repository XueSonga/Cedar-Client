package cn.XueSong.Client.gui.clickgui;

import cn.XueSong.Client.Client;
import cn.XueSong.Client.font.CFontRenderer;
import cn.XueSong.Client.mod.Mod;
import cn.XueSong.Client.mod.Render.ClickGUI;
import cn.XueSong.Client.util.render.RenderUtil;

import java.awt.*;

public class ModPanel {
    private final Mod mod;
    public int x;
    public int y;

    private final int width = 100;
    private final int height = 20;

    boolean press;

    private boolean hovered;

    public ModPanel(int x, int y, Mod mod) {
        this.x = x;
        this.y = y;
        this.mod = mod;
    }
    private static final CFontRenderer font_A = new CFontRenderer("Roboto-Medium", 18.0F, Font.PLAIN, true, true);//ÆÕÍ¨
    public void drawScreen(int mouseX, int mouseY, float partialTicks, int x, int y) {
        hovered = mouseX >= x && mouseY >= y && mouseX < x + this.width && mouseY < y + this.height;
        if (mod.isEnabled()){
            Color backgroundColorSelect = new Color(229, 229, 229,255);
            Color backgroundColor = new Color(255, 255, 255,255);
            RenderUtil.drawRect(x,y, width, height, hovered ? backgroundColorSelect.getRGB():backgroundColor.getRGB());
            CFontRenderer.DisplayFont(mod.getName(),x + 5,y + (height / 2 - font_A.getStringHeight("A") / 2),new Color(0, 0, 0,255).getRGB());
            if (hovered){
                RenderUtil.drawHoveringText(mod.getDescription(),x+width+8,mouseY-8);
            }
        }else {
            Color backgroundColorSelect = new Color(31, 30, 31,255);
            Color backgroundColor = new Color(26, 25, 26,255);
            RenderUtil.drawRect(x,y, width, height, hovered ? backgroundColorSelect.getRGB():backgroundColor.getRGB());
            CFontRenderer.DisplayFont(mod.getName(),x + 5,y + (height / 2 - font_A.getStringHeight("A") / 2),new Color(255,255,255,255).getRGB());
            if (hovered){
                RenderUtil.drawHoveringText(mod.getDescription(),x+width+8,mouseY+-8);
            }
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton, int x, int y) {
        press = mouseX >= x && mouseY >= y && mouseX < x + this.width && mouseY < y + this.height;
    }


    protected void mouseReleased(int mouseX, int mouseY, int mouseButton, int x, int y) {
        if (mouseButton == 0 && hovered) {
            if (!mod.equals(Client.modManager.getByClass(ClickGUI.class))) {
                mod.setEnabled(!mod.isEnabled());
                if (mod.isEnabled()){
                    mod.onEnable();
                }else {
                    mod.onDisable();
                }
            }
        }
    }

    public boolean isPress() {
        return press;
    }



    /**
     * Draws the text when mouse is over creative inventory tab. Params: current creative tab to be checked, current
     * mouse x position, current mouse y position.
     */

}
