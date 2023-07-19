package cn.XueSong.Client.mod.Other;

import cn.XueSong.Client.mod.Mod;
import cn.XueSong.Client.serverdetection.ServerDetection;

public class Serverdetection extends Mod {
    String SeverName = "未知服务器";
    String SeverGame = "未知游戏";
    public Serverdetection() {
        super("ServerDetection", "未知服务器", true);
    }

    @Override
    public void subThread() {
        SeverName=ServerDetection.getSeverName();
        SeverGame=ServerDetection.getSeverGameName();
        setType(SeverName+" "+SeverGame);
    }
}
