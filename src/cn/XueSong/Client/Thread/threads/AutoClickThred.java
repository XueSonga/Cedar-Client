package cn.XueSong.Client.Thread.threads;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AutoClickThred implements Runnable {
    public static boolean AutoClickisOn = false;
    private long lastRenderTime = 0;

    public static void setAutoClick(boolean isOn) {
        AutoClickisOn = isOn;
    }

    private static final Random random = new Random();
    private static final Set<String> VALID_SWORD_NAMES = new HashSet<>();

    public static Boolean AutoBlock = true;

    static {
        VALID_SWORD_NAMES.add("minecraft:wooden_sword");
        VALID_SWORD_NAMES.add("minecraft:stone_sword");
        VALID_SWORD_NAMES.add("minecraft:iron_sword");
        VALID_SWORD_NAMES.add("minecraft:diamond_sword");
        VALID_SWORD_NAMES.add("minecraft:golden_sword");
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

    @Override
    public void run() {
        while (true) {
                if (AutoClickisOn) {
                    long currentTime = System.currentTimeMillis();
                    long elapsedTime = currentTime - lastRenderTime;
                    if (elapsedTime >= 53) {
                        lastRenderTime = currentTime;
                        if (Minecraft.getMinecraft().gameSettings.keyBindAttack.isKeyDown()) {
                            ItemStack heldItem = Minecraft.getMinecraft().thePlayer.getHeldItem();
                            if (heldItem == null || heldItem.getItem() == null) {
                                continue;
                            }
                            Item heldItemType = heldItem.getItem();
                            String heldItemName = Item.itemRegistry.getNameForObject(heldItemType).toString();
                            if (VALID_SWORD_NAMES.contains(heldItemName)) {
                                if (isLookingAtEntity()) {
                                    int randomNumber = random.nextInt(60) + 20;
                                    if (randomNumber <= 70 && AutoBlock) {
                                        try {
                                            KeyBinding.onTick(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode());
                                            Thread.sleep(randomNumber + 10);
                                            KeyBinding.onTick(Minecraft.getMinecraft().gameSettings.keyBindUseItem.getKeyCode());
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                    } else {
                                        KeyBinding.onTick(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
