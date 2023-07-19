package cn.XueSong.Client.mod.Other;

import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class Nick extends Mod {
    public static Boolean isNick =true;

    public static Minecraft mc = Minecraft.getMinecraft();
    public Nick() {
        super("Nick", "ÄäÃûÄ£Ê½[K]", isNick);
        setKey(Keyboard.KEY_K);
    }

    public static String Nick(String str) {
        if(isNick){
            if(mc.thePlayer!=null&&mc.theWorld!=null){
                str = str.replace(Minecraft.getMinecraft().thePlayer.getName(), "¡ìc[YourName]¡ìr");
                return str;
            }
        }
        return str;
    }

    public static void setisNick(Boolean Nick){
        isNick=Nick;
    }

    @Override
    public void onDisable() {
        setisNick(false);
    }

    @Override
    public void onEnable() {
        setisNick(true);
    }
}
