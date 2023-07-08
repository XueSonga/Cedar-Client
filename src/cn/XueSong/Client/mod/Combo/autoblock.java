package cn.XueSong.Client.mod.Combo;


import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;

public class autoblock extends Mod{

    public autoblock() {
        super("AutoBlock", "×Ô¶¯·À¿³Smart", true);
    }

    boolean isUsing = false;
    @Override
    public void onUpdate() {
        if (Minecraft.getMinecraft().gameSettings.keyBindPickBlock.isKeyDown()&& !isUsing){
            SmartBlock thread = new SmartBlock();
            thread.start();
            isUsing = true;
        }
        if (!Minecraft.getMinecraft().gameSettings.keyBindPickBlock.isKeyDown()){
            isUsing = false;
        }
    }
}
