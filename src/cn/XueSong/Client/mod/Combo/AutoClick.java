package cn.XueSong.Client.mod.Combo;

import cn.XueSong.Client.Thread.threads.AutoClickThred;
import cn.XueSong.Client.mod.Mod;

public class AutoClick extends Mod {
    public AutoClick() {
        super("AutoClick", "�Զ�����Smart", true);
        AutoClickThred.setAutoClick(true);
    }
}

