package cn.XueSong.Client.mod.Combo;

import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;

public class autoblock extends Mod {
    public autoblock() {
        super("AutoBlock", "�Զ�����Smart", true);
    }

    boolean isUsing = false;
    ItemStack woodenSword = new ItemStack(Item.getByNameOrId("minecraft:wooden_sword"));

    @Override
    public void onUpdate() {
        if (Minecraft.getMinecraft().gameSettings.keyBindPickBlock.isKeyDown() && !isUsing) {
            ItemStack heldItem = Minecraft.getMinecraft().thePlayer.getHeldItem();
            if (heldItem == null || heldItem.getItem() == null) {
                // ����ֳ�Ϊ��
                return;
            }
            Item heldItemType = heldItem.getItem();
            String heldItemName = Item.itemRegistry.getNameForObject(heldItemType).toString();
            if (heldItemName.equals("minecraft:wooden_sword")
                    || heldItemName.equals("minecraft:stone_sword")
                    || heldItemName.equals("minecraft:iron_sword")
                    || heldItemName.equals("minecraft:diamond_sword")
                    || heldItemName.equals("minecraft:golden_sword")) {
                if (isLookingAtEntity()) {
                    // �����������ʵ��
                    SmartBlock thread = new SmartBlock();
                    thread.start();
                    isUsing = true;
                }
            }
        } else {
            isUsing = false;
        }
    }
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