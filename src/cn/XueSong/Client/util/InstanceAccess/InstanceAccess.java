package cn.XueSong.Client.util.InstanceAccess;

import cn.XueSong.Client.util.shader.impl.*;
import net.minecraft.client.Minecraft;
import cn.XueSong.Client.util.shader.base.*;
import java.util.ArrayList;
import java.util.List;

/**
*这是一个我们可以在需要访问游戏时执行的界面
*实例或我们的客户端实例，如果我们需要在任何地方。
*/
public interface InstanceAccess {//实例访问
    Minecraft mc = Minecraft.getMinecraft();
    List<Runnable> UI_BLOOM_RUNNABLES = new ArrayList<>();
    List<Runnable> UI_POST_BLOOM_RUNNABLES = new ArrayList<>();
    List<Runnable> UI_RENDER_RUNNABLES = new ArrayList<>();
    List<Runnable> UI_BLUR_RUNNABLES = new ArrayList<>();

    List<Runnable> NORMAL_PRE_RENDER_RUNNABLES = new ArrayList<>();
    List<Runnable> NORMAL_BLUR_RUNNABLES = new ArrayList<>();
    List<Runnable> NORMAL_POST_BLOOM_RUNNABLES = new ArrayList<>();
    List<Runnable> NORMAL_OUTLINE_RUNNABLES = new ArrayList<>();
    List<Runnable> NORMAL_RENDER_RUNNABLES = new ArrayList<>();
    List<Runnable> NORMAL_POST_RENDER_RUNNABLES = new ArrayList<>();

    List<Runnable> LIMITED_PRE_RENDER_RUNNABLES = new ArrayList<>();
    List<Runnable> LIMITED_POST_RENDER_RUNNABLES = new ArrayList<>();

}
