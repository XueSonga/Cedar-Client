package cn.XueSong.Client.mod.MiniGames;

import cn.XueSong.Client.mod.Category;
import cn.XueSong.Client.mod.Mod;
import cn.XueSong.Client.util.render.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.*;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class AnimalSearcher extends Mod {
    private Set<Entity> markedEntities = new HashSet<>();

    public AnimalSearcher() {
        super("AnimalSearcher", "�������", false, Category.MiniGames, "���Ҷ����èè�Ķ���");
    }

    @Override
    public void renderWorld(float partialTicks) {
        for (Entity entity: Minecraft.getMinecraft().theWorld.loadedEntityList){
            if (!(entity instanceof EntityPig || entity instanceof EntityCow || entity instanceof EntityOcelot ||
                    entity instanceof EntityChicken || entity instanceof EntityHorse || entity instanceof EntityWolf)) {
                continue;
            }

            if (Math.abs(entity.rotationPitch) > 15.0f) {  // Assume direct forward view as rotationPitch within ��15.0, adjust as necessary
                markedEntities.add(entity);
            }

            if (markedEntities.contains(entity)) {
                RenderUtil.renderLabelEntity(entity, "�ҵ�����! "+entity.getName(), 300, new Color(255, 0, 0,255),partialTicks);
            }
        }
    }

    @Override
    public void onWorldChange() {
        markedEntities.clear();
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {

    }
}
