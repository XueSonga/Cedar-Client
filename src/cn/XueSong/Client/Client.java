package cn.XueSong.Client;

import cn.XueSong.Client.Thread.ThreadManager;
import cn.XueSong.Client.gui.clickgui.ClickGui;
import cn.XueSong.Client.mod.ModManager;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class Client {

    public static final String NAEM = "Cedar";
    public static final String VERSION = "Dev 0.3";
    public static ModManager modManager;
    public static ThreadManager threadManager = new ThreadManager();
    public static ClickGui clickGui;
    public static void start(){
        modManager = new ModManager();
        modManager.load();//����mod
        threadManager.StartThreads();
        clickGui=new ClickGui();
        Display.setTitle(NAEM + " | " + VERSION);
        float f = GL11.glGetFloat(34047);
        float f1 = 1;
        f1 = Math.min(f1, f);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, 34046, f1);

    }

    public static void Stop(){
        System.out.println("CedarClient Stop");
    }
}
