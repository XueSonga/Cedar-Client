package cn.XueSong.Client.Thread.threads;

import cn.XueSong.Client.util.chat.ChatUtil;
import cn.XueSong.Client.util.player.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class ScaffoldThread implements Runnable {

   public Boolean isScaffold;

    public void setScaffold(Boolean scaffold) {
        isScaffold = scaffold;
    }

    public Boolean getScaffold() {
        return isScaffold;
    }

    Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void run() {
        while (true){
            if (isScaffold){
                if(!PlayerUtil.isBlockUnder(mc.thePlayer.height+3,false)&&mc.gameSettings.keyBindBack.isKeyDown()&&mc.thePlayer.fallDistance==0){
                    KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(),true);
                }else if (PlayerUtil.isBlockUnder(mc.thePlayer.height+3,false)&&mc.gameSettings.keyBindBack.isKeyDown()&&mc.gameSettings.keyBindSneak.isKeyDown()){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(),false);
                }else if(PlayerUtil.isBlockUnder(mc.thePlayer.height+3,false)&&mc.gameSettings.keyBindForward.isKeyDown()){
                    KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(),false);
                }
            }else {
                break;
            }
        }
    }


}
