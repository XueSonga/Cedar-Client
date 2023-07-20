package cn.XueSong.Client.mod.Other;

import cn.XueSong.Client.mod.Category;
import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class Nick extends Mod {
    public static Boolean isNick =true;

    public static Minecraft mc = Minecraft.getMinecraft();
    public Nick() {
        super("Nick", "匿名模式", isNick, Category.Other, "隐藏自己的名称,在任何有你名字的地方,包括输入框");
    }

    public static String Nick(String str) {
        if(isNick){
            if(mc.thePlayer!=null&&mc.theWorld!=null){
                str = str.replace(Minecraft.getMinecraft().thePlayer.getName(), "§c[YourName]§r");
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
