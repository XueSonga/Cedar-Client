package cn.XueSong.Client.mod.Combo;

import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;

import java.util.HashSet;
import java.util.Set;

public class autoclick extends Mod {
    private static final Set<String> VALID_SWORD_NAMES = new HashSet<>();

    static {
        VALID_SWORD_NAMES.add("minecraft:wooden_sword");
        VALID_SWORD_NAMES.add("minecraft:stone_sword");
        VALID_SWORD_NAMES.add("minecraft:iron_sword");
        VALID_SWORD_NAMES.add("minecraft:diamond_sword");
        VALID_SWORD_NAMES.add("minecraft:golden_sword");
    }

    public autoclick() {
        super("AutoClick", "�Զ�����Smart", true);
    }

    @Override
    public void onUpdate() {
        if (Minecraft.getMinecraft().gameSettings.keyBindPickBlock.isKeyDown()) {
            ItemStack heldItem = Minecraft.getMinecraft().thePlayer.getHeldItem();
            if (heldItem == null || heldItem.getItem() == null) {
                return;
            }
            Item heldItemType = heldItem.getItem();
            String heldItemName = Item.itemRegistry.getNameForObject(heldItemType).toString();
            if (VALID_SWORD_NAMES.contains(heldItemName)) {
                if (isLookingAtEntity()) {
                    // �����������ʵ��
                    SmartBlock thread = new SmartBlock();
                    thread.start();
                }
            }
        }
    }

    /**
     * �������Ƿ���������ʵ��
     *
     * @return ��������������ʵ���򷵻�true�����򷵻�false
     */
    public boolean isLookingAtEntity() {
        Minecraft mc = Minecraft.getMinecraft();
        MovingObjectPosition target = mc.objectMouseOver;

        if (target != null && target.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            Entity entity = target.entityHit;
            if (entity != null && entity.isEntityAlive()) {
                // �����������ʵ��
                return true;
            }
        }
        // ���û������ʵ��
        return false;
    }
}
