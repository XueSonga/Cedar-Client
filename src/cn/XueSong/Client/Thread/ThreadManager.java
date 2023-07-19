package cn.XueSong.Client.Thread;

import cn.XueSong.Client.Thread.threads.AimAssistThread;
import cn.XueSong.Client.Thread.threads.AutoClickThred;
import cn.XueSong.Client.Thread.threads.SubThread;
import cn.XueSong.Client.Thread.threads.WorldChangeThread;
import net.minecraft.client.Minecraft;

public class ThreadManager {

    Minecraft mc = Minecraft.getMinecraft();
    AimAssistThread aimAssistthread = new AimAssistThread(mc);
    AutoClickThred autoClickthread = new AutoClickThred();
    SubThread subthread = new SubThread();
    WorldChangeThread worldChangeThread = new WorldChangeThread(mc);

    public void StartThreads(){
        Thread AimAssistThread = new Thread(aimAssistthread);  AimAssistThread.start();
        Thread AutoClickThred = new Thread(autoClickthread);  AutoClickThred.start();
        Thread SubThread = new Thread(subthread); SubThread.start();
        Thread WorldChangeThread = new Thread(worldChangeThread); WorldChangeThread.start();
    }
}
