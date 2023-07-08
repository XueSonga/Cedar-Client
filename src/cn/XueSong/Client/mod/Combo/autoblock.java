package cn.XueSong.Client.mod.Combo;


import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class autoblock extends Mod{

    public autoblock() {
        super("AutoBlock", "×Ô¶¯·À¿³Smart", true);
    }

    boolean isUsing = false;
    ItemStack woodenSword = new ItemStack(Item.getByNameOrId("net.minecraft.item.ItemSword"));
    @Override
    public void onUpdate() {
        if (Minecraft.getMinecraft().gameSettings.keyBindPickBlock.isKeyDown()&& !isUsing
                && (Minecraft.getMinecraft().thePlayer.getHeldItem().getItem() == Item.getByNameOrId("268")
                || Minecraft.getMinecraft().thePlayer.getHeldItem().getItem() == Item.getByNameOrId("267")
                || Minecraft.getMinecraft().thePlayer.getHeldItem().getItem() == Item.getByNameOrId("272")
                || Minecraft.getMinecraft().thePlayer.getHeldItem().getItem() == Item.getByNameOrId("276")
                || Minecraft.getMinecraft().thePlayer.getHeldItem().getItem() == Item.getByNameOrId("283"))
        ){
            System.out.println("click!");
            SmartBlock thread = new SmartBlock();
            thread.start();
            isUsing = true;
        }
        if (!Minecraft.getMinecraft().gameSettings.keyBindPickBlock.isKeyDown()){
            isUsing = false;
        }
    }
}
