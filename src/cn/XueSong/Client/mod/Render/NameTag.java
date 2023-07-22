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
import java.util.ArrayList;
import java.util.Comparator;

public class NameTag extends Mod {
    Minecraft mc = Minecraft.getMinecraft();
    public NameTag() {
        super("NameTag", "���Ʊ�ǩ", true, Category.Render,"��ʾ��ҵ�Name��������,ʵ��͸��Ч��");
        this.setKey(Keyboard.KEY_V);
    }
    String name= "";
    @Override
    public void renderWorld(float partialTicks) {
        AntiBotUtil antiBotUtil = new AntiBotUtil();

        // ��ʵ���б�תΪ ArrayList���Ա��������
        ArrayList<Entity> entities = new ArrayList<>(Minecraft.getMinecraft().theWorld.loadedEntityList);

        // ����ʵ������ҵľ���������򣬾����Զ��ʵ�����ǰ��
        entities.sort(Comparator.comparingDouble(e -> -e.getDistanceToEntity(mc.thePlayer)));

        for (Entity entity : entities) {
            if(entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;
                if (!antiBotUtil.isPlayerBot(player, mc) && !player.equals(mc.thePlayer)) {
                    name = player.getDisplayName().getFormattedText();
                    RenderUtil.renderLabelPlayer(player, name, 400, 0,0.1f,new Color(0,0,0,255),false,partialTicks);
                }
            }
        }
    }







}
