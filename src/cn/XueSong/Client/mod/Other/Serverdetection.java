package cn.XueSong.Client.mod.Other;

import cn.XueSong.Client.mod.Mod;
import cn.XueSong.Client.serverdetection.ServerDetection;

public class Serverdetection extends Mod {
    String SeverName = "UnKnow";
    String SeverGame = "UnKnow";
    public Serverdetection() {
        super("ServerDetection", "UnKnow", true);
    }

    @Override
    public void onUpdate() {
        SeverName=ServerDetection.getSeverName();
        SeverGame=ServerDetection.getSeverGameName();
        setType(SeverName+" "+SeverGame);
    }
}
