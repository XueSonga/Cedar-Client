package cn.XueSong.Client.mod.Other;

import cn.XueSong.Client.mod.Category;
import cn.XueSong.Client.mod.Mod;
import cn.XueSong.Client.serverdetection.ServerDetection;

public class Serverdetection extends Mod {
    String SeverName = "δ֪������";
    String SeverGame = "δ֪��Ϸ";
    public Serverdetection() {
        super("ServerDetection", "δ֪������", true, Category.Other, "���ڷ���������Ϸ��� Ŀǰ��֧��Hypxiel");
    }

    @Override
    public void subThread() {
        SeverName=ServerDetection.getSeverName();
        SeverGame=ServerDetection.getSeverGameName();
        setType(SeverName+" "+SeverGame);
    }
}
