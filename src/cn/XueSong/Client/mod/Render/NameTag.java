package cn.XueSong.Client.mod.Render;

import cn.XueSong.Client.mod.Category;
import cn.XueSong.Client.mod.Mod;
import cn.XueSong.Client.util.bot.AntiBotUtil;
import cn.XueSong.Client.util.render.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class NameTag extends Mod {
    Minecraft mc = Minecraft.getMinecraft();
    public NameTag() {
        super("NameTag", "名称标签", true, Category.Render,"显示玩家的Name在世界上,实现透视效果");
        this.setKey(Keyboard.KEY_V);
    }
    String name= "";
    @Override
    public void renderWorld(float partialTicks) {
        AntiBotUtil antiBotUtil = new AntiBotUtil();
        for (Entity entity: Minecraft.getMinecraft().theWorld.loadedEntityList){
            if(entity instanceof EntityPlayer){
                EntityPlayer player = (EntityPlayer) entity;
                if (!antiBotUtil.isPlayerBot(player, mc) && !player.equals(mc.thePlayer)) {
                    name = player.getDisplayName().getFormattedText();
                    RenderUtil.renderLabelPlayer(player, name, 128, new Color(0,0,0,255),partialTicks);
                }
            }
        }
    }

}
