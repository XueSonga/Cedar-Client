package cn.XueSong.Client.util.InstanceAccess;

import cn.XueSong.Client.util.shader.impl.*;
import net.minecraft.client.Minecraft;
import cn.XueSong.Client.util.shader.base.*;
import java.util.ArrayList;
import java.util.List;

/**
*����һ�����ǿ�������Ҫ������Ϸʱִ�еĽ���
*ʵ�������ǵĿͻ���ʵ�������������Ҫ���κεط���
*/
public interface InstanceAccess {//ʵ������
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
