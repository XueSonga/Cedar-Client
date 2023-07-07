package cn.XueSong.Client.mod.MoveMent;

import cn.XueSong.Client.mod.Mod;
import org.lwjgl.input.Keyboard;

public class Sprint extends Mod{

    public Sprint() {
        super("Sprint","×Ô¶¯¼²ÅÜ", true);
        setKey(Keyboard.KEY_Z);
    }

    @Override
    public void onUpdate(){

       //if (Minecraft.getMinecraft().gameSettings.keyBindForward.isPressed() || Minecraft.getMinecraft().gameSettings.keyBindLeft.isPressed() || Minecraft.getMinecraft().gameSettings.keyBindRight.isPressed()){
         //  if (!Minecraft.getMinecraft().thePlayer.isInWater()) Minecraft.getMinecraft().thePlayer.setSprinting(true);
      //  }
    }

    @Override
    public void render() {

    }
}
