package cn.XueSong.Client.mod.Combo;

import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AutoClick extends Mod {
    private static final Set<String> VALID_SWORD_NAMES = new HashSet<>();

    static {
        VALID_SWORD_NAMES.add("minecraft:wooden_sword");
        VALID_SWORD_NAMES.add("minecraft:stone_sword");
        VALID_SWORD_NAMES.add("minecraft:iron_sword");
        VALID_SWORD_NAMES.add("minecraft:diamond_sword");
        VALID_SWORD_NAMES.add("minecraft:golden_sword");
    }

    private long lastRenderTime = 0;

    public AutoClick() {
        super("AutoClick", "×Ô¶¯·À¿³Smart", true);
    }

    @Override
    public void render() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastRenderTime;

        if (elapsedTime >= 50) {
            lastRenderTime = currentTime;

            if (Minecraft.getMinecraft().gameSettings.keyBindPickBlock.isKeyDown()) {
                ItemStack heldItem = Minecraft.getMinecraft().thePlayer.getHeldItem();
                if (heldItem == null || heldItem.getItem() == null) {
                    return;
                }
                Item heldItemType = heldItem.getItem();
                String heldItemName = Item.itemRegistry.getNameForObject(heldItemType).toString();
                if (VALID_SWORD_NAMES.contains(heldItemName)) {
                    if (isLookingAtEntity()) {
                        SmartBlock thread = new SmartBlock();
                        thread.start();
                    }
                }
            }
        }
    }

    public boolean isLookingAtEntity() {
        Minecraft mc = Minecraft.getMinecraft();
        MovingObjectPosition target = mc.objectMouseOver;

        if (target != null && target.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            Entity entity = target.entityHit;
            if (entity != null && entity.isEntityAlive()) {
                return true;
            }
        }
        return false;
    }
}

class SmartBlock extends Thread {
    private static final Random random = new Random();

    @Override
    public void run() {
        int randomNumber = random.nextInt(60) + 20;
        try {
            KeyBinding.onTick(Minecraft.getMinecraft().gameSettings.keyBindPickBlock.getKeyCode());
            Thread.sleep(randomNumber);
            KeyBinding.onTick(Minecraft.getMinecraft().gameSettings.keyBindDrop.getKeyCode());
            Thread.sleep(randomNumber);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
