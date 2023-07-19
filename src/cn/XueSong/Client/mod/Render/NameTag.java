package cn.XueSong.Client.mod.Render;

import cn.XueSong.Client.mod.Category;
import cn.XueSong.Client.mod.Mod;
import cn.XueSong.Client.util.bot.AntiBotUtil;
import cn.XueSong.Client.util.render.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;

public class NameTag extends Mod {
    Minecraft mc = Minecraft.getMinecraft();
    public NameTag() {
        super("NameTag", "Ãû³Æ±êÇ©", true, Category.Render);
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
                    RenderUtil.renderLabel(player, name, 128, partialTicks);
                }
            }
        }
    }

}
